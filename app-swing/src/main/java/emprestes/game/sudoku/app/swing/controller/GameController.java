package emprestes.game.sudoku.app.swing.controller;

import emprestes.game.sudoku.domain.IPosition;
import emprestes.game.sudoku.domain.exception.WrongPositionException;
import emprestes.game.sudoku.service.BoardService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Mediator between board cells and value buttons.
 * Tracks the currently selected position and dispatches value plays.
 */
public final class GameController {

    private final BoardService service;
    private final List<Consumer<IPosition>> selectionListeners = new ArrayList<>();
    private final List<Runnable> gameOverListeners = new ArrayList<>();
    private final List<Consumer<String>> errorListeners = new ArrayList<>();

    private IPosition selectedPosition;

    public GameController(BoardService service) {
        this.service = service;
    }

    public void select(IPosition position) {
        if (position.isVisible()) {
            return; // pre-filled cells cannot be selected
        }
        this.selectedPosition = position;
        selectionListeners.forEach(listener -> listener.accept(position));
    }

    public void playValue(Character value) {
        if (selectedPosition == null) {
            return;
        }
        try {
            service.play(value, selectedPosition);
            selectionListeners.forEach(listener -> listener.accept(null)); // deselect
            if (!service.isNotGameOver()) {
                gameOverListeners.forEach(Runnable::run);
            }
        } catch (WrongPositionException e) {
            errorListeners.forEach(listener -> listener.accept(e.getMessage()));
        }
        this.selectedPosition = null;
    }

    public void clearSelected() {
        if (selectedPosition == null || selectedPosition.isVisible()) {
            return;
        }
        selectedPosition.clear();
        selectionListeners.forEach(listener -> listener.accept(null));
        this.selectedPosition = null;
    }

    public IPosition getSelectedPosition() {
        return selectedPosition;
    }

    public boolean isSelected(IPosition position) {
        return selectedPosition != null && selectedPosition.equals(position);
    }

    public void onSelectionChanged(Consumer<IPosition> listener) {
        selectionListeners.add(listener);
    }

    public void onGameOver(Runnable listener) {
        gameOverListeners.add(listener);
    }

    public void onError(Consumer<String> listener) {
        errorListeners.add(listener);
    }

    public BoardService getService() {
        return service;
    }
}
