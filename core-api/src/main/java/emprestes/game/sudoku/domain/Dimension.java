package emprestes.game.sudoku.domain;

import static emprestes.game.sudoku.domain.SymbolValues.V16;
import static emprestes.game.sudoku.domain.SymbolValues.V9;

/**
 * Dimensões suportadas do Sudoku (ex.: 3x3 = 9x9, 4x4 = 16x16).
 *
 * @author Dude
 * @since 02/2026
 */
public enum Dimension {
    D3X3(3, V9),
    D4X4(4, V16);

    private static final byte ZERO = 0;
    private static final byte ONE = 1;

    public final Byte size;
    public final Byte side;
    public final SymbolValues symbols;

    Dimension(int side, SymbolValues symbols) {
        this.side = (byte) side;
        this.size = symbols.size;
        this.symbols = symbols;
    }

    public Byte from() {
        return 1;
    }

    public Byte to() {
        return side;
    }

    public Byte nextFromColumn(Byte column, Byte regionNumber) {
        if (symbols.size.equals(column)) {
            return ONE;
        }

        return (byte) (ONE + ((regionNumber % side) * side));
    }

    public Byte nextFromRow(Byte row, Byte regionNumber) {
        if ((regionNumber % side) == ZERO) {
            return (byte) (regionNumber + (row % side));
        }

        return row;
    }

    public Byte nextTo(Byte value) {
        return (byte) (value + side - 1);
    }
}
