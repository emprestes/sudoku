package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.PositionException;
import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Sudoku board contract: initialization, moves, and completion check.
 *
 * @author Dude
 * @since 02/2026
 */
public interface Board extends Serializable {

    /** @return board side length. */
    Byte getSide();

    /** Initialize regions/rows/columns applying the given action. */
    void init(Consumer<Region> action);

    /** Clear and fill the board with valid values. */
    void start();

    /** Make a move with value and coordinates (region, row, column). */
    void play(Character value, byte regionNumber, byte rowNumber, byte columnNumber) throws PositionException;

    /** Make a move on a given position. */
    void play(Character value, Position position) throws WrongPositionException;

    /** @return true if all positions are correctly filled. */
    boolean isGameOver();

    /** @return true if the game has not finished. */
    default boolean isNotGameOver() {
        return !isGameOver();
    }
}
