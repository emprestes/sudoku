package emprestes.game.sudoku.app.swing;

import emprestes.game.sudoku.app.swing.view.GameView;
import emprestes.game.sudoku.domain.GameVisibility;
import emprestes.game.sudoku.domain.IBoard;
import emprestes.game.sudoku.domain.model.SudokuBoard;
import emprestes.game.sudoku.service.BoardService;
import emprestes.game.sudoku.service.model.DefaultBoardService;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    private final JFrame gameView;

    Main(IBoard board) {
        this(new DefaultBoardService(board));
    }

    Main(BoardService service) {
        this(new GameView(service));
    }

    Main(JFrame gameView) {
        super();
        this.gameView = gameView;
    }

    static Main getInstance() {
        IBoard board = new SudokuBoard();
        board.setVisibility(GameVisibility.EASY);
        return new Main(board);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main.getInstance()::show);
    }

    void show() {
        gameView.setVisible(true);
    }
}
