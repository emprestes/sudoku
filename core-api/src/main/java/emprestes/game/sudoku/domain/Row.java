package emprestes.game.sudoku.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.Set.of;

public interface Row extends Serializable, Comparable<Row> {

    boolean isBlank();

    Byte getNumber();

    Character[] toArrayValues();

    default Set<Character> getAllValues() {
        return of(toArrayValues());
    }

    void add(Position position);

    void forEach(Consumer<Position> action);

    int getSizePositions();

    boolean isCompleted();

    boolean contains(Character value);

    boolean equals(byte number);

    default boolean equals(Byte number) {
        return equals(number.byteValue());
    }
}
