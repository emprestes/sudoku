package emprestes.game.sudoku.domain;

public enum Dimension {
    D3X3(3, 49, 50, 51, 52, 53, 54, 55, 56, 57),
    D4X4(4, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71);

    private static final byte ZERO = 0;
    private static final byte ONE = 1;

    public final Byte size;
    private final Byte multiplicity;
    private final int[] ascii;

    Dimension(int multiplicity, int... ascii) {
        this.multiplicity = (byte) multiplicity;
        this.size = (byte) ascii.length;
        this.ascii = ascii;
    }

    public Byte from() {
        return 1;
    }

    public Byte to() {
        return multiplicity;
    }

    public Byte generateIndex() {
        return generate(1, size);
    }

    public boolean generateVisibility() {
        byte index = generateIndex();
        return index % 2 == 0;
    }

    public Character generateValue() {
        return (char) ascii[generate(0, size)];
    }

    private byte generate(int from, int to) {
        return (byte) (from + Math.random() * to);
    }

    public Byte nextFromColumn(Byte column, Byte regionNumber) {
        if (size.equals(column)) {
            return ONE;
        }

        return (byte) (ONE + ((regionNumber % multiplicity) * multiplicity));
    }

    public Byte nextFromRow(Byte row, Byte regionNumber) {
        if ((regionNumber % multiplicity) == ZERO) {
            return (byte) (regionNumber + ((row % multiplicity)));
        }

        return row;
    }

    public Byte nextTo(Byte value) {
        return (byte) (value + multiplicity - 1);
    }
}
