package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.PositionException;
import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.io.Serializable;

public interface Board extends Serializable {

    void init(InitRegion action);

    void start();

    void play(Character value, byte regionNumber, byte rowNumber, byte columnNumber) throws PositionException;

    void play(Character value, Position position) throws WrongPositionException;

    boolean isGameOver();

    default boolean isNotGameOver() {
        return !isGameOver();
    }
}
