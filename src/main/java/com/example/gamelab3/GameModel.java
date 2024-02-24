package com.example.gamelab3;

import java.util.Objects;

public class GameModel {
    static String[][] gameField;
    String player;
    String computer;
    int winCountsPlayer;
    int winCountsComputer;
    boolean gameContinue = true;

    public GameController controller;

    public GameModel() {
        gameField = new String[3][3];
        player = "X";
        computer = "O";
        winCountsPlayer = 0;
        winCountsComputer = 0;
        newGame();
    }

    public String[][] getGameField() {
        return gameField;
    }

    public static void setGameField(String[][] gameField) {
        GameModel.gameField = gameField;
    }

    public boolean gameMoves(int row, int column) {
        if (!Objects.equals(gameField[row][column], "")) {
            return false;
        }
        checkWin();
        return true;
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (checkLine(gameField[i][0], gameField[i][1], gameField[i][2])) return true;
            if (checkLine(gameField[0][i], gameField[1][i], gameField[2][i])) return true;
        }
        return checkLine(gameField[0][0], gameField[1][1], gameField[2][2]) ||
                checkLine(gameField[0][2], gameField[1][1], gameField[2][0]);// No win found
    }

    private boolean checkLine(String a, String b, String c) {
        return Objects.equals(a, b) && Objects.equals(a, c) &&
                (Objects.equals(a, "O") || Objects.equals(a, "X"));
    }

    public boolean isGameFieldFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameField[row][col].isEmpty()) {
                    return false;
                }
            }
        }
        return true; // No empty spaces found
    }

    public void newGame() {
        for (int row = 0; row < 3; row++){
            for (int column = 0; column < 3; column++) {
                gameField[row][column] = "";
                System.out.println(gameField[row][column]);
            }
        }
        getGameField();
    }

    public int getWinCountsPlayer() {
        return winCountsPlayer;
    }

    public int getWinCountsComputer() {
        return winCountsComputer;
    }

    public void setWinCountsPlayer(int winCountsPlayer) {
        this.winCountsPlayer = winCountsPlayer;
    }

    public void setWinCountsComputer(int winCountsComputer) {
        this.winCountsComputer = winCountsComputer;
    }
}
