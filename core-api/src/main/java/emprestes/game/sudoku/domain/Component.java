package emprestes.game.sudoku.domain;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Interface base para componentes do tabuleiro (regiões, linhas, colunas).
 *
 * @author Dude
 * @since 02/2026
 */
public interface Component extends Serializable {

    /** Limpa os valores contidos. */
    void clear();

    /** @return true se o identificador corresponde ao componente. */
    boolean equals(byte number);

    /** @return true se todas as posições do componente estão preenchidas. */
    boolean isCompleted();

    /** Executa ação sobre cada posição do componente. */
    void forEach(Consumer<Position> action);

    /** Obtém uma posição pelas coordenadas de linha/coluna. */
    Optional<Position> getBy(byte rowNumber, byte columnNumber);

    /** @return quantidade de colunas. */
    int getSizeColumns();

    /** @return quantidade de posições. */
    int getSizePositions();

    /** @return quantidade de linhas. */
    int getSizeRows();
}
