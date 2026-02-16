/**
 * API de domínio do Sudoku.
 * <p>
 * Define os contratos para tabuleiro ({@link emprestes.game.sudoku.domain.Board}),
 * componentes estruturais ({@link emprestes.game.sudoku.domain.Region},
 * {@link emprestes.game.sudoku.domain.Row}, {@link emprestes.game.sudoku.domain.Column},
 * {@link emprestes.game.sudoku.domain.Position}) e valores permitidos
 * ({@link emprestes.game.sudoku.domain.SymbolValues}).
 * <p>
 * Também expõe utilitários de inicialização ({@link emprestes.game.sudoku.domain.InitRegion},
 * {@link emprestes.game.sudoku.domain.InitPosition}) e exceções de regra de negócio
 * em {@link emprestes.game.sudoku.domain.exception}.
 */
package emprestes.game.sudoku.domain;
