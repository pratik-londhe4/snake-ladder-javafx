package com.example.snake_ladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize=40,tileWidth=10,tileHeight=10;
    public static final int buttonLine = tileSize*tileHeight + 50 , infoLine = buttonLine-30;

    private static Dice dice = new Dice();
    private Player playerOne,playerTwo;

    private boolean gameStarted = false , playerOneTurn=true, playerTwoTurn=false;

    public static void playEffect(int choice){
        MediaPlayer ladderMp;
        MediaPlayer failMp;

        switch (choice){
            case 0: {
                Media fail = new Media("file:/home/pratik/IdeaProjects/snake_ladder/src/main/fail.mp3");
                 failMp = new MediaPlayer(fail);
                 failMp.play();
                 failMp.setOnEndOfMedia(new Runnable() {
                     @Override
                     public void run() {
                         failMp.stop();
                         failMp.dispose();
                     }
                 });

                 break;
            }
            case 1: {
                Media ladder = new Media("file:/home/pratik/IdeaProjects/snake_ladder/src/main/ladder.mp3");
                ladderMp = new MediaPlayer(ladder);
                ladderMp.play();

                ladderMp.setOnEndOfMedia(new Runnable() {
                    @Override
                    public void run() {
                        ladderMp.stop();
                        ladderMp.dispose();
                    }
                });
            }
        }

    }

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(tileSize*tileHeight,tileSize*tileWidth+100);
        for (int i = 0; i < tileHeight; i++) {
            for (int j = 0; j < tileWidth; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().addAll(tile);
            }
        }
        Image img = new Image("file:/home/pratik/IdeaProjects/snake_ladder/src/main/img.jpg");
        ImageView board = new ImageView(img);
        board.setFitHeight(tileSize*tileHeight);
        board.setFitWidth(tileSize*tileWidth);
        root.getChildren().add(board);

        //button line
        Button playerOneButton = new Button("Player One");
        playerOneButton.setDisable(true);
        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setDisable(true);
        Button startButton = new Button("Start");

        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(buttonLine);

        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(buttonLine);

        startButton.setTranslateX(160);
        startButton.setTranslateY(buttonLine);

        //info label
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start Game");

        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);

        playerTwoLabel.setTranslateY(infoLine)  ;
        playerTwoLabel.setTranslateX(300);

        diceLabel.setTranslateY(infoLine) ;
        diceLabel.setTranslateX(160);

        //
        playerOne = new Player(tileSize, Color.BLACK,"Pratik");
        playerTwo = new Player(tileSize-10,Color.YELLOW,"Sahil");


        //move player

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                    gameStarted = true;
                    diceLabel.setText("Game started");
                    playerOneLabel.setText("Your Turn "+ playerOne.getName());
                    playerOneTurn = true;
                    playerTwoTurn=  false;
                    playerTwoLabel.setText("");
                    playerTwoButton.setDisable(true);
                    playerOneButton.setDisable(false);
                playerOne.resetPlayer();
                playerTwo.resetPlayer();



            }
        });

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn) {
                        //move
                        int diceValue = dice.getRolledDice();
                        diceLabel.setText(diceValue + "");
                        playerOne.movePlayer(diceValue);

                        //winning condition
                        if(playerOne.isWinner(playerOne.getCurrentPosition())){
                            diceLabel.setText("Winner is : "+ playerOne.getName());
                            //disable myself
                            playerOneTurn = false;
                            playerOneLabel.setText("");
                            playerOneButton.setDisable(true);
                            //disable player 2
                            playerTwoTurn = false;
                            playerTwoLabel.setText("");
                            playerTwoButton.setDisable(true);
                            //enable start
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted=false;
                        }
                        else{
                            //disable myself
                            playerOneTurn = false;
                            playerOneLabel.setText("");
                            playerOneButton.setDisable(true);
                            //enable player 2
                            playerTwoTurn = true;
                            playerTwoLabel.setText("Your Turn "+ playerTwo.getName());
                            playerTwoButton.setDisable(false);
                        }


                    }
                }



            }
        });


        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn) {
                        //move
                        int diceValue = dice.getRolledDice();
                        diceLabel.setText(diceValue + "");
                        playerTwo.movePlayer(diceValue);

                        //winning condition
                        if(playerTwo.isWinner(playerTwo.getCurrentPosition())){
                            diceLabel.setText("Winner is : "+ playerTwo.getName());
                            //disable myself
                            playerOneTurn = false;
                            playerOneLabel.setText("");
                            playerOneButton.setDisable(true);
                            //disable player 2
                            playerTwoTurn = false;
                            playerTwoLabel.setText("");
                            playerTwoButton.setDisable(true);
                            //enable start
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted=false;
                        }else{
                            //disable myself
                            playerTwoTurn = false;
                            playerTwoLabel.setText("");
                            playerTwoButton.setDisable(true);
                            //enable player 2
                            playerOneTurn = true;
                            playerOneLabel.setText("Your Turn "+ playerOne.getName());
                            playerOneButton.setDisable(false);
                        }


                    }
                }



            }
        });


        root.getChildren().addAll(
                playerOneButton,playerTwoButton,startButton,
                playerOneLabel,playerTwoLabel,diceLabel,
                playerOne.getCoin(),playerTwo.getCoin()
        );



        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Media bgm = new Media("file:/home/pratik/IdeaProjects/snake_ladder/src/main/bgm.mp3");
        MediaPlayer mp = new MediaPlayer(bgm);
        mp.setVolume(0.3);
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.setAutoPlay(true);
        mp.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mp.seek(Duration.ZERO);
                mp.play();
            }
        });
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}