package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Dimension;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuBoardTest {

    private SudokuBoard board;

    @Test
    public void start3x3Test() {
        board = new SudokuBoard(Dimension.D3X3);

        board.start();

        assertEquals(81, board.getSizePositions());
        assertEquals(9, board.getSizeRegions());
        assertEquals(9, board.getSizeRows());
        assertEquals(9, board.getSizeColumns());
        assertTrue(board.isNotGameOver());
    }

    @Test
    public void start4x4Test() {
        board = new SudokuBoard(Dimension.D4X4);

        board.start();

        assertEquals(256, board.getSizePositions());
        assertEquals(16, board.getSizeRegions());
        assertEquals(16, board.getSizeRows());
        assertEquals(16, board.getSizeColumns());
        assertTrue(board.isNotGameOver());
    }
}
