package emprestes.game.sudoku.domain.model;

import junitx.extensions.EqualsHashCodeTestCase;

import static emprestes.game.sudoku.domain.Dimension.D3X3;
import static emprestes.game.sudoku.domain.Dimension.D4X4;

public class SudokuBoardEqualsHashCodeTest extends EqualsHashCodeTestCase {

    public SudokuBoardEqualsHashCodeTest(String name) {
        super(name);
    }

    @Override
    protected Object createInstance() {
        return new SudokuBoard(D3X3);
    }

    @Override
    protected Object createNotEqualInstance() {
        return new SudokuBoard(D4X4);
    }
}
