package emprestes.game.sudoku.domain;

/**
 * Functional callback to initialize a region when building the board.
 *
 * @author Dude
 * @since 02/2026
 */
@FunctionalInterface
public interface InitRegion {

    /** Initialize the provided region. */
    void init(Region region);
}
