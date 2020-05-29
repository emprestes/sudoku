package emprestes.game.sudoku.domain.exception;

public class WrongPositionException extends Exception {

    private static final long serialVersionUID = -8413260296869926676L;

    public WrongPositionException(String message) {
        super(message);
    }
}
