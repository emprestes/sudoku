package emprestes.game.sudoku.domain.model;

import junitx.extensions.SerializabilityTestCase;

import java.io.Serializable;

public class SudokuRowSerializabilityTest extends SerializabilityTestCase {

    public SudokuRowSerializabilityTest(String name) {
        super(name);
    }

    @Override
    protected Serializable createInstance() {
        final byte number = 1;
        return new SudokuRow(number);
    }
}
