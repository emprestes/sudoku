package emprestes.game.sudoku.service.model;

import emprestes.game.sudoku.domain.GameDimension;
import emprestes.game.sudoku.domain.IBoard;
import emprestes.game.sudoku.domain.IPosition;
import emprestes.game.sudoku.domain.IRegion;
import emprestes.game.sudoku.domain.exception.PositionException;
import emprestes.game.sudoku.domain.exception.WrongPositionException;
import emprestes.game.sudoku.domain.model.SudokuBoard;
import emprestes.game.sudoku.service.BoardService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DefaultBoardService")
class DefaultBoardServiceTest {

    private IBoard board;
    private BoardService service;

    @BeforeEach
    void setUp() {
        board = new SudokuBoard();
        service = new DefaultBoardService(board);
    }

    @Nested
    @DisplayName("getBoard()")
    class GetBoard {

        @Test
        @DisplayName("should return the board instance passed to constructor")
        void shouldReturnBoard() {
            assertSame(board, service.getBoard());
        }
    }

    @Nested
    @DisplayName("getSide()")
    class GetSide {

        @Test
        @DisplayName("should return the board side length")
        void shouldReturnSide() {
            assertEquals(board.getSide(), service.getSide());
        }

        @Test
        @DisplayName("should return 3 for default 3x3 dimension")
        void shouldReturn3ForDefault() {
            assertEquals((byte) 3, service.getSide());
        }

        @Test
        @DisplayName("should return 4 for 4x4 dimension")
        void shouldReturn4For4x4() {
            IBoard board4 = new SudokuBoard(GameDimension.D4X4);
            BoardService service4 = new DefaultBoardService(board4);
            assertEquals((byte) 4, service4.getSide());
        }
    }

    @Nested
    @DisplayName("start()")
    class Start {

        @Test
        @DisplayName("should start board without error")
        void shouldStartBoard() {
            assertDoesNotThrow(() -> service.start());
        }

        @Test
        @DisplayName("after start, game should not be over (positions are masked)")
        void afterStartGameNotOver() {
            service.start();
            // After start with default visibility, some positions are hidden
            // so the game should not be over
            assertTrue(service.isNotGameOver());
        }
    }

    @Nested
    @DisplayName("start(Consumer<IRegion>)")
    class StartWithAction {

        @Test
        @DisplayName("should initialize board with custom action")
        void shouldInitWithAction() {
            Consumer<IRegion> action = region -> {
                // no-op action - just verify it doesn't throw
            };
            assertDoesNotThrow(() -> service.start(action));
        }
    }

    @Nested
    @DisplayName("play(Character, byte, byte, byte)")
    class PlayByCoordinates {

        @Test
        @DisplayName("should throw PositionException for invalid coordinates")
        void shouldThrowForInvalidCoordinates() {
            service.start();
            // Region 0 doesn't exist (regions are 1-based)
            assertThrows(PositionException.class, () ->
                    service.play('1', (byte) 0, (byte) 0, (byte) 0));
        }
    }

    @Nested
    @DisplayName("play(Character, int, int, int) default method")
    class PlayByIntCoordinates {

        @Test
        @DisplayName("should delegate to byte version")
        void shouldDelegateToByteVersion() {
            service.start();
            // Invalid coordinates should still throw
            assertThrows(PositionException.class, () ->
                    service.play('1', 0, 0, 0));
        }
    }

    @Nested
    @DisplayName("isNotGameOver()")
    class IsNotGameOver {

        @Test
        @DisplayName("should return true when game is in progress")
        void shouldReturnTrueWhenInProgress() {
            service.start();
            assertTrue(service.isNotGameOver());
        }
    }

    @Nested
    @DisplayName("constructor")
    class Constructor {

        @Test
        @DisplayName("should accept a SudokuBoard with 4x4 dimension")
        void shouldAccept4x4Board() {
            IBoard board4 = new SudokuBoard(GameDimension.D4X4);
            BoardService service4 = new DefaultBoardService(board4);
            assertNotNull(service4.getBoard());
            assertEquals((byte) 4, service4.getSide());
        }
    }
}
