package emprestes.game.sudoku.app.swing.component;

import emprestes.game.sudoku.app.swing.controller.ExitAction;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class GameMenuBar extends JMenuBar {

    private static final long serialVersionUID = -1619051870761232426L;

    private JMenu game;
    private JMenuItem exit;

    public GameMenuBar() {
        super();

        initComponents();
        initControllers();
        initView();
    }

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
