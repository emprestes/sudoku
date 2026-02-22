package emprestes.game.sudoku.domain;

/**
 * Supported Sudoku difficulty levels.
 *
 * @author Dude
 * @since 02/2026
 */
public enum SudokuDifficulty {

    EASY(36, 45);

    private final int minVisiblePositions;
    private final int maxVisiblePositions;

    SudokuDifficulty(int minVisiblePositions, int maxVisiblePositions) {
        this.minVisiblePositions = minVisiblePositions;
        this.maxVisiblePositions = maxVisiblePositions;
    }

    public int getMinVisiblePositions() {
        return minVisiblePositions;
    }

    public int getMaxVisiblePositions() {
        return maxVisiblePositions;
    }
}
