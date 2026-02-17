package emprestes.game.sudoku.domain;

/**
 * Callback funcional para inicializar uma região ao construir o tabuleiro.
 *
 * @author Dude
 * @since 02/2026
 */
@FunctionalInterface
public interface InitRegion {

    /** Inicializa a região fornecida. */
    void init(Region region);
}
