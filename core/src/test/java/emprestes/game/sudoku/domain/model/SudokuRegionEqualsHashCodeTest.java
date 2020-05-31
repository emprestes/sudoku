package emprestes.game.sudoku.domain.model;

import junitx.extensions.EqualsHashCodeTestCase;

public class SudokuRegionEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public SudokuRegionEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() {
        final byte number = 1;
        return new SudokuRegion(number);
    }

    @Override
    protected Object createNotEqualInstance() {
        final byte number = 9;
        return new SudokuRegion(number);
    }
}
