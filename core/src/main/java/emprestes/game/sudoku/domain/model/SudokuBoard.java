    package emprestes.game.sudoku.domain.model;

    import emprestes.game.sudoku.domain.GameDimension;
    import emprestes.game.sudoku.domain.GameSymbol;
    import emprestes.game.sudoku.domain.GameVisibility;
    import emprestes.game.sudoku.domain.IBoard;
    import emprestes.game.sudoku.domain.IColumn;
    import emprestes.game.sudoku.domain.IPosition;
    import emprestes.game.sudoku.domain.IRegion;
    import emprestes.game.sudoku.domain.IRow;
    import emprestes.game.sudoku.domain.IShuffleValueStrategy;
    import emprestes.game.sudoku.domain.exception.PositionException;
    import emprestes.game.sudoku.domain.exception.PositionNotFoundException;
    import emprestes.game.sudoku.domain.exception.WrongPositionException;

    import java.io.Serial;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Objects;
    import java.util.Optional;
    import java.util.Set;
    import java.util.TreeSet;
    import java.util.function.Consumer;

    import static emprestes.game.sudoku.domain.GameDimension.D3X3;
    import static emprestes.game.sudoku.domain.GameSymbol.BLANK;
    import static emprestes.game.sudoku.domain.GameSymbol.BREAK;
    import static emprestes.game.sudoku.domain.GameVisibility.EASY;
    import static java.lang.Math.max;
    import static java.util.Objects.hash;
    import static java.util.Objects.isNull;
    import static java.util.Optional.ofNullable;

    /**
 * Concrete Board implementation with initial fill using backtracking.
 *
 * @author Dude
 * @since 02/2026
 */
public final class SudokuBoard implements IBoard {

    @Serial
    private static final long serialVersionUID = 5804390727980289178L;

    private final GameDimension dimension;

    private final GameSymbol symbols;

    private GameVisibility visibility;

    private final IShuffleValueStrategy shuffleValueStrategy;

    private final List<IRegion> regionList;

    private final List<IPosition> positionList = new ArrayList<>();

    private final Set<IRow> rowList = new TreeSet<>();

    public SudokuBoard() {
        this(D3X3);
    }

    public SudokuBoard(GameDimension dimension) {
        this(EASY, dimension);
    }

    SudokuBoard(GameVisibility difficult, GameDimension dimension) {
        super();

        this.visibility = difficult;
        this.dimension = dimension;
        this.symbols = dimension.symbols;
        this.shuffleValueStrategy = new SudokuShuffleValueStrategy();
        this.regionList = new ArrayList<>(dimension.size);

        init();
    }

    private void init() {
        init(regionList::add);
    }

    public void setVisibility(GameVisibility visibility) {
        this.visibility = visibility;
    }

    /** {@inheritDoc} */
    @Override
    public Byte getSide() {
        return dimension.side;
    }

    /** {@inheritDoc} */
    @Override
    public void init(Consumer<IRegion> action) {
        ofNullable(action).ifPresent(_action -> {
            IRegion region = null;
            IColumn column = null;
            IRow row = null;
            byte fromRow, toRow, fromColumn, toColumn;

            fromRow = fromColumn = dimension.from();
            toRow = toColumn = dimension.to();

            for (byte regionNumber = 1; regionNumber <= dimension.size; regionNumber++) {
                if (regionNumber == 1) {
                    region = new SudokuRegion(regionNumber, dimension);
                } else {
                    region = region.next(regionNumber, dimension);
                }

                for (byte rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
                    final byte actualRowIndex = rowIndex;
                    final IRow actualRow = row;
                    final IRegion finalRegion = region;
                    row = getRow(rowIndex)
                            .filter(region::nonExistsRow)
                            .map(region::add)
                            .orElseGet(() -> finalRegion.getRowOr(actualRowIndex, actualRow));
                    for (byte columnIndex = fromColumn; columnIndex <= toColumn; columnIndex++) {
                        final byte actualColumnIndex = columnIndex;
                        final IColumn actualColumn = column;
                        column = getColumn(columnIndex)
                                .filter(region::nonExistsColumn)
                                .map(region::add)
                                .orElseGet(() -> finalRegion.getColumnOr(actualColumnIndex, actualColumn));
                        positionList.add(region.createPositionFor(row, column));
                        rowList.add(row);
                    }
                }

                _action.accept(region);

                fromColumn = region.nextFromColumn(toColumn, regionNumber);
                fromRow = region.nextFromRow(fromRow, regionNumber);

                toColumn = region.nextTo(fromColumn);
                toRow = region.nextTo(fromRow);
            }
        });
    }

    private Optional<IColumn> getColumn(byte number) {
        return regionList.stream()
                .filter(region -> region.existsColumn(number))
                .map(region -> region.getColumnBy(number))
                .findFirst();
    }

    private Optional<IRow> getRow(byte number) {
        return regionList.stream()
                .filter(region -> region.existsRow(number))
                .map(region -> region.getRowBy(number))
                .findFirst();
    }

    /** {@inheritDoc} */
    @Override
    public void start() {
        clear();
        initValues();
    }

    private void clear() {
        regionList.forEach(IRegion::clear);
    }

    private void initValues() {
        shuffleValueStrategy.shuffle(positionList, symbols::shuffle, visibility::isVisible);
    }

    /** {@inheritDoc} */
    @Override
    public void play(Character value, byte regionNumber, byte rowNumber, byte columnNumber) throws PositionException {
        final IPosition position = regionList.stream()
                .filter(region -> region.equals(regionNumber))
                .map(region -> region.getBy(rowNumber, columnNumber))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new PositionNotFoundException("Position has not found for row(%s) and column(%s)",
                        rowNumber, columnNumber));

        play(value, position);
    }

    /** {@inheritDoc} */
    @Override
    public void play(Character value, IPosition position) throws WrongPositionException {
        position.play(value);
    }

    @Override
    public boolean isGameOver() {
        return regionList.stream()
                .map(IRegion::isCompleted)
                .reduce(true, ((result, isCompleted) -> result && isCompleted));
    }

    int getSizePositions() {
        return regionList.stream()
                .map(IRegion::getSizePositions)
                .reduce(0, Integer::sum);
    }

    int getSizeRegions() {
        return regionList.size();
    }

    int getSizeRows() {
        return regionList.stream()
                .map(IRegion::getSizeRows)
                .distinct()
                .reduce(0, Integer::sum);
    }

    int getSizeColumns() {
        return regionList.stream()
                .map(IRegion::getSizeColumns)
                .distinct()
                .reduce(0, Integer::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuBoard that)) return false;
        return dimension == that.dimension && Objects.equals(regionList, that.regionList);
    }

    @Override
    public int hashCode() {
        return hash(dimension, regionList);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        var horizontalLine = createHorizontalLine(dimension.side);
        var rowCounter = 1;

        sb.append(horizontalLine);

        for (var row : rowList) {
            IPosition previousPosition = null;

            sb.append('|');

            for (var position : row.positionList()) {
                if (isNull(previousPosition)) {
                    previousPosition = position;
                }

                if (position.hasChangedRegion(previousPosition)) {
                    sb.append(BLANK).append('|');
                }

                sb.append(BLANK);

                if (position.isVisible()) {
                    sb.append(position.getValue());
                } else {
                    sb.append(BLANK);
                }

                previousPosition = position;
            }

            sb.append(BLANK).append('|');

            if (rowCounter++ % dimension.side == 0) {
                sb.append(horizontalLine);
            } else {
                sb.append(BREAK);
            }
        }

        return sb.toString();
    }

    private String createHorizontalLine(int boxSize) {
        var line = new StringBuilder().append(BREAK);

        for (int i = 0; i < boxSize; i++) {
            line.append("+");
            line.repeat("-", max(0, boxSize * 2 + 1));
        }

        line.append("+").append(BREAK);

        return line.toString();
    }
}
