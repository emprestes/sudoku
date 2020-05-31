module sudoku.service {
    requires sudoku.core.api;
    requires sudoku.service.api;

    exports emprestes.game.sudoku.service.model;
}