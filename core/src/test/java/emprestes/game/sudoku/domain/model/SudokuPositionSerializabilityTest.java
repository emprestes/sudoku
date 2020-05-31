package emprestes.game.sudoku.domain.model;

import junitx.extensions.SerializabilityTestCase;

import java.io.Serializable;

public class SudokuPositionSerializabilityTest extends SerializabilityTestCase {

    public SudokuPositionSerializabilityTest(String name) {
        super(name);
    }

    @Override
    protected Serializable createInstance() {
        final byte regionNumber = 1;
        final byte rowNumber = 2;
        final byte columnNumber = 3;

        return new SudokuPosition(regionNumber, rowNumber, columnNumber);
    }
}
