package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.WrongPositionException;

public interface Board {

    void init(InitRegion action);

    void start();

    void play(Integer number, Position position) throws WrongPositionException;

    boolean isGameOver();

    default boolean isNotGameOver() {
        return !isGameOver();
    }
}
