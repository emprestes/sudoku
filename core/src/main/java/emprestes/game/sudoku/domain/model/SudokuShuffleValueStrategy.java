package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.IPosition;
import emprestes.game.sudoku.domain.IShuffleValueStrategy;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SudokuShuffleValueStrategy implements IShuffleValueStrategy {

    @Override
    public void shuffle(List<IPosition> positions, Function<Character[], List<Character>> symbols, Predicate<IPosition> visibility) {
        shuffleWithBacktracking(positions, 0, symbols, visibility);
    }

    private boolean shuffleWithBacktracking(
            List<IPosition> positions,
            int index,
            Function<Character[], List<Character>> symbols,
            Predicate<IPosition> visibility
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
            position.setVisible(visibility.test(position));

            if (shuffleWithBacktracking(positions, index + 1, symbols, visibility)) {
                return true;
            }

            position.clear();
        }

        return false;
    }
}
