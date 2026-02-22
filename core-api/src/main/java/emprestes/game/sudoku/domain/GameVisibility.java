package emprestes.game.sudoku.domain;

import java.util.Random;

public enum GameVisibility {
    EASY(0.44f, 0.56f, 0.22f, 0.33f),;

    private final float minRegion;
    private final float maxRegion;
    private final float minBoard;
    private final float maxBoard;

    private static final Random random = new Random();

    GameVisibility(float minRegion, float maxRegion, float minBoard, float maxBoard) {
        this.minRegion = minRegion;
        this.maxRegion = maxRegion;
        this.minBoard = minBoard;
        this.maxBoard = maxBoard;
    }

    public boolean isVisible(IPosition position) {
        var size = position.regionSize();

        /* TODO: develop a logic for visibility considering as following:
         * 1. Percentage relationship between region size from position, minimum visibility and maximum visibility;
         * 2. Visibility true should be always between min/max percentage and must be defined randomly;
         * 3. All the rest after covered min/max percentage visibility, all the rest should be false.
         * 4. The total visibility must be between min/max board visibility.
         */
        return false;
    }
}
