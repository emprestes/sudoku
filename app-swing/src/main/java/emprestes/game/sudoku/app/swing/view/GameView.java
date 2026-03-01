package emprestes.game.sudoku.app.swing.view;

import emprestes.game.sudoku.app.swing.component.GameMenuBar;
import emprestes.game.sudoku.app.swing.controller.ExitAction;
import emprestes.game.sudoku.app.swing.controller.GameController;
import emprestes.game.sudoku.service.BoardService;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import static javax.swing.SwingConstants.CENTER;

public class GameView extends JFrame {

    private static final long serialVersionUID = 2337305186388467101L;

    private JMenuBar menu;
    private JPanel boardView;
    private JPanel valueListView;
    private JLabel statusLabel;

    public GameView(BoardService service) {
        super("SUDOKU");

        GameController controller = new GameController(service);

        initComponents(controller);
        initControllers(controller);
        initView();
        init();
    }

    private void initComponents(GameController controller) {
        this.menu = new GameMenuBar();
        this.boardView = new BoardView(controller);
        this.valueListView = new ValueListView(controller);
        this.statusLabel = new JLabel("Select an empty cell, then pick a value.");
        statusLabel.setHorizontalAlignment(CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        statusLabel.setForeground(Color.DARK_GRAY);
    }

    private void initControllers(GameController controller) {
        addWindowListener(new ExitAction(this));

        controller.onSelectionChanged(pos -> {
            if (pos != null) {
                statusLabel.setText("Cell selected — choose a value above.");
                statusLabel.setForeground(new Color(0, 90, 180));
            } else {
                statusLabel.setText("Select an empty cell, then pick a value.");
                statusLabel.setForeground(Color.DARK_GRAY);
            }
        });

        controller.onGameOver(() -> {
            statusLabel.setText("🎉 Congratulations! Puzzle complete!");
            statusLabel.setForeground(new Color(0, 150, 0));
            JOptionPane.showMessageDialog(this,
                    "Congratulations! You solved the puzzle!",
                    "SUDOKU — Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        controller.onError(message -> {
            statusLabel.setText("Invalid move: " + message);
            statusLabel.setForeground(Color.RED);
        });
    }

    private void initView() {
        setJMenuBar(menu);
        setLayout(new BorderLayout());
        add(valueListView, BorderLayout.NORTH);
        add(boardView, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void init() {
        setSize(700, 750);
        setLocationRelativeTo(null);
        setResizable(Boolean.FALSE);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
