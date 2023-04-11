package com.example.snake_ladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
   private Circle coin;

  private   int currentPosition;

   private String name;

   private static Board board = new Board();

    public Player(int tileSize, Color coinColor,String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currentPosition = 0;
        movePlayer(1);
        name = playerName;
    }

    public void movePlayer(int diceValue){
        if(currentPosition+diceValue <= 100){
            currentPosition+=diceValue;

            TranslateTransition firsMove = translateAnimation(diceValue),secondMove = null;

            int newPosition = board.getNewPosition(currentPosition);
            if(newPosition != currentPosition){
                if(newPosition < currentPosition){
                    SnakeLadder.playEffect(0);
                }else if(newPosition > currentPosition) {
                    SnakeLadder.playEffect(1);
                }
                currentPosition = newPosition;
                secondMove = translateAnimation(6);
            }

            if(secondMove == null){
                firsMove.play();
            }else{
                SequentialTransition sequentialTransition = new SequentialTransition(
                        firsMove,new PauseTransition(Duration.millis(200)), secondMove
                );
                sequentialTransition.play();
            }


        }


    }

    private TranslateTransition translateAnimation(int diceValue){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100*diceValue),coin);
        translateTransition.setToX(board.getXCoordinate(currentPosition));
        translateTransition.setToY(board.getYCoordinate(currentPosition));
        translateTransition.setAutoReverse(false);
      return translateTransition;
    }


    boolean isWinner(int currentPosition){
        if(currentPosition == 100){
            return true;
        }
        return false;
    }

    void resetPlayer(){
        currentPosition = 0;
        movePlayer(1);
    }

    public Circle getCoin() {
        return coin;
    }

    public void setCoin(Circle coin) {
        this.coin = coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
