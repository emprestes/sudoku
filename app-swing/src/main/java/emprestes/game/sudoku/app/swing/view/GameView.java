package emprestes.game.sudoku.app.swing.view;

import emprestes.game.sudoku.app.swing.component.GameMenuBar;
import emprestes.game.sudoku.app.swing.controller.ExitAction;
import emprestes.game.sudoku.service.BoardService;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class GameView extends JFrame {

    private static final long serialVersionUID = 2337305186388467101L;

    private JMenuBar menu;
    private JPanel boardView;
    private JPanel valueListView;

    public GameView(BoardService service) {
        super("SUDOKU");

        initComponents(service);
        initControllers();
        initView();
        init();
    }

    private void initComponents(BoardService service) {
        this.menu = new GameMenuBar();
        this.boardView = new BoardView(service);
        this.valueListView = new ValueListView();
    }

    private void initControllers() {
        addWindowListener(new ExitAction(this));
    }

    private void initView() {
        setJMenuBar(menu);
        setLayout(new BorderLayout());
        add(boardView, BorderLayout.CENTER);
        add(valueListView, BorderLayout.NORTH);
    }

    private void init() {
        // FIXME Use dimension coming from service
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(Boolean.FALSE);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
