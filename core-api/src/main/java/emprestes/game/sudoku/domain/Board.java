package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.PositionException;
import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Sudoku board contract, including initialization, moves, and visibility settings.
 *
 * @author Dude
 * @since 02/2026
 */
public interface Board extends Serializable {

    /** @return board side length. */
    Byte getSide();

    /** Initialize regions/rows/columns applying the provided action. */
    void init(Consumer<Region> action);

    /** Clear and fill the board with valid values. */
    void start();

    /** Make a move with value and coordinates (region, row, column). */
    void play(Character value, byte regionNumber, byte rowNumber, byte columnNumber) throws PositionException;

    /** Make a move on a previously retrieved position. */
    void play(Character value, Position position) throws WrongPositionException;

    /** @return true when all positions are correctly filled. */
    boolean isGameOver();

    /** @return true if the game has not finished yet. */
    default boolean isNotGameOver() {
        return !isGameOver();
    }

    /** Set board visibility level. */
    void setLevel(SudokuLevel level);

    /** @return current board visibility level. */
    SudokuLevel getLevel();
}
