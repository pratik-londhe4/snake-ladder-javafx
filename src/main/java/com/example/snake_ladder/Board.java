package com.example.snake_ladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> positionCoordinates ;
    ArrayList<Integer> snakeLadderPositions;

    public Board(){
        positionCoordinates = new ArrayList<>();
        populateCoordinates();
        populateSnakeLadder();
    }

    private void populateCoordinates(){
        positionCoordinates.add(new Pair<>(0,0));

        for (int i = 0; i < SnakeLadder.tileHeight; i++) {
            for (int j = 0; j < SnakeLadder.tileWidth; j++) {

                int xcord = 0;

                if(i % 2 ==0){
                    xcord = j*SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                }
                else {
                    xcord = (SnakeLadder.tileWidth*SnakeLadder.tileSize) - j*SnakeLadder.tileSize - SnakeLadder.tileSize/2;
                }

                int ycord = (SnakeLadder.tileSize*SnakeLadder.tileHeight) - i*SnakeLadder.tileSize - SnakeLadder.tileSize/2;

                positionCoordinates.add(new Pair<>(xcord,ycord));

            }
        }

    }

    private void populateSnakeLadder(){
        snakeLadderPositions = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPositions.add(i);
        }

        snakeLadderPositions.set(4,25);
        snakeLadderPositions.set(13,46);
        snakeLadderPositions.set(27,5);
        snakeLadderPositions.set(33,49);
        snakeLadderPositions.set(40,3);
        snakeLadderPositions.set(42,63);
        snakeLadderPositions.set(43,18);
        snakeLadderPositions.set(50,69);
        snakeLadderPositions.set(54,31);
        snakeLadderPositions.set(62,81);
        snakeLadderPositions.set(66,45);
        snakeLadderPositions.set(74,92);
        snakeLadderPositions.set(76,58);
        snakeLadderPositions.set(89,53);
        snakeLadderPositions.set(99,41);
    }

    int getNewPosition(int currentPosition){
        if(currentPosition >= 1 && currentPosition <= 100){
            return snakeLadderPositions.get(currentPosition);
        }
        return -1;
    }
    int getXCoordinate(int position){
        if(position >=1 && position <= 100){
            return positionCoordinates.get(position).getKey();
        }
        return -1;
    }

    int getYCoordinate(int position){
        if(position >=1 && position <= 100){
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }

//    public static void main(String[] args) {
//        Board b = new Board();
//        for (int i = 0; i < b.positionCoordinates.size(); i++) {
//            System.out.println(i+"$  X: "+b.positionCoordinates.get(i).getKey() +"  Y: "+b.positionCoordinates.get(i).getValue());
//        }
//    }
}
