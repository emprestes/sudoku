package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Position;
import junitx.extensions.ComparabilityTestCase;

public class SudokuPositionComparabilityTest extends ComparabilityTestCase {

    public SudokuPositionComparabilityTest(String name) {
        super(name);
    }

    @Override
    protected Comparable<Position> createLessInstance() {
        final byte regionNumber = 1;
        final byte rowNumber = 1;
        final byte columnNumber = 1;

        return new SudokuPosition(regionNumber, rowNumber, columnNumber);
    }

    @Override
    protected Comparable<Position> createEqualInstance() {
        final byte regionNumber = 5;
        final byte rowNumber = 5;
        final byte columnNumber = 5;

        return new SudokuPosition(regionNumber, rowNumber, columnNumber);
    }

    @Override
    protected Comparable<Position> createGreaterInstance() {
        final byte regionNumber = 9;
        final byte rowNumber = 9;
        final byte columnNumber = 9;

        return new SudokuPosition(regionNumber, rowNumber, columnNumber);
    }
}
