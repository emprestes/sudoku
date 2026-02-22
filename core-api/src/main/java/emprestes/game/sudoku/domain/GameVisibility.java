package emprestes.game.sudoku.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;
import static java.lang.Math.max;
import static java.lang.Math.min;

public enum GameVisibility {
    EASY(0.22f, 0.67f, 0.22f, 0.67f),
    ;

    private final float minRegion;
    private final float maxRegion;
    private final float minBoard;
    private final float maxBoard;

    private static final Random random = new Random();

    private final Map<Byte, Integer> regionVisibleMin = new HashMap<>();
    private final Map<Byte, Integer> regionVisibleMax = new HashMap<>();
    private final Map<Byte, Integer> regionVisibleAssigned = new HashMap<>();
    private final Map<Byte, Integer> regionProcessed = new HashMap<>();
    private final Map<String, Boolean> visibilityByPosition = new HashMap<>();

    private int boardSize;
    private int boardMinVisible;
    private int boardMaxVisible;
    private int boardVisibleAssigned;
    private int boardProcessed;

    GameVisibility(float minRegion, float maxRegion, float minBoard, float maxBoard) {
        this.minRegion = minRegion;
        this.maxRegion = maxRegion;
        this.minBoard = minBoard;
        this.maxBoard = maxBoard;
    }

    public void reset() {
        regionVisibleMin.clear();
        regionVisibleMax.clear();
        regionVisibleAssigned.clear();
        regionProcessed.clear();
        visibilityByPosition.clear();

        boardSize = 0;
        boardMinVisible = 0;
        boardMaxVisible = 0;
        boardVisibleAssigned = 0;
        boardProcessed = 0;
    }

    public boolean isVisible(IPosition position) {
        var positionKey = key(position);
        if (visibilityByPosition.containsKey(positionKey)) {
            return visibilityByPosition.get(positionKey);
        }

        initBoardLimitsIfNeeded(position);

        var regionNumber = position.getRegion().getNumber();
        int regionSize = position.regionSize();
        int regionMin = regionVisibleMin.computeIfAbsent(regionNumber, _region -> percentageMin(regionSize, minRegion));
        int regionMax = regionVisibleMax.computeIfAbsent(regionNumber, _region -> percentageMax(regionSize, maxRegion));

        int assignedByRegion = regionVisibleAssigned.getOrDefault(regionNumber, 0);
        int processedByRegion = regionProcessed.getOrDefault(regionNumber, 0);

        int remainingByRegion = regionSize - processedByRegion;
        int remainingByBoard = boardSize - boardProcessed;

        int regionStillRequired = max(0, regionMin - assignedByRegion);
        int boardStillRequired = max(0, boardMinVisible - boardVisibleAssigned);

        boolean mustBeVisible = regionStillRequired >= remainingByRegion
                || boardStillRequired >= remainingByBoard;

        boolean reachedRegionMax = assignedByRegion >= regionMax;
        boolean reachedBoardMax = boardVisibleAssigned >= boardMaxVisible;

        boolean visible;
        if (mustBeVisible && !reachedRegionMax && !reachedBoardMax) {
            visible = true;
        } else if (reachedRegionMax || reachedBoardMax) {
            visible = false;
        } else {
            float regionPressure = remainingByRegion > 0
                    ? (float) regionStillRequired / remainingByRegion
                    : 0f;
            float boardPressure = remainingByBoard > 0
                    ? (float) boardStillRequired / remainingByBoard
                    : 0f;

            float chance = max(regionPressure, boardPressure);
            visible = random.nextFloat() < chance;
        }

        if (visible) {
            regionVisibleAssigned.put(regionNumber, assignedByRegion + 1);
            boardVisibleAssigned++;
        }

        regionProcessed.put(regionNumber, processedByRegion + 1);
        boardProcessed++;

        visibilityByPosition.put(positionKey, visible);
        return visible;
    }

    private void initBoardLimitsIfNeeded(IPosition position) {
        if (boardSize > 0) {
            return;
        }

        int regionSize = position.regionSize();
        boardSize = regionSize * regionSize;

        boardMinVisible = percentageMin(boardSize, minBoard);
        boardMaxVisible = percentageMax(boardSize, maxBoard);
    }

    private int percentageMin(int total, float percentage) {
        return max(1, (int) ceil(total * percentage));
    }

    private int percentageMax(int total, float percentage) {
        return min(total, max(1, (int) floor(total * percentage)));
    }

    private String key(IPosition position) {
        return "%s-%s-%s".formatted(
                position.getRegion().getNumber(),
                position.getRow().getNumber(),
                position.getColumn().getNumber()
        );
    }
}
