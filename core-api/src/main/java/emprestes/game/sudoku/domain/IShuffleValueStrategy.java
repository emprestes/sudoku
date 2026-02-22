package emprestes.game.sudoku.domain;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Strategy for deciding which positions are initially visible.
 */
public interface IShuffleValueStrategy {

    void shuffle(List<IPosition> positions, Function<Character[], List<Character>> symbols, Supplier<Boolean> visibility);
}
