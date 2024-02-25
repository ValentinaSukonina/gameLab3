package com.example.gamelab3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.gamelab3.GameModel.gameField;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameModelTest {
    private GameModel game;

    @BeforeEach
    void setUp() {
        game = new GameModel();
    }

    @Test
    @DisplayName("Game allow move on empty position")
    void gameAllowMoveOnEmptyPosition()  {
        assertTrue(game.gameMoves(0, 0)); // Assuming 1,1 is an empty cell
    }

    @Test
    @DisplayName("Game not allow move on occupied position")
    void gameNotAllowMoveOnOccupiedPosition() {
        gameField[0][0] = "X";

        assertFalse(game.gameMoves(0, 0));
    }

    @Test
    @DisplayName("Game should detect win in row")
    void gameShouldDetectWinInRow() {
        String[][] field = game.getGameField();
        field[0][0] = "X";
        field[0][1] = "X";
        field[0][2] = "X";

        assertTrue(game.checkWin());
    }

    @Test
    @DisplayName("Game should detect win in column")
    void gameShouldDetectWinInColumn() {
        String[][] field = game.getGameField();
        field[0][0] = "O";
        field[1][0] = "O";
        field[2][0] = "O";

        assertTrue(game.checkWin());
    }

    @Test
    @DisplayName("Game should detect win in diagonal")
    void gameShouldDetectWinInDiagonal() {
        String[][] field = game.getGameField();
        field[0][0] = "O";
        field[1][1] = "O";
        field[2][2] = "O";

        assertTrue(game.checkWin());
    }

    @Test
    @DisplayName("checkWin return false for X and O in same row")
    void checkWinReturnFalseForXandOinSameRow() {
        String[][] field = game.getGameField();
        field[0][0] = "X";
        field[0][1] = "O";
        field[0][2] = "X";

        assertFalse(game.checkWin());
    }

    @Test
    @DisplayName("GameFieldFull return true for full board")
    void gameFieldFullReturnTrueForFullBoard() {
        String[][] fullField = {
                {"X", "O", "X"},
                {"X", "X", "O"},
                {"O", "X", "O"}
        };
        game.setGameField(fullField);

        assertTrue(game.isGameFieldFull());
    }

    @Test
    @DisplayName("GameFieldFull return false with empty spaces")
    void gameFieldFullReturnFalseWithEmptySpaces() {
        String[][] notFullField = {
                {"", "O", "X"},
                {"X", "", "O"},
                {"O", "X", "O"}
        };
        game.setGameField(notFullField);

        assertFalse(game.isGameFieldFull(), "Field should be considered not full when some cells are empty");
    }

    @Test
    @DisplayName("GameField return True For Empty Cell")
    void gameFieldReturnTrueForEmptyCell() {

        gameField[0][0] = "";

        assertTrue(game.gameMoves(0,0));
    }
    
    @Test
    @DisplayName("GameTestForDraw")
    void gameTestForDraw() {
        String[][] fullField = {
                {"O", "X", "O"},
                {"X", "O", "X"},
                {"X", "O", "X"}
        };
        game.setGameField(fullField);

        assertTrue(game.isGameFieldFull(), "Field considered full with no empty spaces");
        assertFalse(game.checkWin(), "Draw if no winner found with full board");
    }

    @Test
    @DisplayName("Set win count 3 to player should return 3")
    void setWinCount3ToPlayerShouldReturn3() {
        game.setWinCountsPlayer(3);

        assertThat(game.getWinCountsPlayer()).isEqualTo(3);
}

    @Test
    @DisplayName("Set win count 1 to computer should return 1")
    void setWinCount1ToComputerShouldReturn1() {
        game.setWinCountsComputer(1);

        assertThat(game.getWinCountsComputer()).isEqualTo(1);
}


}