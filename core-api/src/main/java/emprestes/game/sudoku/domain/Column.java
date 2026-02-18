package emprestes.game.sudoku.domain;

import java.io.Serializable;
import java.util.Set;

import static java.util.Set.of;

/**
 * Represents a Sudoku column.
 *
 * @author Dude
 * @since 02/2026
 */
public interface Column extends Serializable, Comparable<Column> {

    /** @return true if all positions are blank. */
    boolean isBlank();

    /** @return column number (1-based). */
    Byte getNumber();

    /** @return column values as an array (including blanks). */
    Character[] toArrayValues();

    /** @return set of current column values. */
    default Set<Character> getAllValues() {
        return of(toArrayValues());
    }

    /** Add a position to this column. */
    void add(Position position);

    /** @return number of positions. */
    int getSizePositions();

    /** @return true if all positions are filled. */
    boolean isCompleted();

    /** @return true if the column contains the value. */
    boolean contains(Character value);

    /** @return true if the given number matches this column. */
    boolean equals(byte number);

    default boolean equals(Byte number) {
        return equals(number.byteValue());
    }
}
