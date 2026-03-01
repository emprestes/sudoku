package emprestes.game.sudoku.app.swing.component;

import emprestes.game.sudoku.app.swing.controller.ExitAction;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

public class GameMenuBar extends JMenuBar {

    private static final long serialVersionUID = -1619051870761232426L;

    private JMenu game;
    private JMenu help;
    private JMenuItem newGame;
    private JMenuItem save;
    private JMenuItem exit;
    private JMenuItem learn;
    private JMenuItem about;

    public GameMenuBar() {
        super();

        initComponents();
        initControllers();
        initView();
    }

    private void initComponents() {
        this.game = new JMenu("Game");
        this.game.setMnemonic(KeyEvent.VK_G);

        this.help = new JMenu("Help");
        this.help.setMnemonic(KeyEvent.VK_H);

        this.newGame = new JMenuItem("New Game...");
        this.newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, CTRL_DOWN_MASK));

        this.save = new JMenuItem("Save...");
        this.save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, CTRL_DOWN_MASK));

        this.exit = new JMenuItem("Exit");
        this.exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, CTRL_DOWN_MASK));

        this.learn = new JMenuItem("Learn");
        this.learn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, CTRL_DOWN_MASK));

        this.about = new JMenuItem("About");
    }

    private void initControllers() {
        exit.addActionListener(new ExitAction(getRootPane()));
        about.addActionListener(e -> JOptionPane.showMessageDialog(
                getRootPane(), "Sudoku v1.0", "About", JOptionPane.INFORMATION_MESSAGE));
    }

    private void initView() {
        game.add(newGame);
        game.add(save);
        game.add(new JSeparator());
        game.add(exit);

        help.add(learn);
        help.add(about);

        add(game);
        add(help);
    }
}
