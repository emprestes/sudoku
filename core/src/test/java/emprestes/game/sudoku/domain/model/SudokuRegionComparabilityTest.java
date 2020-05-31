package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Region;
import junitx.extensions.ComparabilityTestCase;

public class SudokuRegionComparabilityTest extends ComparabilityTestCase {

    public SudokuRegionComparabilityTest(String name) {
        super(name);
    }

    @Override
    protected Comparable<Region> createLessInstance() {
        final byte number = 1;
        return new SudokuRegion(number);
    }

    @Override
    protected Comparable<Region> createEqualInstance() {
        final byte number = 5;
        return new SudokuRegion(number);
    }

    @Override
    protected Comparable<Region> createGreaterInstance() {
        final byte number = 9;
        return new SudokuRegion(number);
    }
}
