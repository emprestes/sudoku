package emprestes.game.sudoku.app.swing;

import emprestes.game.sudoku.app.swing.view.GameView;
import emprestes.game.sudoku.domain.GameVisibility;
import emprestes.game.sudoku.domain.IBoard;
import emprestes.game.sudoku.domain.model.SudokuBoard;
import emprestes.game.sudoku.service.BoardService;
import emprestes.game.sudoku.service.model.DefaultBoardService;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Sudoku {

    private final JFrame gameView;

    Sudoku(IBoard board) {
        this(new DefaultBoardService(board));
    }

    Sudoku(BoardService service) {
        this(new GameView(service));
    }

    Sudoku(JFrame gameView) {
        super();
        this.gameView = gameView;
    }

    static Sudoku getInstance() {
        IBoard board = new SudokuBoard();
        board.setVisibility(GameVisibility.EASY);
        return new Sudoku(board);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Sudoku.getInstance()::show);
    }

    void show() {
        gameView.setVisible(true);
    }
}
