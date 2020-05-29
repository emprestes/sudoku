package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.WrongPositionException;

public interface Row {

    void add(Position position);

    void play(Integer number) throws WrongPositionException;
}
