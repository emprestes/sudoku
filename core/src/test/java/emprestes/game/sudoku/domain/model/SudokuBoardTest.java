package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.GameDimension;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SudokuBoardTest {

    private SudokuBoard board;

    @Test
    public void start3x3Test() {
        board = new SudokuBoard(GameDimension.D3X3);

        board.start();
        System.out.println(board);

        assertEquals(81, board.getSizePositions());
        assertEquals(9, board.getSizeRegions());
        assertEquals(9, board.getSizeRows());
        assertEquals(9, board.getSizeColumns());
        assertTrue(board.isNotGameOver());
    }

    @Test
    public void start4x4Test() {
        board = new SudokuBoard(GameDimension.D4X4);

        board.start();
        System.out.println(board);

        assertEquals(256, board.getSizePositions());
        assertEquals(16, board.getSizeRegions());
        assertEquals(16, board.getSizeRows());
        assertEquals(16, board.getSizeColumns());
        assertTrue(board.isNotGameOver());
    }

}
