package emprestes.game.sudoku.domain.model;

import junitx.extensions.EqualsHashCodeTestCase;

public class SudokuRowEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public SudokuRowEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() {
        final byte number = 1;
        return new SudokuRow(number);
    }

    @Override
    protected Object createNotEqualInstance() {
        final byte number = 9;
        return new SudokuRow(number);
    }
}
