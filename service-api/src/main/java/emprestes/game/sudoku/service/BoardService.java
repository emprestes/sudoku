package emprestes.game.sudoku.service;

import emprestes.game.sudoku.domain.IBoard;
import emprestes.game.sudoku.domain.IPosition;
import emprestes.game.sudoku.domain.IRegion;
import emprestes.game.sudoku.domain.exception.PositionException;
import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.util.function.Consumer;

public interface BoardService {

    IBoard getBoard();

    Byte getSide();

    void start(Consumer<IRegion> action);

    void start();

    void play(Character value, byte region, byte row, byte column) throws PositionException;

    default void play(Character value, int region, int row, int column) throws PositionException {
        play(value, (byte) region, (byte) row, (byte) column);
    }

    void play(Character value, IPosition position) throws WrongPositionException;

    boolean isNotGameOver();
}
