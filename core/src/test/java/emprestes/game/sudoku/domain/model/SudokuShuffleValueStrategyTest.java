package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.GameDimension;
import emprestes.game.sudoku.domain.IPosition;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SudokuShuffleValueStrategyTest {

    @Test
    public void shuffleShouldFillBoardAndHideAllWhenVisibilityIsFalse() {
        var board = new SudokuBoard(GameDimension.D3X3);
        var positions = getPositions(board);
        var strategy = new SudokuShuffleValueStrategy();

        strategy.shuffle(positions, GameDimension.D3X3.symbols::shuffle, () -> false);

        assertTrue(positions.stream().allMatch(IPosition::nonBlank));
        assertTrue(positions.stream().noneMatch(IPosition::isVisible));
    }

    @Test
    public void shuffleShouldFillBoardAndShowAllWhenVisibilityIsTrue() {
        var board = new SudokuBoard(GameDimension.D3X3);
        var positions = getPositions(board);
        var strategy = new SudokuShuffleValueStrategy();

        strategy.shuffle(positions, GameDimension.D3X3.symbols::shuffle, () -> true);

        assertTrue(positions.stream().allMatch(IPosition::nonBlank));
        assertTrue(positions.stream().allMatch(IPosition::isVisible));
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
