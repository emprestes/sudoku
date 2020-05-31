package emprestes.game.sudoku.domain.model;

import junitx.extensions.EqualsHashCodeTestCase;

public class SudokuPositionEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public SudokuPositionEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() {
        final byte regionNumber = 1;
        final byte rowNumber = 2;
        final byte columnNumber = 3;

        return new SudokuPosition(regionNumber, rowNumber, columnNumber);
    }

    @Override
    protected Object createNotEqualInstance() {
        final byte regionNumber = 3;
        final byte rowNumber = 2;
        final byte columnNumber = 1;

        return new SudokuPosition(regionNumber, rowNumber, columnNumber);
    }
}
