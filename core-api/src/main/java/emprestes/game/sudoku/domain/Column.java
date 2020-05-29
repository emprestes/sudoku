package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.WrongPositionException;

public interface Column {

    void add(Position position);

    void play(Integer number) throws WrongPositionException;
}
