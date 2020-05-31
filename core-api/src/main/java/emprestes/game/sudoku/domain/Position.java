package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.io.Serializable;

public interface Position extends Serializable, Comparable<Position> {

    Region getRegion();

    Row getRow();

    Column getColumn();

    Character getValue();

    void setValue(Character value);

    void play(Character value) throws WrongPositionException;

    boolean contains(Character value);

    boolean isValid();

    boolean isVisible();

    void setVisible(boolean visible);

    default boolean isInvalid() {
        return !isValid();
    }

    default boolean isInvisible() {
        return !isVisible();
    }

    boolean equals(byte regionNumber, byte rowNumber, byte columnNumber);
}
