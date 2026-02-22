package emprestes.game.sudoku.domain;

/**
 * Functional callback to initialize a region when building the board.
 *
 * @author Dude
 * @since 02/2026
 */
@FunctionalInterface
public interface IInitRegion {

    /** Initialize the provided region. */
    void init(IRegion region);
}
