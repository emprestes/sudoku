package emprestes.game.sudoku.domain.model;

import junitx.extensions.SerializabilityTestCase;

import java.io.Serializable;

public class SudokuColumnSerializabilityTest extends SerializabilityTestCase {

    public SudokuColumnSerializabilityTest(String name) {
        super(name);
    }

    @Override
    protected Serializable createInstance() {
        final byte number = 1;
        return new SudokuColumn(number);
    }
}
