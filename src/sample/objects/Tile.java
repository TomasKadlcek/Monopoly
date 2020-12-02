package sample.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile(int x, int y, int height, int width) {
        // maybe safe delete xPos
        setWidth(width);
        setHeight(height);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
    }

    public Tile (int height, int width){
        setWidth(width);
        setHeight(height);
        setFill(Color.GRAY);
        setStroke(Color.BLACK);
    }

}
