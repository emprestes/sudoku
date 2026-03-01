package emprestes.game.sudoku.service.model;

import emprestes.game.sudoku.domain.IBoard;
import emprestes.game.sudoku.domain.IPosition;
import emprestes.game.sudoku.domain.IRegion;
import emprestes.game.sudoku.domain.exception.PositionException;
import emprestes.game.sudoku.domain.exception.WrongPositionException;
import emprestes.game.sudoku.service.BoardService;

import java.util.function.Consumer;

public final class DefaultBoardService implements BoardService {

    private final IBoard board;

    public DefaultBoardService(IBoard board) {
        super();

        this.board = board;
    }

    @Override
    public Byte getSide() {
        return board.getSide();
    }

    @Override
    public IBoard getBoard() {
        return board;
    }

    @Override
    public void start() {
        board.start();
    }

    @Override
    public void start(Consumer<IRegion> action) {
        board.init(action);
    }

    @Override
    public void play(Character value, byte region, byte row, byte column) throws PositionException {
        board.play(value, region, row, column);
    }

    @Override
    public void play(Character value, IPosition position) throws WrongPositionException {
        board.play(value, position);
    }

    @Override
    public boolean isNotGameOver() {
        return board.isNotGameOver();
    }
}
