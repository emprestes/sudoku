package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.IPosition;
import emprestes.game.sudoku.domain.IShuffleValueStrategy;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class SudokuShuffleValueStrategy implements IShuffleValueStrategy, Serializable {

    @Serial
    private static final long serialVersionUID = -5450054276194144588L;

    @Override
    public void shuffle(
            List<IPosition> positions,
            Function<Character[], List<Character>> symbols,
            Supplier<Boolean> visibility
    ) {
        shuffleWithBacktracking(positions, 0, symbols, visibility);
    }

    private boolean shuffleWithBacktracking(
            List<IPosition> positions,
            int index,
            Function<Character[], List<Character>> symbols,
            Supplier<Boolean> visibility
    ) {
        if (index >= positions.size()) {
            return true;
        }

        var position = positions.get(index);

        var candidates = symbols.apply(position.usedSymbols());
        for (Character symbol : candidates) {
            if (position.isInvalidFor(symbol)) {
                continue;
            }

            position.setValue(symbol);
            position.setVisible(visibility.get());

            if (shuffleWithBacktracking(positions, index + 1, symbols, visibility)) {
                return true;
            }

            position.clear();
        }

        return false;
    }
}
