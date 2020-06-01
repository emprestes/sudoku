package emprestes.game.sudoku.domain;

import java.io.Serializable;
import java.util.Optional;

public interface Region extends Serializable, Comparable<Region> {

    Byte getNumber();

    Byte getSide();

    boolean equals(byte number);

    default boolean equals(Byte number) {
        return equals(number.byteValue());
    }

    void init();

    void init(InitPosition action);

    boolean contains(Character value);

    boolean isCompleted();

    void createPositionFor(Row row, Column column);

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
}
