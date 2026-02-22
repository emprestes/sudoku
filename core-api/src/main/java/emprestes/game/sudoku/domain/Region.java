package emprestes.game.sudoku.domain;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

import static java.util.Set.of;

/**
 * Sudoku region (sub-grid) containing rows, columns, and positions.
 *
 * @author Dude
 * @since 02/2026
 */
public interface Region extends Serializable, Comparable<Region> {

    /** @return region identifier (1-based). */
    Byte getNumber();

    /** @return true if the given number matches this region. */
    boolean equals(byte number);

    default boolean equals(Byte number) {
        return equals(number.byteValue());
    }

    boolean nonEquals(Object o);

    /** Clear positions. */
    void clear();

    /** @return true if contains the value. */
    boolean contains(Character value);

    /** @return true if all positions are filled. */
    boolean isCompleted();

    /** Create a position for the given row/column. */
    Position createPositionFor(Row row, Column column);

    /** Get a position by coordinates. */
    Optional<Position> getBy(byte rowNumber, byte columnNumber);

    Column add(Column column);

    boolean existsColumn(byte number);

    default boolean nonExistsColumn(Column column) {
        return nonExistsColumn(column.getNumber());
    }

    default boolean nonExistsColumn(byte number) {
        return !existsColumn(number);
    }

    Row add(Row row);

    boolean existsRow(byte number);

    default boolean nonExistsRow(Row row) {
        return nonExistsRow(row.getNumber());
    }

    default boolean nonExistsRow(byte number) {
        return !existsRow(number);
    }

    Row getRowBy(byte number);

    Row getRowOr(byte number, Row actualRow);

    Column getColumnBy(byte number);

    Column getColumnOr(byte number, Column actualColumn);

    int getSizePositions();

    int getSizeRows();

    int getSizeColumns();

    byte nextFromColumn(byte column, byte regionNumber);

    byte nextFromRow(byte row, byte regionIndex);

    byte nextTo(byte value);

    /** @return non-blank values in the region. */
    Character[] toArrayValues();

    default Set<Character> getAllValues() {
        return of(toArrayValues());
    }

    /** Create the next chained region in the given dimension. */
    Region next(byte regionNumber, Dimension dimension);
}
