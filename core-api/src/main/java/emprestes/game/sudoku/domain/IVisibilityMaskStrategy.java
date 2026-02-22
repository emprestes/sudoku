package emprestes.game.sudoku.domain;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Strategy for deciding which positions are initially visible.
 */
public interface IVisibilityMaskStrategy extends Consumer<Collection<IPosition>> { }
