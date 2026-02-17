package emprestes.game.sudoku.domain;

/**
 * Callback funcional para atribuir valor inicial a uma posição.
 *
 * @author Dude
 * @since 02/2026
 */
@FunctionalInterface
public interface InitPosition {

    /** Inicializa a posição fornecida. */
    void init(Position position);
}
