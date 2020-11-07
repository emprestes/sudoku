package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Column;
import emprestes.game.sudoku.domain.Dimension;
import emprestes.game.sudoku.domain.Position;
import emprestes.game.sudoku.domain.Region;
import emprestes.game.sudoku.domain.Row;
import emprestes.game.sudoku.domain.SymbolValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static emprestes.game.sudoku.domain.Dimension.D3X3;
import static java.util.Optional.ofNullable;

final class SudokuRegion implements Region {

    private static final long serialVersionUID = 527395914171821865L;

    private final byte number;
    private final Dimension dimension;
    private final List<Position> positions;
    private final List<Column> columns;
    private final List<Row> rows;

    SudokuRegion(Byte number) {
        this(number, D3X3);
    }

    SudokuRegion(Byte number, Dimension dimension) {
        super();

        this.number = number;
        this.dimension = dimension;
        this.positions = new ArrayList<>(dimension.side);
        this.columns = new ArrayList<>(dimension.size);
        this.rows = new ArrayList<>(dimension.size);
    }

    @Override
    public Byte getNumber() {
        return number;
    }

    @Override
    public Byte getSide() {
        return dimension.side;
    }

    @Override
    public void init(Consumer<Position> action) {
        positions.forEach(action);
    }

    @Override
    public boolean isCompleted() {
        boolean isCompleted = positions.stream().allMatch(Position::isVisible);
        boolean isCompletedRow = rows.stream().allMatch(Row::isCompleted);
        boolean isCompletedColumn = columns.stream().allMatch(Column::isCompleted);

        return isCompleted && isCompletedRow && isCompletedColumn;
    }

    private boolean isBlank(Position position, Character value) {
        return position.isBlank()
                && position.notInRegion(value)
                && position.notInRow(value)
                && position.notInColumn(value);
    }

    @Override
    public boolean contains(Character value) {
        return positions.stream()
                .filter(Position::nonBlank)
                .map(Position::getValue)
                .anyMatch(_value -> _value.equals(value));
    }

    @Override
    public boolean existsColumn(byte number) {
        return getColumn(number).isPresent();
    }

    @Override
    public Column getColumnBy(byte number) {
        return getColumn(number)
                .orElse(null);
    }

    @Override
    public Column getColumnOr(byte number, Column actualColumn) {
        return getColumn(number)
                .orElseGet(() -> ofNullable(actualColumn)
                        .filter(column -> column.equals(number))
                        .map(this::add)
                        .orElseGet(() -> newColumn(number)));
    }

    private Optional<Column> getColumn(byte number) {
        return columns.stream()
                .filter(column -> column.equals(number))
                .findFirst();
    }

    @Override
    public byte nextFromColumn(byte column, byte regionNumber) {
        return dimension.nextFromColumn(column, regionNumber);
    }

    @Override
    public byte nextTo(byte value) {
        return dimension.nextTo(value);
    }

    @Override
    public Character[] toArrayValues() {
        return positions.stream()
                .map(Position::getValue)
                .filter(value -> !SymbolValues.BLANK.equals(value))
                .toArray(Character[]::new);
    }

    @Override
    public void clear() {
        positions.forEach(Position::clear);
    }

    @Override
    public void init(Character value) {
         positions.stream()
                 .filter(position -> isBlank(position, value))
                 .findAny()
                 .ifPresent(position -> position.setValue(value));
    }

    private Column newColumn(byte number) {
        return add(new SudokuColumn(number));
    }

    @Override
    public Optional<Position> getBy(byte rowNumber, byte columnNumber) {
        return positions.stream()
                .filter(position -> position.equals(number, rowNumber, columnNumber))
                .findFirst();
    }

    @Override
    public Column add(Column column) {
        columns.add(column);
        return column;
    }

    @Override
    public boolean existsRow(byte number) {
        return getRow(number).isPresent();
    }

    @Override
    public Stream<Row> getRows() {
        return rows.stream();
    }

    @Override
    public Row getRowBy(byte number) {
        return getRow(number)
                .orElse(null);
    }

    @Override
    public Row getRowOr(byte number, Row actualRow) {
        return getRow(number)
                .orElseGet(() -> ofNullable(actualRow)
                        .filter(row -> row.equals(number))
                        .map(this::add)
                        .orElseGet(() -> newRow(number)));
    }

    private Optional<Row> getRow(byte number) {
        return rows.stream()
                .filter(row -> row.equals(number))
                .findFirst();
    }

    @Override
    public byte nextFromRow(byte row, byte regionIndex) {
        return dimension.nextFromRow(row, regionIndex);
    }

    private Row newRow(byte number) {
        return add(new SudokuRow(number));
    }

    @Override
    public Row add(Row row) {
        rows.add(row);
        return row;
    }

    @Override
    public void createPositionFor(Row row, Column column) {
        final Position position = new SudokuPosition(this, row, column);

        positions.add(position);
        column.add(position);
        row.add(position);
    }

    @Override
    public int getSizePositions() {
        return positions.size();
    }

    @Override
    public int getSizeRows() {
        return rows.stream()
                .map(Row::getSizePositions)
                .distinct()
                .reduce(0, Integer::sum);
    }

    @Override
    public int getSizeColumns() {
        return columns.stream()
                .map(Column::getSizePositions)
                .distinct()
                .reduce(0, Integer::sum);
    }

    @Override
    public int compareTo(Region other) {
        return getNumber().compareTo(other.getNumber());
    }

    @Override
    public boolean equals(byte number) {
        return this.number == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuRegion)) return false;
        SudokuRegion that = (SudokuRegion) o;
        return equals(that.number) &&
                dimension == that.dimension;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, dimension);
    }

    @Override
    public String toString() {
        return "Region: " + number;
    }
}
