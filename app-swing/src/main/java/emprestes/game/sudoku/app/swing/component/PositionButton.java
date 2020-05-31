package emprestes.game.sudoku.app.swing.component;

import emprestes.game.sudoku.domain.Position;

import javax.swing.JButton;

import static java.lang.String.valueOf;

public class PositionButton extends JButton {

    private static final long serialVersionUID = -2852955512595059549L;

    private final Position position;

    public PositionButton(Position position) {
        super();

        this.position = position;

        initControllers();
        init();
    }

    private void initControllers() {
        // TODO Implement ActionListener implementation here
    }

    private void init() {
        setToolTipText(valueOf(position));
        setText(valueOf(position.getValue()));
    }

    @Override
    public String toString() {
        return valueOf(position);
    }
}
