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

    default void setValue(Value value) {
        setValue(value.getSymbol());
    }

    void play(Character value) throws WrongPositionException;

    boolean inColumn(Character value);

    default boolean notInColumn(Value value) {
        return notInColumn(value.getSymbol());
    }

    default boolean notInColumn(Character value) {
        return !inColumn(value);
    }

    boolean inRow(Character value);

    default boolean notInRow(Value value) {
        return notInRow(value.getSymbol());
    }

    default boolean notInRow(Character value) {
        return !inRow(value);
    }

    boolean inRegion(Character value);

    default boolean notInRegion(Value value) {
        return notInRegion(value.getSymbol());
    }

    default boolean notInRegion(Character value) {
        return !inRegion(value);
    }

    void clear();

    boolean isBlank();

    default boolean nonBlank() {
        return !isBlank();
    }

    boolean isValidFor(Character symbol);

    default boolean isValidFor(Value value) {
        return isValidFor(value.getSymbol());
    }

    boolean isVisible();

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

    boolean equals(byte regionNumber, byte rowNumber, byte columnNumber);

    default boolean equals(Character value) {
        return getValue().equals(value);
    }
}
