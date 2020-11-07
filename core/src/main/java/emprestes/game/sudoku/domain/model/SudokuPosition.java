package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Column;
import emprestes.game.sudoku.domain.Position;
import emprestes.game.sudoku.domain.Region;
import emprestes.game.sudoku.domain.Row;
import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static emprestes.game.sudoku.domain.SymbolValues.BLANK;
import static java.lang.String.format;

final class SudokuPosition implements Position {

    private static final long serialVersionUID = 1463509382546272675L;

    private final Region region;
    private final Row row;
    private final Column column;

    private Character value;
    private boolean valid;
    private boolean visible;

    SudokuPosition(byte regionNumber, byte rowNumber, byte columnNumber) {
        this(new SudokuRegion(regionNumber), new SudokuRow(rowNumber), new SudokuColumn(columnNumber));
    }

    SudokuPosition(Region region, Row row, Column column) {
        super();

        this.region = region;
        this.row = row;
        this.column = column;
        this.value = BLANK;
        this.visible = false;
        this.valid = false;
    }

    @Override
    public Region getRegion() {
        return region;
    }

    @Override
    public Row getRow() {
        return row;
    }

    @Override
    public Byte getRowNumber() {
        return getRow().getNumber();
    }

    @Override
    public Column getColumn() {
        return column;
    }

    @Override
    public Byte getColumnNumber() {
        return getColumn().getNumber();
    }

    @Override
    public Character getValue() {
        return value;
    }

    @Override
    public Character[] getAllExistSymbols() {
        final List<Character> allValues = new ArrayList<>();

        allValues.addAll(region.getAllValues());
        allValues.addAll(row.getAllValues());
        allValues.addAll(column.getAllValues());

        return allValues.stream()
                .distinct()
                .toArray(Character[]::new);
    }

    @Override
    public void setValue(Character value) {
        this.value = value;
    }

    @Override
    public void play(Character value) throws WrongPositionException {
        valid = this.value.equals(value);

        if (isInvalidFor(value)) {
            throw new WrongPositionException("Wrong position: %s", this);
        }

        this.value = value;
        this.visible = valid;
    }

    @Override
    public boolean inColumn(Character value) {
        return column.contains(value);
    }

    @Override
    public boolean inRow(Character value) {
        return row.contains(value);
    }

    @Override
    public boolean inRegion(Character value) {
        return region.contains(value);
    }

    @Override
    public void clear() {
        setValue(BLANK);
    }

    @Override
    public boolean isBlank() {
        return BLANK.equals(value);
    }

    @Override
    public boolean isValidFor(Character value) {
        return notInRegion(value) && notInRow(value) && notInColumn(value);
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public int compareTo(Position other) {
        int comp = getRegion().compareTo(other.getRegion());

        if (comp == 0) {
            comp = getRow().compareTo(other.getRow());
        }

        if (comp == 0) {
            comp = getColumn().compareTo(other.getColumn());
        }

        return comp;
    }

    @Override
    public boolean equals(byte regionNumber, byte rowNumber, byte columnNumber) {
        return region.equals(regionNumber) && row.equals(rowNumber) && column.equals(columnNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuPosition)) return false;
        SudokuPosition that = (SudokuPosition) o;
        return valid == that.valid &&
                visible == that.visible &&
                region.equals(that.region.getNumber()) &&
                row.equals(that.row.getNumber()) &&
                column.equals(that.column.getNumber()) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valid, visible, region.getNumber(), row.getNumber(), column.getNumber(), value);
    }

    @Override
    public String toString() {
        return format("%s %s %s -> %s (%s)", region, row, column, getValue(), isVisible());
    }
}
