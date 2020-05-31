package emprestes.game.sudoku.domain.exception;

import static java.lang.String.format;

public class PositionNotFoundException extends PositionException {

    private static final long serialVersionUID = -6747600513506656033L;

    public PositionNotFoundException(String formatMessage, Object... values) {
        super(format(formatMessage, values));
    }
}
