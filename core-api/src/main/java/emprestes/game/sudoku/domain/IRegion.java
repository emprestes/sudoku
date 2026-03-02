package emprestes.game.sudoku.domain;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.Set.of;

/**
 * Sudoku region (sub-grid) containing rows, columns, and positions.
 *
 * @author Dude
 * @since 02/2026
 */
public interface IRegion extends Serializable, Comparable<IRegion> {

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
    IPosition createPositionFor(IRow row, IColumn column);

    /** Execute an action for each position in this region. */
    void forEach(Consumer<IPosition> action);

    /** Get a position by coordinates. */
    Optional<IPosition> getBy(byte rowNumber, byte columnNumber);

    IColumn add(IColumn column);

    boolean existsColumn(byte number);

    default boolean nonExistsColumn(IColumn column) {
        return nonExistsColumn(column.getNumber());
    }

    default boolean nonExistsColumn(byte number) {
        return !existsColumn(number);
    }

    IRow add(IRow row);

    boolean existsRow(byte number);

    default boolean nonExistsRow(IRow row) {
        return nonExistsRow(row.getNumber());
    }

    default boolean nonExistsRow(byte number) {
        return !existsRow(number);
    }

    IRow getRowBy(byte number);

    IRow getRowOr(byte number, IRow actualRow);

    IColumn getColumnBy(byte number);

    IColumn getColumnOr(byte number, IColumn actualColumn);

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
    IRegion next(byte regionNumber, GameDimension dimension);
}
