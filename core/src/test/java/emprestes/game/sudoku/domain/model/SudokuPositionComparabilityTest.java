package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.IPosition;
import junitx.extensions.ComparabilityTestCase;

public class SudokuPositionComparabilityTest extends ComparabilityTestCase {

    public SudokuPositionComparabilityTest(String name) {
        super(name);
    }

    @Override
    protected Comparable<IPosition> createLessInstance() {
        final byte regionNumber = 1;
        final byte rowNumber = 1;
        final byte columnNumber = 1;

        return new SudokuPosition(regionNumber, rowNumber, columnNumber);
    }

    @Override
    protected Comparable<IPosition> createEqualInstance() {
        final byte regionNumber = 5;
        final byte rowNumber = 5;
        final byte columnNumber = 5;

        return new SudokuPosition(regionNumber, rowNumber, columnNumber);
    }

    @Override
    protected Comparable<IPosition> createGreaterInstance() {
        final byte regionNumber = 9;
        final byte rowNumber = 9;
        final byte columnNumber = 9;

        return new SudokuPosition(regionNumber, rowNumber, columnNumber);
    }
}
