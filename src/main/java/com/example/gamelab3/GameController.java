package com.example.gamelab3;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static javafx.util.Duration.seconds;

public class GameController {
    static Label drawSign;
    public Label gameOverSign;
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;
    public Button newGameButton;

    private GameModel model = new GameModel();

    @FXML
    Button[][] buttons = new Button[3][3];

    @FXML
    private Label showPlayerScore;

    @FXML
    private Label showComputerScore;

    @FXML
    private Button exitButton;

    @FXML
    private GridPane grid;

    public void initialize() {
        this.buttons[0][0] = button1;
        this.buttons[0][1] = button2;
        this.buttons[0][2] = button3;
        this.buttons[1][0] = button4;
        this.buttons[1][1] = button5;
        this.buttons[1][2] = button6;
        this.buttons[2][0] = button7;
        this.buttons[2][1] = button8;
        this.buttons[2][2] = button9;
    }

    @FXML
    public void buttonClick(ActionEvent actionEvent) {
        Button buttonClicked = (Button) actionEvent.getSource();

        int rowIndex = GridPane.getRowIndex(buttonClicked);
        int columnIndex = GridPane.getColumnIndex(buttonClicked);

        if (!model.gameContinue) return;//no action if game is over or button already pressed
        if(model.gameMoves(rowIndex, columnIndex)) {
            model.player = "X";
            buttonClicked.setText("X");
        } else {
            buttonClicked.setText("X");
        }
        model.gameField[rowIndex][columnIndex] = model.player;

            if(model.checkWin()){
                gameOver();
                model.setWinCountsPlayer(++model.winCountsPlayer);
                displayScore();

            } else if (model.isGameFieldFull()) {
                gameOver();
                displayScore();

            } else {
                computerMove();
            }
    }

    private void computerMove() {
        PauseTransition pause = new PauseTransition(seconds(0.2)); // game delay
        pause.setOnFinished(event -> {
        for(int row = 0; row < 3;row++) {
            for (int column = 0; column < 3; column++) {
                if (buttons[row][column].getText().isEmpty()) {
                    model.gameField[row][column] = model.computer;
                    buttons[row][column].setText(model.computer);

                    if (model.checkWin()) {
                        gameOver();
                        model.setWinCountsComputer(++model.winCountsComputer);
                        displayScore();
                        return;
                    }
                    if (model.isGameFieldFull()) {
                        gameOver();
                        displayScore();}
                    return;
                }
            }
        }
    });
    pause.play();

    }

    protected void displayScore() {
        showPlayerScore.setText(String.valueOf(model.getWinCountsPlayer()));
        showComputerScore.setText(String.valueOf(model.getWinCountsComputer()));
    }

    protected void gameOver(){
        gameOverSign.setText("Game is over");
        button1.setDisable(true);
        button2.setDisable(true);
        button3.setDisable(true);
        button4.setDisable(true);
        button5.setDisable(true);
        button6.setDisable(true);
        button7.setDisable(true);
        button8.setDisable(true);
        button9.setDisable(true);
    }

    public void newGameClick() {
        model.newGame();
        clearBoard();
        model.getGameField();
    }

    private void clearBoard() {
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        button1.setDisable(false);
        button2.setDisable(false);
        button3.setDisable(false);
        button4.setDisable(false);
        button5.setDisable(false);
        button6.setDisable(false);
        button7.setDisable(false);
        button8.setDisable(false);
        button9.setDisable(false);
        gameOverSign.setText("");
    }

    public void closeWindow() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}