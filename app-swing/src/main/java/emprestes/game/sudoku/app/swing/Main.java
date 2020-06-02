package emprestes.game.sudoku.app.swing;

import emprestes.game.sudoku.app.swing.view.GameView;
import emprestes.game.sudoku.domain.Board;
import emprestes.game.sudoku.domain.model.SudokuBoard;
import emprestes.game.sudoku.service.BoardService;
import emprestes.game.sudoku.service.model.DefaultBoardService;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    private final JFrame gameView;

    Main() {
        this(new SudokuBoard());
    }

    Main(Board board) {
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
        return new Main();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main.getInstance()::show);
    }

    void show() {
        gameView.setVisible(true);
    }
}
