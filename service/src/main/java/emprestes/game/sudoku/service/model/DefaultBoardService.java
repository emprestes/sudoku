package emprestes.game.sudoku.service.model;

import emprestes.game.sudoku.domain.Board;
import emprestes.game.sudoku.domain.InitRegion;
import emprestes.game.sudoku.domain.Position;
import emprestes.game.sudoku.domain.exception.PositionException;
import emprestes.game.sudoku.domain.exception.WrongPositionException;
import emprestes.game.sudoku.service.BoardService;

public final class DefaultBoardService implements BoardService {

    private final Board board;

    public DefaultBoardService(Board board) {
        super();

        this.board = board;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void start() {
        board.start();
    }

    @Override
    public void start(InitRegion action) {
        board.init(action);
    }

    @Override
    public void play(Character value, Byte region, Byte row, Byte column) throws PositionException {
        board.play(value, region, row, column);
    }

    @Override
    public void play(Character value, Position position) throws WrongPositionException {
        board.play(value, position);
    }

    @Override
    public boolean isNotGameOver() {
        return board.isNotGameOver();
    }
}
