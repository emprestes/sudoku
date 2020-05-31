package emprestes.game.sudoku.domain.model;

import junitx.extensions.EqualsHashCodeTestCase;

public class SudokuColumnEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public SudokuColumnEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() {
        final byte number = 1;
        return new SudokuColumn(number);
    }

    @Override
    protected Object createNotEqualInstance() {
        final byte number = 9;
        return new SudokuColumn(number);
    }
}
