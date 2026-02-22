package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.IPosition;
import emprestes.game.sudoku.domain.IRow;
import emprestes.game.sudoku.domain.GameSymbol;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Concrete Row implementation.
 *
 * @author Dude
 * @since 02/2026
 */
final class SudokuRow implements IRow {

    @Serial
    private static final long serialVersionUID = 1961360384243495838L;

    private final byte number;

    private final List<IPosition> positions;

    SudokuRow(Byte number) {
        super();

        this.number = number;
        this.positions = new ArrayList<>();
    }

    @Override
    public Byte getNumber() {
        return number;
    }

    @Override
    public Character[] toArrayValues() {
        return positions.stream()
                .map(IPosition::getValue)
                .filter(value -> !GameSymbol.BLANK.equals(value))
                .toArray(Character[]::new);
    }

    @Override
    public void add(IPosition position) {
        positions.add(position);
    }

    @Override
    public List<IPosition> positionList() {
        return positions;
    }

    @Override
    public void forEach(Consumer<IPosition> action) {
        positions.forEach(action);
    }

    @Override
    public int getSizePositions() {
        return positions.size();
    }

    @Override
    public boolean isCompleted() {
        return positions.stream().allMatch(IPosition::isVisible);
    }

    @Override
    public boolean contains(Character value) {
        return positions.stream()
                .filter(IPosition::nonBlank)
                .map(IPosition::getValue)
                .anyMatch(_value -> _value.equals(value));
    }

    @Override
    public int compareTo(IRow other) {
        return getNumber().compareTo(other.getNumber());
    }

    @Override
    public boolean equals(byte number) {
        return this.number == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SudokuRow sudokuRow)) return false;
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
