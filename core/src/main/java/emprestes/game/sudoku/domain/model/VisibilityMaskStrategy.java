package emprestes.game.sudoku.domain.model;

import emprestes.game.sudoku.domain.Position;

import java.util.List;

/**
 * Strategy for deciding which positions are initially visible.
 */
interface VisibilityMaskStrategy {

    void apply(List<Position> positions);
}
