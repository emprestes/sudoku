package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.GameDimension;
import emprestes.game.sudoku.domain.IPosition;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static emprestes.game.sudoku.domain.GameDimension.D3X3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
        assertAllPositionsFilled(board);
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
        assertAllPositionsFilled(board);
        assertTrue(board.isNotGameOver());
    }

    @Test
    public void printSpacingShouldKeepSpaceAfterRegionSeparator() {
        board = new SudokuBoard(GameDimension.D3X3);
        board.start();

        var lines = board.toString().lines()
                .filter(line -> line.startsWith("|"))
                .toList();

        assertFalse(lines.isEmpty());
        assertTrue(lines.stream().noneMatch(line -> line.matches(".*\\|\\S.*")));
    }

    @Test
    public void easyLevelShouldStartWithVisiblePositionsInExpectedRange() {
        board = new SudokuBoard(D3X3);

        board.start();

        int visiblePositions = getPositions(board).stream()
                .filter(IPosition::isVisible)
                .mapToInt(_position -> 1)
                .sum();

        assertTrue(visiblePositions >= 36);
        assertTrue(visiblePositions <= 45);
    }

    @Test
    public void easyLevelMaskShouldVaryAcrossLoads() {
        board = new SudokuBoard(D3X3);

        board.start();
        var firstMask = getPositions(board).stream()
                .map(IPosition::isVisible)
                .toList();

        board.start();
        var secondMask = getPositions(board).stream()
                .map(IPosition::isVisible)
                .toList();

        assertNotEquals(firstMask, secondMask);
    }

    private void assertAllPositionsFilled(SudokuBoard board) {
        assertTrue(getPositions(board).stream().allMatch(IPosition::nonBlank));
    }

    @SuppressWarnings("unchecked")
    private List<IPosition> getPositions(SudokuBoard board) {
        try {
            Field field = SudokuBoard.class.getDeclaredField("positionList");
            field.setAccessible(true);

            return (List<IPosition>) field.get(board);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Could not inspect board positions", e);
        }
    }
}
