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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static emprestes.game.sudoku.domain.Dimension.D3X3;
import static java.util.Optional.ofNullable;

public final class SudokuBoard implements Board {

    private static final long serialVersionUID = 5804390727980289178L;

    private final Dimension dimension;
    private final SymbolValues possibleSymbols;
    private final List<Region> regionList;

    public SudokuBoard() {
        this(D3X3);
    }

    public SudokuBoard(Dimension dimension) {
        super();

        this.dimension = dimension;
        this.possibleSymbols = dimension.possibleSymbolValues;
        this.regionList = new ArrayList<>(dimension.size);

        init();
    }

    private void init() {
        init(regionList::add);
    }

    private Stream<Region> getRegionList() {
        return regionList.stream();
    }

    @Override
    public Byte getSide() {
        return dimension.side;
    }

    @Override
    public void init(Consumer<Region> action) {
        ofNullable(action).ifPresent(_action -> {
            Column column = null;
            Row row = null;
            byte fromRow, toRow, fromColumn, toColumn;

            fromRow = fromColumn = dimension.from();
            toRow = toColumn = dimension.to();

            for (byte regionNumber = 1; regionNumber <= dimension.size; regionNumber++) {
                final Region region = new SudokuRegion(regionNumber, dimension);

                for (byte rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
                    final byte actualRowIndex = rowIndex;
                    final Row actualRow = row;
                    row = getRow(rowIndex)
                            .filter(region::nonExistsRow)
                            .map(region::add)
                            .orElseGet(() -> region.getRowOr(actualRowIndex, actualRow));
                    for (byte columnIndex = fromColumn; columnIndex <= toColumn; columnIndex++) {
                        final byte actualColumnIndex = columnIndex;
                        final Column actualColumn = column;
                        column = getColumn(columnIndex)
                                .filter(region::nonExistsColumn)
                                .map(region::add)
                                .orElseGet(() -> region.getColumnOr(actualColumnIndex, actualColumn));
                        region.createPositionFor(row, column);
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

    @Override
    public void start() {
        clear();
        initValues();
    }

    private void clear() {
        regionList.forEach(Region::clear);
    }

    private void initValues() {
        getRegionList()
                .flatMap(Region::getRows)
                .forEach(row -> row.forEach(this::initValueAt));
    }

    private void initValueAt(Position position) {
        final Character[] existSymbols = position.getAllExistSymbols();
        char symbol;

        do {
            symbol = possibleSymbols.generateNotIn(existSymbols);
        } while (position.isInvalidFor(symbol));

        position.setValue(symbol);
    }

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
        if (!(o instanceof SudokuBoard)) return false;
        SudokuBoard that = (SudokuBoard) o;
        return dimension == that.dimension &&
                Objects.equals(regionList, that.regionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimension, regionList);
    }

    @Override
    public String toString() {
        // TODO Print the board using BoardDimension and list of region
        return "\nSUDOKU                                                  SUDOKU\n" +
                "    || [1]   [2]   [3]    [4]   [5]   [6]    [7]   [8]   [9] ||\n" +
                "====++=====+=====+=====++=====+=====+=====++=====+=====+=====++\n" +
                "    ||       [1]       ||       [2]       ||       [3]       ||\n" +
                "    ||-----|-----|-----||-----|-----|-----||-----|-----|-----||\n" +
                "[1] ||  1  |     |     ||     |     |  4  ||     |     |     ||\n" +
                "    ||-----|-----|-----||-----|-----|-----||-----|-----|-----||\n" +
                "[2] ||     |  5  |  8  ||     |     |     ||  2  |  7  |  6  ||\n" +
                "    ||-----|-----|-----||-----|-----|-----||-----|-----|-----||\n" +
                "[3] ||     |  9  |     ||     |  2  |  8  ||  1  |     |     ||\n" +
                "    ++=====+=====+=====||=====+=====+=====||=====+=====+=====++\n" +
                "    ||       [4]       ||       [5]       ||       [6]       ||\n" +
                "    ||-----|-----|-----||-----|-----|-----||-----|-----|-----||\n" +
                "[4] ||     |     |  4  ||     |  5  |     ||  7  |  6  |  1  ||\n" +
                "    ||-----|-----|-----||-----|-----|-----||-----|-----|-----||\n" +
                "[5] ||  6  |  7  |     ||  8  |     |  2  ||     |  5  |  4  ||\n" +
                "    ||-----|-----|-----||-----|-----|-----||-----|-----|-----||\n" +
                "[6] ||  5  |  3  |  1  ||     |  6  |     ||  8  |     |     ||\n" +
                "    ++=====+=====+=====||=====+=====+=====||=====+=====+=====++\n" +
                "    ||       [7]       ||       [8]       ||       [9]       ||\n" +
                "    ||-----|-----|-----||-----|-----|-----||-----|-----|-----||\n" +
                "[7] ||     |     |  2  ||  1  |  4  |     ||     |  8  |     ||\n" +
                "    ||-----|-----|-----||-----|-----|-----||-----|-----|-----||\n" +
                "[8] ||  8  |  1  |  7  ||     |     |     ||  4  |  3  |     ||\n" +
                "    ||-----|-----|-----||-----|-----|-----||-----|-----|-----||\n" +
                "[9] ||     |     |     ||  7  |     |     ||     |     |  2  ||\n" +
                "====++=====+=====+=====++=====+=====+=====++=====+=====+=====++\n" +
                "SUDOKU                                                   SUDOKU";
    }
}
