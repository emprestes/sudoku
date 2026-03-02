package emprestes.game.sudoku.app.swing.component;

import emprestes.game.sudoku.app.swing.controller.GameController;
import emprestes.game.sudoku.domain.IPosition;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import static java.lang.String.valueOf;

/**
 * Represents a single Sudoku cell.
 * Pre-filled cells are non-editable and styled differently.
 * Empty cells can be selected for value entry.
 */
public class PositionButton extends JButton {

    private static final long serialVersionUID = -2852955512595059549L;

    private static final Color COLOR_PREFILLED = new Color(50, 50, 50);
    private static final Color COLOR_USER = new Color(0, 90, 180);
    private static final Color COLOR_SELECTED = new Color(200, 230, 255);
    private static final Color COLOR_EMPTY = Color.WHITE;
    private static final Font FONT_PREFILLED = new Font("SansSerif", Font.BOLD, 18);
    private static final Font FONT_USER = new Font("SansSerif", Font.PLAIN, 18);

    private final IPosition position;
    private final GameController controller;

    public PositionButton(IPosition position, GameController controller) {
        super();
        this.position = position;
        this.controller = controller;

        initControllers();
        initView();
    }

    private void initControllers() {
        addActionListener(e -> controller.select(position));
        controller.onSelectionChanged(selected -> refresh());
    }

    private void initView() {
        setToolTipText(valueOf(position));
        setFocusPainted(false);
        refresh();
    }

    private void refresh() {
        if (position.isVisible()) {
            // Pre-filled cell
            setText(valueOf(position.getValue()));
            setFont(FONT_PREFILLED);
            setForeground(COLOR_PREFILLED);
            setBackground(COLOR_EMPTY);
            setEnabled(false);
        } else if (position.nonBlank()) {
            // User-filled cell
            setText(valueOf(position.getValue()));
            setFont(FONT_USER);
            setForeground(COLOR_USER);
            setBackground(controller.isSelected(position) ? COLOR_SELECTED : COLOR_EMPTY);
            setEnabled(true);
        } else {
            // Empty cell
            setText("");
            setFont(FONT_USER);
            setForeground(COLOR_USER);
            setBackground(controller.isSelected(position) ? COLOR_SELECTED : COLOR_EMPTY);
            setEnabled(true);
        }
        setOpaque(true);
    }

    @Override
    public String toString() {
        return valueOf(position);
    }
}
