    package emprestes.game.sudoku.domain.model;

    import emprestes.game.sudoku.domain.Board;
    import emprestes.game.sudoku.domain.Column;
    import emprestes.game.sudoku.domain.Dimension;
    import emprestes.game.sudoku.domain.Position;
    import emprestes.game.sudoku.domain.Region;
    import emprestes.game.sudoku.domain.Row;
    import emprestes.game.sudoku.domain.SymbolValues;
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
    import java.util.Random;
    import java.util.function.Consumer;

    import static emprestes.game.sudoku.domain.Dimension.D3X3;
    import static emprestes.game.sudoku.domain.SymbolValues.BLANK;
    import static emprestes.game.sudoku.domain.SymbolValues.BREAK;
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
public final class SudokuBoard implements Board {

    @Serial
    private static final long serialVersionUID = 5804390727980289178L;

    private final Dimension dimension;
    private final SymbolValues symbols;
    private final List<Region> regionList;
    private final VisibilityMaskStrategy visibilityMaskStrategy;

    private final List<Position> positionList = new ArrayList<>();
    private final Set<Row> rowList = new TreeSet<>();

    public SudokuBoard() {
        this(D3X3);
    }

    public SudokuBoard(Dimension dimension) {
        this(dimension, new EasyVisibilityMaskStrategy(new Random()));
    }

    SudokuBoard(Dimension dimension, VisibilityMaskStrategy visibilityMaskStrategy) {
        super();

        this.dimension = dimension;
        this.symbols = dimension.symbols;
        this.regionList = new ArrayList<>(dimension.size);
        this.visibilityMaskStrategy = visibilityMaskStrategy;

        init();
    }

    private void init() {
        init(regionList::add);
    }

    /** {@inheritDoc} */
    @Override
    public Byte getSide() {
        return dimension.side;
    }

    /** {@inheritDoc} */
    @Override
    public void init(Consumer<Region> action) {
        ofNullable(action).ifPresent(_action -> {
            Region region = null;
            Column column = null;
            Row row = null;
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
                    final Row actualRow = row;
                    final Region finalRegion = region;
                    row = getRow(rowIndex)
                            .filter(region::nonExistsRow)
                            .map(region::add)
                            .orElseGet(() -> finalRegion.getRowOr(actualRowIndex, actualRow));
                    for (byte columnIndex = fromColumn; columnIndex <= toColumn; columnIndex++) {
                        final byte actualColumnIndex = columnIndex;
                        final Column actualColumn = column;
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

    private Optional<Column> getColumn(byte number) {
        return regionList.stream()
                .filter(region -> region.existsColumn(number))
                .map(region -> region.getColumnBy(number))
                .findFirst();
    }

    private Optional<Row> getRow(byte number) {
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
        regionList.forEach(Region::clear);
    }

    private void initValues() {
        drawValuesTo(positionList);
        visibilityMaskStrategy.apply(positionList);
    }

    private void drawValuesTo(List<Position> positions) {
        drawWithBacktracking(positions, 0);
    }

    private boolean drawWithBacktracking(List<Position> positions, int index) {
        if (index >= positions.size()) {
            return true;
        }

        var position = positions.get(index);

        var candidates = symbols.shuffle(position.usedSymbols());
        for (Character symbol : candidates) {
            if (position.isInvalidFor(symbol)) {
                continue;
            }

            position.setValue(symbol);
            if (drawWithBacktracking(positions, index + 1)) {
                return true;
            }
            position.clear();
        }

        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void play(Character value, byte regionNumber, byte rowNumber, byte columnNumber) throws PositionException {
        final Position position = regionList.stream()
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
    public void play(Character value, Position position) throws WrongPositionException {
        position.play(value);
    }

    @Override
    public boolean isGameOver() {
        return regionList.stream()
                .map(Region::isCompleted)
                .reduce(true, ((result, isCompleted) -> result && isCompleted));
    }

    int getSizePositions() {
        return regionList.stream()
                .map(Region::getSizePositions)
                .reduce(0, Integer::sum);
    }

    int getSizeRegions() {
        return regionList.size();
    }

    int getSizeRows() {
        return regionList.stream()
                .map(Region::getSizeRows)
                .distinct()
                .reduce(0, Integer::sum);
    }

    int getSizeColumns() {
        return regionList.stream()
                .map(Region::getSizeColumns)
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
            Position previousPosition = null;

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
