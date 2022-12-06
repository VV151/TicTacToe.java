// **********************************************************************************
// Title: TicTacToe Game
// Author: Vivek Vashishat
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022
// File: TicTacToe.java
// Description: The users of this software will be those that want to play a simple game of Tic-Tac-Toe.
// The software's purpose is to utilize javafx to create the game itself.
// This software will be used only when the user(s) are bored and want to play something.
// The software works mainly through javafx and it's imports to create the actual stage for the game.
// Anyone would use this software over existing processes due to simplicity in its code and final product.
// **********************************************************************************

package com.example.tictactoe;

// JavaFx imports allow for program to operate/show the scene.

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Ellipse;

public class TicTacToe extends Application {
    private Cell[][] cell = new Cell[3][3]; // "cell" value sets up a 3x3 table for the tic-tac-toe format.
    private Label lblStatus = new Label("X's turn to play"); // "lblstatus" value sets up a label for text based on whose turn it is and who wins.
    private char whoseTurn = 'X'; // Character value "whoseTurn" helps to indicate the turn of said player(s).

    // Sets up the primary stage for the program when running.
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pane.add(cell[i][j] = new Cell(), j, i);
            }
        }
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);
        Scene scene = new Scene (borderPane, 450, 170);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Boolean expression "isFull" ends up stopping the code if the
    public boolean isFull () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(cell[i][j].getToken() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Boolean expression "isWon" will indicate whether the one using the private char
    // "token" as 'X' or 'O' has won the game.
    public boolean isWon (char token) {
        for (int i = 0; i < 3; i++) {
            if (cell[i][0].getToken() == token
                    && cell[i][1].getToken() == token
                    && cell[i][2].getToken() == token) {
                return true;
            }
            for (int j = 0; j < 3; j++) {
                if (cell[0][j].getToken() == token
                        && cell[1][j].getToken() == token
                        && cell[2][j].getToken() == token) {
                    return true;
                }
                if (cell[0][0].getToken() == token
                        && cell[1][1].getToken() == token
                        && cell[2][2].getToken() == token) {
                    return true;
                }
                if (cell[0][2].getToken() == token
                        && cell[1][1].getToken() == token
                        && cell[2][0].getToken() == token) {
                    return true;
                }
            }
        }
        return false;
    }

    // Sets up functions of mouse clicking and the Line shape for the 'X' and the Ellipse shape for 'O'.
    public class Cell extends Pane {
        private char token = ' ';

        public Cell() {
            setStyle("-fx-border-color:black");
            this.setPrefSize(800, 800);
            this.setOnMouseClicked(e->handleMouseClick()); // Allows for a panel in the stage to be clicked.
        }

        public char getToken() {
            return token;
        }

        public void setToken(char c) {
            token = c;
            // Sets up the 'X' token for first player and forms the X shape.
            if (token == 'X'){
                Line line1 = new Line(10, 10, this.getWidth() - 10,
                        this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));
                Line line2 = new Line(10, this.getHeight() - 10,
                        this.getWidth() - 10,10);
                line2.startYProperty().bind(this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));
                this.getChildren().addAll(line1,line2);
            }
            // Sets up the 'O' token for the other player and forms the Ellipse shape.
            else if (token == 'O'){
                Ellipse ellipse = new Ellipse(this.getWidth()/2,
                        this.getHeight()/2, this.getWidth()/2-10,
                        this.getHeight()/2-10);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.BLUE);
                ellipse.setFill(Color.WHITE);
                this.getChildren().add(ellipse);
            }
        }

        // Utilized to signal whose turn it is after one player has made his/her turn.
        private void handleMouseClick() {
            if(token == ' ' && whoseTurn != ' '){
                setToken(whoseTurn);
                if (isWon(whoseTurn)) {
                    lblStatus.setText(whoseTurn + " won! The game is over!");
                    whoseTurn = ' ';
                }
                else if (isFull()) {
                    lblStatus.setText("Tie! The game is over!");
                }
                else{
                    whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
                    lblStatus.setText(whoseTurn + "'s turn");
                }
            }
        }
    }

    // Launches the program.
    public static void main(String[] args) {
        Help.about();
        launch(args);
    }
}
