package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Row;
import junitx.extensions.ComparabilityTestCase;

public class SudokuRowComparabilityTest extends ComparabilityTestCase {

    public SudokuRowComparabilityTest(String name) {
        super(name);
    }

    @Override
    protected Comparable<Row> createLessInstance() {
        final byte number = 1;
        return new SudokuRow(number);
    }

    @Override
    protected Comparable<Row> createEqualInstance() {
        final byte number = 5;
        return new SudokuRow(number);
    }

    @Override
    protected Comparable<Row> createGreaterInstance() {
        final byte number = 9;
        return new SudokuRow(number);
    }
}
