package emprestes.game.sudoku.domain;

import java.io.Serializable;

public interface Row extends Serializable, Comparable<Row> {

    Byte getNumber();

    void add(Position position);

    int getSizePositions();

    boolean isCompleted();

    boolean contains(Character value);

    boolean equals(byte number);

    default boolean equals(Byte number) {
        return equals(number.byteValue());
    }
}
