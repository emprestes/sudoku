package emprestes.game.sudoku.domain;

public enum BoardDimension {
    D9X9(9, 3), D16X16(16, 3);

    public final int size;
    private final int multiplicity;

    BoardDimension(int size, int multiplicity) {
        this.size = size;
        this.multiplicity = multiplicity;
    }

    public Integer nextColumn(Integer column, Integer regionId) {
        return column + (column == size ? 0 : (regionId % multiplicity) * multiplicity);
    }

    public Integer nextRow(Integer row, Integer regionId) {
        return regionId % multiplicity == 0 ? (row + regionId) : row;
    }
}
