package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.GameDimension;
import emprestes.game.sudoku.domain.IPosition;
import emprestes.game.sudoku.domain.IRegion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SudokuRegionForEachTest {

    @Test
    public void forEachPositionsCountD3X3Test() {
        final SudokuBoard board = new SudokuBoard(GameDimension.D3X3);
        final List<IRegion> regions = new ArrayList<>();

        board.init(regions::add);

        assertEquals(9, regions.size());
        regions.forEach(region -> {
            final List<IPosition> positions = new ArrayList<>();

            region.forEach(positions::add);

            assertEquals(9, positions.size());
            assertTrue(positions.stream().allMatch(position -> position.getRegion().equals(region.getNumber())));
        });
    }

    @Test
    public void forEachPositionsCountD4X4Test() {
        final SudokuBoard board = new SudokuBoard(GameDimension.D4X4);
        final List<IRegion> regions = new ArrayList<>();

        board.init(regions::add);

        assertEquals(16, regions.size());
        regions.forEach(region -> {
            final List<IPosition> positions = new ArrayList<>();

            region.forEach(positions::add);

            assertEquals(16, positions.size());
            assertTrue(positions.stream().allMatch(position -> position.getRegion().equals(region.getNumber())));
        });
    }
}
