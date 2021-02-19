package emprestes.game.sudoku.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public enum SymbolValues {
    V9(49, 50, 51, 52, 53, 54, 55, 56, 57),
    V16(49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71);

    public static final Character BLANK = ' ';

    public final Byte size;
    private final Integer[] ascii;

    SymbolValues(Integer... ascii) {
        this.ascii = ascii;
        this.size = (byte) ascii.length;
    }

    public Stream<Character> shuffle( ) {
        final List<Integer> available = stream(ascii).collect(toList());
        Collections.shuffle(available);
        return available.stream()
                .map(code -> (char) code.intValue());
    }

    public void forEach(Consumer<Character> action) {
        stream(ascii)
                .map(code -> (char) code.intValue())
                .forEach(action);
    }

    public Character generateNotIn(Character... values) {
        return generateNotIn(stream(values)
                .filter(Objects::nonNull)
                .map(value -> (int) value)
                .toArray(Integer[]::new));
    }

    private Character generateNotIn(Integer... values) {
        final Character[] possibleSymbols = stream(ascii)
                .filter(code -> stream(values).noneMatch(value -> value.equals(code)))
                .map(code -> (char) code.intValue())
                .toArray(Character[]::new);
        final int index = (int) (Math.random() * possibleSymbols.length);

        return possibleSymbols[index];
    }
}
