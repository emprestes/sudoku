package emprestes.game.sudoku.app.swing.component;

import javax.swing.JButton;

import static java.lang.String.valueOf;

public class ValueButton extends JButton {

    private static final long serialVersionUID = -3047182538082322862L;

    public ValueButton(Character value) {
        this(valueOf(value));
    }

    public ValueButton(String value) {
        super(value);
    }
}
