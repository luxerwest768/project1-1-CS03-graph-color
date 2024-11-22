package page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class menu {
    static Scene menuPage(){
        Button playBtn = new Button();
        Button leadBoardBtn = new Button();
        Button moveBack = new Button();
        Text txt = new Text("                        Welcome to \n Two-Three Musketeers - Graph Coloring Game");
        playBtn.getStyleClass().add("play-button");
        leadBoardBtn.getStyleClass().add("leaderBoard-button");
        moveBack.getStyleClass().add("back-button");
        txt.getStyleClass().add("txt");

        playBtn.setText("Play");
        playBtn.setOnAction(e -> {
            App.changeSelectGameScene();
        });

        leadBoardBtn.setText("LeaderBoards");

        moveBack.setText("Back");
        moveBack.setOnAction(e -> {
            App.changeMainScene();
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(txt,playBtn,leadBoardBtn,moveBack);
        Scene scene = new Scene(root,1300,1000);
        scene.getStylesheets().addAll("./css/menuPage.css");

        return scene;
    }
}
