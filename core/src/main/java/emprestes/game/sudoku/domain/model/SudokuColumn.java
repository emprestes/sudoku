package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.IColumn;
import emprestes.game.sudoku.domain.IPosition;
import emprestes.game.sudoku.domain.GameSymbol;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.hash;

/**
 * Concrete Column implementation.
 *
 * @author Dude
 * @since 02/2026
 */
final class SudokuColumn implements IColumn {

    @Serial
    private static final long serialVersionUID = -6502322659899442632L;

    private final byte number;
    private final List<IPosition> positions;

    SudokuColumn(Byte number) {
        super();

        this.number = number;
        this.positions = new ArrayList<>();
    }

    @Override
    public boolean isBlank() {
        return positions.stream().allMatch(IPosition::isBlank);
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
    public int compareTo(IColumn other) {
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
