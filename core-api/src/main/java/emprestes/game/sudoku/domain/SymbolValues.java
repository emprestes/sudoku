package emprestes.game.sudoku.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * Enum que define os conjuntos de símbolos permitidos para o Sudoku
 * (9x9 e 4x4 estendido para 16 símbolos), além de utilitários
 * de embaralhamento e geração de valores não utilizados.
 */
/**
 * Conjunto de símbolos permitidos por dimensão (ex.: 1-9 ou 1-9 + A-G).
 */
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

    /**
     * Retorna um fluxo embaralhado dos símbolos desta dimensão.
     */
    public Stream<Character> shuffle() {
        final List<Integer> available = stream(ascii).collect(toList());
        Collections.shuffle(available);
        return available.stream()
                .map(code -> (char) code.intValue());
    }

    /**
     * Itera sobre todos os símbolos desta dimensão.
     */
    public void forEach(Consumer<Character> action) {
        stream(ascii)
                .map(code -> (char) code.intValue())
                .forEach(action);
    }

    /**
     * Gera um símbolo que não está presente na lista informada.
     */
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
