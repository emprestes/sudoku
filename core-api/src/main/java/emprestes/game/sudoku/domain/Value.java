package emprestes.game.sudoku.domain;

import java.util.Objects;

import static java.lang.Integer.compare;
import static java.util.Arrays.stream;

/**
 * Value with symbol and weight for ordering purposes.
 *
 * @author Dude
 * @since 02/2026
 */
public final class Value implements Comparable<Value> {

    private final int ascii;
    private int weight;

    private Value(int ascii) {
        this.ascii = ascii;
        this.weight = 0;
    }

    /** Build an array of Value from integer codes. */
    static Value[] arrayOf(Integer... values) {
        return stream(values)
                .map(Value::new)
                .toArray(Value[]::new);
    }

    /** @return the character symbol. */
    public Character getSymbol() {
        return (char) ascii;
    }

    /** Increase weight (used for ordering). */
    public Value increaseWeight() {
        weight++;
        return this;
    }

    @Override
    public int compareTo(Value other) {
        var comp = compare(weight, other.weight);

        if (comp == 0) {
            comp = compare(ascii, other.ascii);
        }

        return comp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Value value)) return false;
        return ascii == value.ascii && weight == value.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ascii, weight);
    }

    @Override
    public String toString() {
        return "Value{symbol='%s', ascii=%d, weight=%d}".formatted((char) ascii, ascii, weight);
    }
}
