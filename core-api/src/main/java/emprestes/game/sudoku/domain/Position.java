package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.WrongPositionException;

public interface Position {

    void play(Integer number) throws WrongPositionException;

    boolean isEmpty();

    default boolean isNotEmpty() {
        return !isEmpty();
    }
}
