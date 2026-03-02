package emprestes.game.sudoku.app.swing.component;

import emprestes.game.sudoku.app.swing.controller.GameController;

import javax.swing.JButton;
import java.awt.Font;

import static java.lang.String.valueOf;

/**
 * A button in the value selection panel.
 * Clicking plays the value on the currently selected position.
 */
public class ValueButton extends JButton {

    private static final long serialVersionUID = -3047182538082322862L;
    private static final Font VALUE_FONT = new Font("SansSerif", Font.BOLD, 16);

    private final Character value;

    public ValueButton(Character value, GameController controller) {
        super(valueOf(value));
        this.value = value;
        setFont(VALUE_FONT);
        addActionListener(e -> controller.playValue(value));
    }
}
