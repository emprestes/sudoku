package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Position;
import emprestes.game.sudoku.domain.SudokuDifficulty;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * EASY strategy: reveal a generous amount of initial clues.
 */
final class EasyVisibilityMaskStrategy implements VisibilityMaskStrategy, Serializable {

    @Serial
    private static final long serialVersionUID = -8864012481913597164L;

    private static final float BASE_3X3_POSITIONS = 81.0f;

    private final Random random;

    EasyVisibilityMaskStrategy(Random random) {
        this.random = random;
    }

    @Override
    public void apply(List<Position> positions) {
        positions.forEach(position -> position.setVisible(false));

        if (positions.isEmpty()) {
            return;
        }

        int visibleCount = nextVisibleCount(positions.size());
        List<Position> shuffled = new ArrayList<>(positions);

        Collections.shuffle(shuffled, random);
        shuffled.stream()
                .limit(visibleCount)
                .forEach(position -> position.setVisible(true));
    }

    private int nextVisibleCount(int size) {
        int minVisible = scaledValue(SudokuDifficulty.EASY.getMinVisiblePositions(), size);
        int maxVisible = scaledValue(SudokuDifficulty.EASY.getMaxVisiblePositions(), size);

        if (maxVisible < minVisible) {
            maxVisible = minVisible;
        }

        return random.nextInt(minVisible, maxVisible + 1);
    }

    /**
     * Scales EASY clue range from a 9x9 baseline (81 positions) to other board sizes.
     */
    private int scaledValue(int defaultFor3x3, int size) {
        int scaled = Math.round((defaultFor3x3 / BASE_3X3_POSITIONS) * size);
        return min(size, max(1, scaled));
    }

}
