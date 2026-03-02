package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.IColumn;
import emprestes.game.sudoku.domain.GameDimension;
import emprestes.game.sudoku.domain.IPosition;
import emprestes.game.sudoku.domain.IRegion;
import emprestes.game.sudoku.domain.IRow;
import emprestes.game.sudoku.domain.GameSymbol;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static emprestes.game.sudoku.domain.GameDimension.D3X3;
import static java.util.Optional.ofNullable;

/**
 * Concrete Region implementation.
 *
 * @author Dude
 * @since 02/2026
 */
final class SudokuRegion implements IRegion {

    @Serial
    private static final long serialVersionUID = 527395914171821865L;

    private final byte number;
    private final GameDimension dimension;
    private final List<IPosition> positions;
    private final List<IColumn> columns;
    private final List<IRow> rows;

    SudokuRegion(Byte number) {
        this(number, D3X3);
    }

    SudokuRegion(Byte number, GameDimension dimension) {
        super();

        this.number = number;
        this.dimension = dimension;
        this.positions = new ArrayList<>(dimension.side);
        this.columns = new ArrayList<>(dimension.size);
        this.rows = new ArrayList<>(dimension.size);
    }

    @Override
    public IRegion next(byte regionNumber, GameDimension dimension) {
        return new SudokuRegion(regionNumber, dimension);
    }

    @Override
    public Byte getNumber() {
        return number;
    }

    @Override
    public boolean isCompleted() {
        boolean isCompleted = positions.stream().allMatch(IPosition::isVisible);
        boolean isCompletedRow = rows.stream().allMatch(IRow::isCompleted);
        boolean isCompletedColumn = columns.stream().allMatch(IColumn::isCompleted);

        return isCompleted && isCompletedRow && isCompletedColumn;
    }

    @Override
    public boolean contains(Character value) {
        return positions.stream()
                .filter(IPosition::nonBlank)
                .map(IPosition::getValue)
                .anyMatch(_value -> _value.equals(value));
    }

    @Override
    public boolean existsColumn(byte number) {
        return getColumn(number).isPresent();
    }

    @Override
    public IColumn getColumnBy(byte number) {
        return getColumn(number)
                .orElse(null);
    }

    @Override
    public IColumn getColumnOr(byte number, IColumn actualColumn) {
        return getColumn(number)
                .orElseGet(() -> ofNullable(actualColumn)
                        .filter(column -> column.equals(number))
                        .map(this::add)
                        .orElseGet(() -> newColumn(number)));
    }

    private Optional<IColumn> getColumn(byte number) {
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
                .map(IPosition::getValue)
                .filter(value -> !GameSymbol.BLANK.equals(value))
                .toArray(Character[]::new);
    }

    @Override
    public void clear() {
        positions.forEach(IPosition::clear);
    }

    private IColumn newColumn(byte number) {
        return add(new SudokuColumn(number));
    }

    @Override
    public void forEach(Consumer<IPosition> action) {
        positions.forEach(action);
    }

    @Override
    public Optional<IPosition> getBy(byte rowNumber, byte columnNumber) {
        return positions.stream()
                .filter(position -> position.equals(number, rowNumber, columnNumber))
                .findFirst();
    }

    @Override
    public IColumn add(IColumn column) {
        columns.add(column);
        return column;
    }

    @Override
    public boolean existsRow(byte number) {
        return getRow(number).isPresent();
    }

    @Override
    public IRow getRowBy(byte number) {
        return getRow(number)
                .orElse(null);
    }

    @Override
    public IRow getRowOr(byte number, IRow actualRow) {
        return getRow(number)
                .orElseGet(() -> ofNullable(actualRow)
                        .filter(row -> row.equals(number))
                        .map(this::add)
                        .orElseGet(() -> newRow(number)));
    }

    private Optional<IRow> getRow(byte number) {
        return rows.stream()
                .filter(row -> row.equals(number))
                .findFirst();
    }

    @Override
    public byte nextFromRow(byte row, byte regionIndex) {
        return dimension.nextFromRow(row, regionIndex);
    }

    private IRow newRow(byte number) {
        return add(new SudokuRow(number));
    }

    @Override
    public IRow add(IRow row) {
        rows.add(row);
        return row;
    }

    @Override
    public IPosition createPositionFor(IRow row, IColumn column) {
        final IPosition position = new SudokuPosition(this, row, column);

        positions.add(position);
        column.add(position);
        row.add(position);

        return position;
    }

    @Override
    public int getSizePositions() {
        return positions.size();
    }

    @Override
    public int getSizeRows() {
        return rows.stream()
                .map(IRow::getSizePositions)
                .distinct()
                .reduce(0, Integer::sum);
    }

    @Override
    public int getSizeColumns() {
        return columns.stream()
                .map(IColumn::getSizePositions)
                .distinct()
                .reduce(0, Integer::sum);
    }

    @Override
    public int compareTo(IRegion other) {
        return getNumber().compareTo(other.getNumber());
    }

    @Override
    public boolean equals(byte number) {
        return this.number == number;
    }

    @Override
    public boolean nonEquals(Object o) {
        return !equals(o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuRegion that)) return false;
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
