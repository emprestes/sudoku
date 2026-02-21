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

    /** @return the column. */
    Column getColumn();


    /** @return current value. */
    Character getValue();

    /** @return all symbols already present in region/row/column. */
    Character[] usedSymbols();

    /** Set the value. */
    void setValue(Character value);

    default void setValue(Value value) {
        setValue(value.getSymbol());
    }

    /** Play a value with validation. */
    void play(Character value) throws WrongPositionException;

    /** @return true if value exists in column. */
    boolean inColumn(Character value);

    default boolean notInColumn(Character value) {
        return !inColumn(value);
    }

    /** @return true if value exists in row. */
    boolean inRow(Character value);

    default boolean notInRow(Character value) {
        return !inRow(value);
    }

    /** @return true if value exists in region. */
    boolean inRegion(Character value);

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

    /** @return true if visible. */
    boolean isVisible();

    default boolean isInvalidFor(Character value) {
        return !isValidFor(value);
    }

    /** Compare coordinates with given numbers. */
    boolean equals(byte regionNumber, byte rowNumber, byte columnNumber);

    default boolean equals(Character value) {
        return getValue().equals(value);
    }

    default boolean hasChangedRegion(Position position) {
        return hasChangedRegion(position.getRegion());
    }

    default boolean hasChangedRegion(Region region) {
        return getRegion().nonEquals(region);
    }
}
