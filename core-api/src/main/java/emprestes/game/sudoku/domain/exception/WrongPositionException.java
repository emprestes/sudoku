package emprestes.game.sudoku.domain.exception;

import static java.lang.String.format;

public class WrongPositionException extends PositionException {

    private static final long serialVersionUID = -8413260296869926676L;

    public WrongPositionException(String formatMessage, Object... values) {
        super(format(formatMessage, values));
    }
}
