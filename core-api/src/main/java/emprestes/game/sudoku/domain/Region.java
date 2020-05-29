package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.WrongPositionException;

public interface Region {

    void init(InitPosition action);

    Column createColumn(Integer number);

    Row createRow(Integer number);

    void play(Integer number) throws WrongPositionException;
}
