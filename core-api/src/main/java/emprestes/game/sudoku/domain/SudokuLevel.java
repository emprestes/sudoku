package emprestes.game.sudoku.domain;

/**
 * Níveis de visibilidade do Sudoku.
 *
 * @author Dude
 * @since 02/2026
 */
public enum SudokuLevel {
    EASY(0.65),
    MEDIUM(0.50),
    HARD(0.40),
    EXPERT(0.35),
    MASTER(0.30),
    EXTREME(0.25);

    private final double visibilityRatio;

    SudokuLevel(double visibilityRatio) {
        this.visibilityRatio = visibilityRatio;
    }

    public double visibilityRatio() {
        return visibilityRatio;
    }
}
