package emprestes.game.sudoku.domain;

import emprestes.game.sudoku.domain.exception.PositionException;
import emprestes.game.sudoku.domain.exception.WrongPositionException;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Contrato de um tabuleiro de Sudoku, permitindo inicialização, jogadas e visibilidade.
 *
 * @author Dude
 * @since 02/2026
 */
public interface Board extends Serializable {

    /** @return lado do tabuleiro. */
    Byte getSide();

    /** Inicializa estrutura de regiões/linhas/colunas aplicando a ação informada. */
    void init(Consumer<Region> action);

    /** Limpa e preenche o tabuleiro com valores válidos. */
    void start();

    /** Realiza jogada informando valor e coordenadas (região, linha, coluna). */
    void play(Character value, byte regionNumber, byte rowNumber, byte columnNumber) throws PositionException;

    /** Realiza jogada em uma posição já obtida. */
    void play(Character value, Position position) throws WrongPositionException;

    /** Indica se todas as posições estão preenchidas corretamente. */
    boolean isGameOver();

    /** @return true se o jogo ainda não terminou. */
    default boolean isNotGameOver() {
        return !isGameOver();
    }

    /** Define o nível de visibilidade do tabuleiro. */
    void setLevel(SudokuLevel level);

    /** @return nível atual de visibilidade. */
    SudokuLevel getLevel();
}
