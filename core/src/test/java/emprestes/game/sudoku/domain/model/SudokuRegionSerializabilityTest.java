package emprestes.game.sudoku.domain.model;

import junitx.extensions.SerializabilityTestCase;

import java.io.Serializable;

public class SudokuRegionSerializabilityTest extends SerializabilityTestCase {

    public SudokuRegionSerializabilityTest(String name) {
        super(name);
    }

    @Override
    protected Serializable createInstance() {
        final byte number = 1;
        return new SudokuRegion(number);
    }
}
