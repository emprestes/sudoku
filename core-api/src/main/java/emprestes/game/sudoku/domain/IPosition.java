package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.io.Serializable;

/**
 * Represents a Sudoku cell with coordinates, value, and validation/visibility rules.
 *
 * @author Dude
 * @since 02/2026
 */
public interface IPosition extends Serializable, Comparable<IPosition> {

    /** @return the region. */
    IRegion getRegion();

    default int regionSize() {
        return getRegion().getSizePositions();
    }

    /** @return the row. */
    IRow getRow();

    /** @return the column. */
    IColumn getColumn();

    /** @return current value. */
    Character getValue();

    /** @return all symbols already present in region/row/column. */
    Character[] usedSymbols();

    /** Set the value. */
    void setValue(Character value);

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

    /** Set cell visibility. */
    void setVisible(boolean visible);

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

    default boolean hasChangedRegion(IPosition position) {
        return hasChangedRegion(position.getRegion());
    }

    default boolean hasChangedRegion(IRegion region) {
        return getRegion().nonEquals(region);
    }
}
