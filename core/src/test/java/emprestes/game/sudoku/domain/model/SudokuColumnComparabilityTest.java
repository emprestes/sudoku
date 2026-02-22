package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.IColumn;
import junitx.extensions.ComparabilityTestCase;

public class SudokuColumnComparabilityTest extends ComparabilityTestCase {

    public SudokuColumnComparabilityTest(String name) {
        super(name);
    }

    @Override
    protected Comparable<IColumn> createLessInstance() {
        final byte number = 1;
        return new SudokuColumn(number);
    }

    @Override
    protected Comparable<IColumn> createEqualInstance() {
        final byte number = 5;
        return new SudokuColumn(number);
    }

    @Override
    protected Comparable<IColumn> createGreaterInstance() {
        final byte number = 9;
        return new SudokuColumn(number);
    }
}
