package emprestes.game.sudoku.domain;

import java.io.Serializable;
import java.util.Set;

import static java.util.Set.of;

public interface Column extends Serializable, Comparable<Column> {

    boolean isBlank();

    Byte getNumber();

    Character[] toArrayValues();

    default Set<Character> getAllValues() {
        return of(toArrayValues());
    }

    void add(Position position);

    int getSizePositions();

    boolean isCompleted();

    boolean contains(Character value);

    boolean equals(byte number);

    default boolean equals(Byte number) {
        return equals(number.byteValue());
    }
}
