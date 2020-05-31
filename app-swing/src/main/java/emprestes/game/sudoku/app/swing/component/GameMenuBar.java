package emprestes.game.sudoku.app.swing.component;

import emprestes.game.sudoku.app.swing.controller.ExitAction;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

// Inheritance
public class GameMenuBar extends JMenuBar {

    // What is it?
    private static final long serialVersionUID = -1619051870761232426L;

    // State
    private JMenu game;
    private JMenuItem exit;

    // Default constructor
    public GameMenuBar() {
        super();

        initComponents();
        initControllers();
        initView();
    }

    // Behaviour
    private void initComponents() {
        this.game = new JMenu("Game");
        this.exit = new JMenuItem("Exit");
    }

    private void initControllers() {
        exit.addActionListener(new ExitAction(getRootPane()));
    }

    private void initView() {
        game.add(new JSeparator());
        game.add(exit);

        add(game);
    }
}
