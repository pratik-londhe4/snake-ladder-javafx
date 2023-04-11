package com.example.snake_ladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile(int tileSize){
        setHeight(tileSize);
        setWidth(tileSize);
        setFill(Color.GREENYELLOW);
        setStroke(Color.BLACK);
    }
}
