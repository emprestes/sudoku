package emprestes.game.sudoku.domain;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;

public interface Component extends Serializable {

    void clear();

    boolean equals(byte number);

    boolean isCompleted();

    void forEach(Consumer<Position> action);

    Optional<Position> getBy(byte rowNumber, byte columnNumber);

    int getSizeColumns();

    int getSizePositions();

    int getSizeRows();
}
