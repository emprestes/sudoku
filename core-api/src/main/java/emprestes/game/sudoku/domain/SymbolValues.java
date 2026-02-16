package emprestes.game.sudoku.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * Supported symbol sets for Sudoku (9 or 16 values) and generation helpers.
 *
 * @author Dude
 * @since 02/2026
 */
public enum SymbolValues {
    V9(49, 50, 51, 52, 53, 54, 55, 56, 57),
    V16(49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71);

    public static final Character BLANK = ' ';

    public final Byte size;
    private final Value[] ascii;

    SymbolValues(Integer... ascii) {
        this(Value.arrayOf(ascii));
    }

    SymbolValues(Value... ascii) {
        this.ascii = ascii;
        this.size = (byte) ascii.length;
    }

    /** Shuffle the symbols and return a stream of characters. */
    public Stream<Character> shuffle() {
        final List<Value> available = stream(ascii).collect(toList());
        Collections.shuffle(available);
        return available.stream().map(Value::getSymbol);
    }

    /** Iterate all symbols in this dimension. */
    public void forEach(Consumer<Character> action) {
        stream(ascii).map(Value::getSymbol).forEach(action);
    }

    /** Generate a symbol that is not in the provided list. */
    public Character generateNotIn(Character... values) {
        return generateNotIn(stream(values)
                .filter(Objects::nonNull)
                .map(value -> (int) value)
                .toArray(Integer[]::new));
    }

    private Character generateNotIn(Integer... values) {
        final Character[] possibleSymbols = stream(ascii)
                .map(Value::getSymbol)
                .filter(code -> stream(values).noneMatch(value -> value.equals((int) code)))
                .toArray(Character[]::new);
        final int index = (int) (Math.random() * possibleSymbols.length);
        return possibleSymbols[index];
    }
}
