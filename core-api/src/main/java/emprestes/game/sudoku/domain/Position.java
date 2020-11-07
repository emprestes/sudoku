package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.io.Serializable;

public interface Position extends Serializable, Comparable<Position> {

    Region getRegion();

    Row getRow();

    Byte getRowNumber();

    Column getColumn();

    Byte getColumnNumber();

    Character getValue();

    Character[] getAllExistSymbols();

    void setValue(Character value);

    void play(Character value) throws WrongPositionException;

    boolean inColumn(Character value);

    default boolean notInColumn(Character value) {
        return !inColumn(value);
    }

    boolean inRow(Character value);

    default boolean notInRow(Character value) {
        return !inRow(value);
    }

    boolean inRegion(Character value);

    default boolean notInRegion(Character value) {
        return !inRegion(value);
    }

    void clear();

    boolean isBlank();

    default boolean nonBlank() {
        return !isBlank();
    }

    boolean isValidFor(Character symbol);

    boolean isVisible();

    void setVisible(boolean visible);

    default boolean isInvalidFor(Character symbol) {
        return !isValidFor(symbol);
    }

    default boolean isInvisible() {
        return !isVisible();
    }

    boolean equals(byte regionNumber, byte rowNumber, byte columnNumber);

    default boolean equals(Character value) {
        return getValue().equals(value);
    }
}
