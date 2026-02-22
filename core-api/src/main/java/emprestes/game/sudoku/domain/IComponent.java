package emprestes.game.sudoku.domain;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Base interface for board components (regions, rows, columns).
 *
 * @author Dude
 * @since 02/2026
 */
public interface IComponent extends Serializable {

    /** Clear contained values. */
    void clear();

    /** @return true if the identifier matches this component. */
    boolean equals(byte number);

    /** @return true if all positions are filled. */
    boolean isCompleted();

    /** Apply an action to each position. */
    void forEach(Consumer<IPosition> action);

    /** Get a position by row/column coordinates. */
    Optional<IPosition> getBy(byte rowNumber, byte columnNumber);

    /** @return number of columns. */
    int getSizeColumns();

    /** @return number of positions. */
    int getSizePositions();

    /** @return number of rows. */
    int getSizeRows();
}
