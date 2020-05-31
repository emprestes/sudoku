package emprestes.game.sudoku.domain.model;

import junitx.extensions.SerializabilityTestCase;

import java.io.Serializable;

public class SudokuBoardSerializabilityTest extends SerializabilityTestCase {

    public SudokuBoardSerializabilityTest(String name) {
        super(name);
    }

    @Override
    protected Serializable createInstance() {
        return new SudokuBoard();
    }
}
