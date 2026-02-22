package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.GameVisibility;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameVisibilityTest {

    @Test
    public void isVisibleShouldReturnBooleanAcrossManyCalls() {
        int trues = 0;
        int falses = 0;

        for (int i = 0; i < 200; i++) {
            if (GameVisibility.EASY.isVisible()) {
                trues++;
            } else {
                falses++;
            }
        }

        assertTrue(trues > 0);
        assertTrue(falses > 0);
    }
}
