package emprestes.game.sudoku.domain;

/**
 * Callback funcional para inicializar uma região (subgrade) ao criar o tabuleiro.
 */
@FunctionalInterface
public interface InitRegion {

    void init(Region region);
}
