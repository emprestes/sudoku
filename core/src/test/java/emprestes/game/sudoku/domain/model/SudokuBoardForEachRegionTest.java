package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.GameDimension;
import emprestes.game.sudoku.domain.IRegion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SudokuBoardForEachRegionTest {

    @Test
    public void forEachRegionD3X3Test() {
        final SudokuBoard board = new SudokuBoard(GameDimension.D3X3);
        final List<IRegion> regions = new ArrayList<>();

        board.forEachRegion(regions::add);

        assertEquals(9, regions.size());
    }

    @Test
    public void forEachRegionD4X4Test() {
        final SudokuBoard board = new SudokuBoard(GameDimension.D4X4);
        final List<IRegion> regions = new ArrayList<>();

        board.forEachRegion(regions::add);

        assertEquals(16, regions.size());
    }
}
