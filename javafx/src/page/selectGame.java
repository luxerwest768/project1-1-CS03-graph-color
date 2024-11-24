package page;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class selectGame {
    public static Scene selectGameScene(){
        Button toTheBitterEnd = new Button();
        Button randomColor = new Button();
        Button iChangeMyMind = new Button();
        Button moveBack = new Button(); 

        Text txt = new Text("Select Game Mode");
        txt.getStyleClass().add("txt");

       toTheBitterEnd.setText("To the Bitter End");
       toTheBitterEnd.getStyleClass().add("to-the-bitter-end");
       toTheBitterEnd.setOnAction(e -> {
            App.changeUploadGraphScene();
       });
        
       randomColor.setText("Random Color");
       randomColor.getStyleClass().add("random-color");
       randomColor.setOnAction(e -> {
            App.changeUploadGraphScene();
        }); 

       iChangeMyMind.setText("I Change My Mind");
       iChangeMyMind.getStyleClass().add("i-change-my-mind");
       iChangeMyMind.setOnAction(e -> {
            App.changeUploadGraphScene();
        });

        moveBack.setText("Back");
        moveBack.getStyleClass().add("back-button");
        moveBack.setOnAction(e -> {
            App.changeMenuScene();
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(txt,moveBack,toTheBitterEnd,randomColor,iChangeMyMind);
        Scene scene = new Scene(root,900,700);
        scene.getStylesheets().addAll("./css/selectGame.css");

        return scene;
    }
}
