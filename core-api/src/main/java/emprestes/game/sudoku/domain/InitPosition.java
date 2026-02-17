package emprestes.game.sudoku.domain;

/**
 * Functional callback to assign an initial value to a position.
 *
 * @author Dude
 * @since 02/2026
 */
@FunctionalInterface
public interface InitPosition {

    /** Initialize the provided position. */
    void init(Position position);
}
