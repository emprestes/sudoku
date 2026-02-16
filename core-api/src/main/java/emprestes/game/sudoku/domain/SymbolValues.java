package emprestes.game.sudoku.domain;

import java.util.function.Consumer;

import static java.util.Arrays.stream;

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

    public void forEach(Consumer<Character> consumer) {
        stream(ascii).map(Value::getSymbol).forEach(consumer);
    }
}
