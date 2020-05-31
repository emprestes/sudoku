package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Column;
import emprestes.game.sudoku.domain.Position;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.hash;

final class SudokuColumn implements Column {

    private static final long serialVersionUID = -6502322659899442632L;

    private final byte number;
    private final List<Position> positions;

    SudokuColumn(Byte number) {
        super();

        this.number = number;
        this.positions = new ArrayList<>();
    }

    public Byte getNumber() {
        return number;
    }

    @Override
    public void add(Position position) {
        positions.add(position);
    }

    @Override
    public int getSizePositions() {
        return positions.size();
    }

    @Override
    public boolean isCompleted() {
        return positions.stream().allMatch(Position::isVisible);
    }

    @Override
    public boolean contains(Character value) {
        return positions.stream()
                .map(Position::getValue)
                .anyMatch(_value -> _value.equals(value));
    }

    @Override
    public int compareTo(Column other) {
        return getNumber().compareTo(other.getNumber());
    }

    @Override
    public boolean equals(byte number) {
        return this.number == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuColumn)) return false;
        SudokuColumn sudokuColumn = (SudokuColumn) o;
        return equals(sudokuColumn.number);
    }

    @Override
    public int hashCode() {
        return hash(number);
    }

    @Override
    public String toString() {
        return "Column: " + number;
    }
}
