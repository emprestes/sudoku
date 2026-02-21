package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Dimension;
import emprestes.game.sudoku.domain.Position;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuBoardTest {

    private SudokuBoard board;

    @Test
    public void start3x3Test() {
        board = new SudokuBoard(Dimension.D3X3);

        board.start();
        System.out.println(board);

        assertEquals(81, board.getSizePositions());
        assertEquals(9, board.getSizeRegions());
        assertEquals(9, board.getSizeRows());
        assertEquals(9, board.getSizeColumns());
        assertAllPositionsFilled(board);
        assertTrue(board.isNotGameOver());
    }

    @Test
    public void start4x4Test() {
        board = new SudokuBoard(Dimension.D4X4);

        board.start();
        System.out.println(board);

        assertEquals(256, board.getSizePositions());
        assertEquals(16, board.getSizeRegions());
        assertEquals(16, board.getSizeRows());
        assertEquals(16, board.getSizeColumns());
        assertAllPositionsFilled(board);
        assertTrue(board.isNotGameOver());
    }

    @Test
    public void printSpacingShouldKeepSpaceAfterRegionSeparator() {
        board = new SudokuBoard(Dimension.D3X3);
        board.start();

        var lines = board.toString().lines()
                .filter(line -> line.startsWith("|"))
                .toList();

        assertFalse(lines.isEmpty());
        assertTrue(lines.stream().noneMatch(line -> line.matches(".*\\|\\S.*")));
    }

    @SuppressWarnings("unchecked")
    private void assertAllPositionsFilled(SudokuBoard board) {
        try {
            Field field = SudokuBoard.class.getDeclaredField("positionList");
            field.setAccessible(true);

            List<Position> positions = (List<Position>) field.get(board);
            assertTrue(positions.stream().allMatch(Position::nonBlank));
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Could not validate generated board values", e);
        }
    }
}
