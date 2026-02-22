package emprestes.game.sudoku.domain;

import java.util.Random;

public enum GameVisibility {
    EASY;

    private static final Random random = new Random();

    public boolean isVisible() {
        return random.nextBoolean();
    }
}
