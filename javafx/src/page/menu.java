package page;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class menu {
    static Scene menuPage(){
        Button playBtn = new Button("Play");
        Button leadBoardBtn = new Button("LeaderBoards");
        Button moveBack = new Button("Back");
        Text txt = new Text("                        Welcome to \n Two-Three Musketeers - Graph Coloring Game");
        playBtn.getStyleClass().add("button, play-button");
        leadBoardBtn.getStyleClass().add("button, leaderBoard-button");
        moveBack.getStyleClass().add("button, back-button");
        txt.getStyleClass().add("txt");

        playBtn.setOnAction(e -> {
            App.changeSelectGameScene();
        });

        moveBack.setOnAction(e -> {
            App.changeMainScene();
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(txt,playBtn,leadBoardBtn,moveBack);
        Scene scene = new Scene(root,900,700);
        scene.getStylesheets().addAll("./css/menuPage.css");

        return scene;
    }
}
