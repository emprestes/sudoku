package emprestes.game.sudoku.app.swing.controller;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;

public class ExitAction extends WindowAdapter implements ActionListener {

    private final Component component;

    public ExitAction(Component component) {
        super();

        this.component = component;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        close(event);
    }

    @Override
    public void windowClosing(WindowEvent event) {
        close(event);
    }

    private void close(AWTEvent event) {
        var option = showConfirmDialog(component, "Do you want to leave?", "SUDOKU", YES_NO_OPTION);
        if (option == YES_OPTION) {
            System.exit(0);
        }
    }
}