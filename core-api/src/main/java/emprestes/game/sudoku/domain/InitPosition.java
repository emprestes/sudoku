package emprestes.game.sudoku.domain;

/**
 * Callback funcional para atribuir um valor inicial a uma posição.
 */
@FunctionalInterface
public interface InitPosition {

    void init(Position position);
}
