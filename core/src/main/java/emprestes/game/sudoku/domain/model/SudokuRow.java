package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Position;
import emprestes.game.sudoku.domain.Row;
import emprestes.game.sudoku.domain.SymbolValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

final class SudokuRow implements Row {

    private static final long serialVersionUID = 1961360384243495838L;

    private final byte number;

    private final List<Position> positions;

    SudokuRow(Byte number) {
        super();

        this.number = number;
        this.positions = new ArrayList<>();
    }

    @Override
    public boolean isBlank() {
        return positions.stream().allMatch(Position::isBlank);
    }

    @Override
    public Byte getNumber() {
        return number;
    }

    @Override
    public Character[] toArrayValues() {
        return positions.stream()
                .map(Position::getValue)
                .filter(value -> !SymbolValues.BLANK.equals(value))
                .toArray(Character[]::new);
    }

    @Override
    public void add(Position position) {
        positions.add(position);
    }

    @Override
    public void forEach(Consumer<Position> action) {
        positions.forEach(action);
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
                .filter(Position::nonBlank)
                .map(Position::getValue)
                .anyMatch(_value -> _value.equals(value));
    }

    @Override
    public int compareTo(Row other) {
        return getNumber().compareTo(other.getNumber());
    }

    @Override
    public boolean equals(byte number) {
        return this.number == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuRow)) return false;
        SudokuRow sudokuRow = (SudokuRow) o;
        return equals(sudokuRow.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Row: " + number;
    }
}
