package emprestes.game.sudoku.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.Set.of;

/**
 * Represents a Sudoku row.
 *
 * @author Dude
 * @since 02/2026
 */
public interface Row extends Serializable, Comparable<Row> {

    /** @return true if all positions are blank. */
    boolean isBlank();

    /** @return row number (1-based). */
    Byte getNumber();

    /** @return row values as array (including blanks). */
    Character[] toArrayValues();

    /** @return set of current row values. */
    default Set<Character> getAllValues() {
        return of(toArrayValues());
    }

    /** Add a position to this row. */
    void add(Position position);

    /** Execute an action for each position. */
    void forEach(Consumer<Position> action);

    /** @return number of positions. */
    int getSizePositions();

    /** @return true if all positions are filled. */
    boolean isCompleted();

    /** @return true if the row contains the value. */
    boolean contains(Character value);

    /** @return true if the number matches this row. */
    boolean equals(byte number);

    default boolean equals(Byte number) {
        return equals(number.byteValue());
    }
}
