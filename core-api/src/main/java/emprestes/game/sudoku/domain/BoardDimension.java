package emprestes.game.sudoku.domain;

public enum BoardDimension {
    D9X9(9, 3), D16X16(16, 3);

    public final Integer size;
    private final Integer multiplicity;

    BoardDimension(int size, int multiplicity) {
        this.size = size;
        this.multiplicity = multiplicity;
    }

    public Integer nextColumn(Integer column, Integer regionId) {
        return column + (size.equals(column) ? 0 : (regionId % multiplicity) * multiplicity);
    }

    public Integer nextRow(Integer row, Integer regionId) {
        return (regionId % multiplicity) == 0 ? (row + regionId) : row;
    }
}
