package emprestes.game.sudoku.domain;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * Supported symbol sets for Sudoku (9 or 16 values) and generation helpers.
 *
 * @author Dude
 * @since 02/2026
 */
public enum GameSymbol {
    V9(49, 50, 51, 52, 53, 54, 55, 56, 57),
    V16(49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71);

    public static final Character BLANK = ' ';
    public static final Character BREAK = '\n';

    public final Byte size;
    private final Integer[] ascii;

    GameSymbol(Integer... ascii) {
        this.ascii = ascii;
        this.size = (byte) ascii.length;
    }

    /** Shuffle the symbols and return a stream of characters. */
    public List<Character> shuffle(Character... excluded) {
        var available = stream(ascii)
                .map(code -> (char) code.intValue())
                .filter(c -> stream(excluded).noneMatch(c::equals))
                .collect(toList());

        Collections.shuffle(available);

        return available;
    }

    /** Iterate all symbols in this dimension. */
    public void forEach(Consumer<Character> action) {
        stream(ascii)
                .map(code -> (char) code.intValue())
                .forEach(action);
    }
}
