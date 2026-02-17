package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Dimension;
import emprestes.game.sudoku.domain.SudokuLevel;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SudokuBoardVisibilityTest {

    @Test
    public void shouldRespectVisibilityPercentsFor3x3() {
        SudokuBoard board = new SudokuBoard(Dimension.D3X3);
        int total = 81;
        Map<SudokuLevel, Double> ratios = Map.of(
                SudokuLevel.EASY, 0.47,
                SudokuLevel.MEDIUM, 0.50,
                SudokuLevel.HARD, 0.40,
                SudokuLevel.EXPERT, 0.35,
                SudokuLevel.MASTER, 0.30,
                SudokuLevel.EXTREME, 0.25
        );

        ratios.forEach((level, ratio) -> {
            board.setLevel(level);
            board.start();

            int expected = Math.max(1, (int) Math.round(total * ratio));
            int visible = board.getVisibleCount();
            assertEquals("Visible count for " + level, expected, visible);

            String rendered = board.toString();
            long printed = rendered.chars().filter(Character::isLetterOrDigit).count();
            assertEquals("Rendered chars for " + level, visible, printed);
        });
    }

    @Test
    public void shouldRespectVisibilityFor4x4() {
        SudokuBoard board = new SudokuBoard(Dimension.D4X4);
        board.setLevel(SudokuLevel.EXPERT);
        board.start();

        int total = 256;
        int expected = Math.max(1, (int) Math.round(total * SudokuLevel.EXPERT.visibilityRatio()));
        int visible = board.getVisibleCount();
        assertEquals(expected, visible);
        assertTrue(visible > 0);
    }
}
