package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.base.GameBoard;

public class Main extends Application {

    // setting up primary stage
    @Override
    public void start(Stage primaryStage){
        GameBoard board = new GameBoard();
        Parent root = board.createContent();
        primaryStage.setTitle("Monopoly");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("sample/img/icon.png"));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
