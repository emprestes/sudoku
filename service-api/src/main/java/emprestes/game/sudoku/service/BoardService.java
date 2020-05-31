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

    void play(Character value, Integer region, Integer row, Integer column) throws PositionException;

    void play(Character value, Position position) throws WrongPositionException;

    boolean isNotGameOver();
}
