package sample.base;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.service.TopOfBoard;
import sample.objects.Tile;


public class GameBoard {
    // Player variables
    public int numOfPlayers = 4;

    // Variables for game board dimension.
    private final int tileSize = 56;
    private final int dimension = 11;
    private final int menuWidth = 400;

    // Group for storing added tiles
    private final Group tileGroup = new Group();

    // main game board class. Generates bg tiles and adds images to them....
    public Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize((dimension * tileSize)+(tileSize*4)+menuWidth, (dimension * tileSize)+(tileSize*4));
        root.getChildren().addAll(tileGroup);
        TopOfBoard top = new TopOfBoard();
        Parent controlsAndMovement = top.newScene();
        root.getChildren().addAll(controlsAndMovement);

        // setting main game board template
        for (int i = 0; i< dimension; i++){
            for (int j = 0; j< dimension; j++){
                if (i==0||i== dimension -1||j==0||j== dimension -1){
                    if (i==0) {
                        if (j==0){
                            tileInit(i, j, tileGroup, tileSize * 2, tileSize * 2, j * tileSize, i * tileSize);
                        }
                        else if (j== dimension -1){
                            tileInit(i, j, tileGroup, tileSize*2, tileSize*2, (j * tileSize)+tileSize, i * tileSize);
                        }
                        else {
                            tileInit(i, j, tileGroup, tileSize*2, tileSize, (j * tileSize)+tileSize, i * tileSize);
                        }
                    }
                    else if (i== dimension -1){
                        if (j==0){
                            tileInit(i, j, tileGroup, tileSize*2, tileSize*2, j * tileSize, (i * tileSize)+tileSize);
                        }
                        else if (j== dimension -1){
                            tileInit(i, j, tileGroup, tileSize*2, tileSize*2, (j * tileSize)+tileSize, (i * tileSize)+tileSize);
                        }
                        else {
                            tileInit(i, j, tileGroup, tileSize * 2, tileSize, (j * tileSize) + tileSize, (i * tileSize) + tileSize);
                        }
                    }
                    else if (j==0){
                        tileInit(i, j, tileGroup, tileSize, tileSize*2, j * tileSize, (i * tileSize)+(tileSize));

                    }
                    else if (j== dimension -1) {
                        tileInit(i, j, tileGroup, tileSize, tileSize*2, (j * tileSize)+(tileSize), (i * tileSize)+tileSize);
                    }

                }
            }

            // Setting player score board
            for (int q = 1; q <= numOfPlayers; q++){
                if (q == 1){
                    Tile tile = new Tile((getTileSize()*2), (getTileSize()*4));
                    tile.setTranslateX((getTileSize()* getDimension())+(getTileSize()*3));
                    tile.setTranslateY(getTileSize()*3);
                    Text text = new Text((getTileSize()* getDimension())+(getTileSize()*4), (getTileSize()*3.5), "Player 1 score board:");
                    tileGroup.getChildren().addAll(tile, text);
                }
                else if (q == 2){
                    Tile tile = new Tile((getTileSize()*2), (getTileSize()*4));
                    tile.setTranslateX((getTileSize()* getDimension())+(getTileSize()*3));
                    tile.setTranslateY(getTileSize()*5);
                    Text text = new Text((getTileSize()* getDimension())+(getTileSize()*4), (getTileSize()*5.5), "Player 2 score board:");
                    tileGroup.getChildren().addAll(tile, text);
                }
                else if (q == 3){
                    Tile tile = new Tile((getTileSize()*2), (getTileSize()*4));
                    tile.setTranslateX((getTileSize()* getDimension())+(getTileSize()*3));
                    tile.setTranslateY(getTileSize()*7);
                    Text text = new Text((getTileSize()* getDimension())+(getTileSize()*4), (getTileSize()*7.5), "Player 3 score board:");
                    tileGroup.getChildren().addAll(tile, text);
                }
                else if (q == 4){
                    Tile tile = new Tile((getTileSize()*2), (getTileSize()*4));
                    tile.setTranslateX((getTileSize()* getDimension())+(getTileSize()*3));
                    tile.setTranslateY(getTileSize()*9);
                    Text text = new Text((getTileSize()* getDimension())+(getTileSize()*4), (getTileSize()*9.5), "Player 4 score board:");
                    tileGroup.getChildren().addAll(tile, text);
                }

            }

            for (int r = 1; r <= numOfPlayers; r++) {
                if (r == 1) {
                    Tile tile = new Tile((getTileSize() * 3), (getTileSize() * 4));
                    tile.setTranslateX((getTileSize() * getDimension()) + (getTileSize() * 7));
                    tile.setTranslateY(0);
                    Text text = new Text((getTileSize() * getDimension()) + (getTileSize() * 8), (getTileSize() * 0.5), "Player 1 owned cards:");
                    tileGroup.getChildren().addAll(tile, text);
                } else if (r == 2) {
                    Tile tile = new Tile((getTileSize() * 3), (getTileSize() * 4));
                    tile.setTranslateX((getTileSize() * getDimension()) + (getTileSize() * 7));
                    tile.setTranslateY(getTileSize() * 3);
                    Text text = new Text((getTileSize() * getDimension()) + (getTileSize() * 8), (getTileSize() * 3.5), "Player 2 owned cards:");
                    tileGroup.getChildren().addAll(tile, text);
                } else if (r == 3) {
                    Tile tile = new Tile((getTileSize() * 3), (getTileSize() * 4));
                    tile.setTranslateX((getTileSize() * getDimension()) + (getTileSize() * 7));
                    tile.setTranslateY(getTileSize() * 6);
                    Text text = new Text((getTileSize() * getDimension()) + (getTileSize() * 8), (getTileSize() * 6.5), "Player 3 owned cards:");
                    tileGroup.getChildren().addAll(tile, text);
                } else if (r == 4) {
                    Tile tile = new Tile((getTileSize() * 3), (getTileSize() * 4));
                    tile.setTranslateX((getTileSize() * getDimension()) + (getTileSize() * 7));
                    tile.setTranslateY(getTileSize() * 9);
                    Text text = new Text((getTileSize() * getDimension()) + (getTileSize() * 8), (getTileSize() * 9.5), "Player 4 owned cards:");
                    tileGroup.getChildren().addAll(tile, text);

                }
            }
            addImg();
        }
        return root;
    }

    // refactored code for repeating patterns
    private static void tileInit(int i, int j, Group tileGroup, int i2, int i3, int i4, int i5) {
        Tile tile = new Tile(i, j, (i2), (i3));
        tile.setTranslateX((i4));
        tile.setTranslateY(i5);
        tileGroup.getChildren().add(tile);
    }

    // Add bg images for basic layout...
    public void addImg(){
        // Add other bg images
        Image board = new Image("sample/img/board.jpg");
        ImageView boardImg = new ImageView();
        boardImg.setImage(board);
        boardImg.setFitHeight((tileSize* dimension)+ (tileSize*2) );
        boardImg.setFitWidth( (tileSize* dimension)+ (tileSize*2) );

        Tile infoTile = new Tile(getTileSize(), getTileSize()*7);
        infoTile.setTranslateX(getTileSize()*3);
        infoTile.setTranslateY(getTileSize()*6);

        tileGroup.getChildren().addAll(boardImg, infoTile);

    }

    // Getters and setters
    public int getTileSize() {
        return tileSize;
    }

    public int getDimension() {
        return dimension;
    }

    public int getMenuWidth() {
        return menuWidth;
    }

}
