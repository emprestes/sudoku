package emprestes.game.sudoku.service;

import emprestes.game.sudoku.domain.Board;
import emprestes.game.sudoku.domain.InitRegion;
import emprestes.game.sudoku.domain.Position;
import emprestes.game.sudoku.domain.exception.PositionException;
import emprestes.game.sudoku.domain.exception.WrongPositionException;

public interface BoardService {

    Board getBoard();

    void start(InitRegion action);

    void start();

    void play(Character value, Byte region, Byte row, Byte column) throws PositionException;

    default void play(Character value, Integer region, Integer row, Integer column) throws PositionException {
        play(value, region.byteValue(), row.byteValue(), column.byteValue());
    }

    void play(Character value, Position position) throws WrongPositionException;

    boolean isNotGameOver();
}
