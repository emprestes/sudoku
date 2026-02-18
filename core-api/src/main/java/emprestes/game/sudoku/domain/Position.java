package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.io.Serializable;

/**
 * Represents a Sudoku cell with coordinates, value, and validation/visibility rules.
 *
 * @author Dude
 * @since 02/2026
 */
public interface Position extends Serializable, Comparable<Position> {

    /** @return the region. */
    Region getRegion();

    /** @return the row. */
    Row getRow();

    /** @return row number (1-based). */
    Byte getRowNumber();

    /** @return the column. */
    Column getColumn();

    /** @return column number (1-based). */
    Byte getColumnNumber();

    /** @return current value. */
    Character getValue();

    /** @return all symbols already present in region/row/column. */
    Character[] getAllExistSymbols();

    /** Set the value. */
    void setValue(Character value);

    default void setValue(Value value) {
        setValue(value.getSymbol());
    }

    /** Play a value with validation. */
    void play(Character value) throws WrongPositionException;

    /** @return true if value exists in column. */
    boolean inColumn(Character value);

    default boolean notInColumn(Value value) {
        return notInColumn(value.getSymbol());
    }

    default boolean notInColumn(Character value) {
        return !inColumn(value);
    }

    /** @return true if value exists in row. */
    boolean inRow(Character value);

    default boolean notInRow(Value value) {
        return notInRow(value.getSymbol());
    }

    default boolean notInRow(Character value) {
        return !inRow(value);
    }

    /** @return true if value exists in region. */
    boolean inRegion(Character value);

    default boolean notInRegion(Value value) {
        return notInRegion(value.getSymbol());
    }

    default boolean notInRegion(Character value) {
        return !inRegion(value);
    }

    /** Clear the value. */
    void clear();

    /** @return true if blank. */
    boolean isBlank();

    default boolean nonBlank() {
        return !isBlank();
    }

    /** Check if symbol is valid for this position. */
    boolean isValidFor(Character symbol);

    default boolean isValidFor(Value value) {
        return isValidFor(value.getSymbol());
    }

    /** @return true if visible. */
    boolean isVisible();

    /** Set visibility. */
    void setVisible(boolean visible);

    default boolean isInvalidFor(Character value) {
        return !isValidFor(value);
    }

    default boolean isInvalidFor(Value value) {
        return isInvalidFor(value.getSymbol());
    }

    default boolean isInvisible() {
        return !isVisible();
    }

    /** Compare coordinates with given numbers. */
    boolean equals(byte regionNumber, byte rowNumber, byte columnNumber);

    default boolean equals(Character value) {
        return getValue().equals(value);
    }
}
