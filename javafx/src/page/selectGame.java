package page;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class selectGame {
    public static Scene selectGameScene() {

        Button toTheBitterEnd = new Button("To the Bitter End");
        Button randomColor = new Button("Random Color");
        Button iChangeMyMind = new Button("I Change My Mind");
        Button moveBack = new Button("Back");

        toTheBitterEnd.getStyleClass().add("button");
        randomColor.getStyleClass().add("button");
        iChangeMyMind.getStyleClass().add("button");
        moveBack.getStyleClass().add("button");

        //button size here
        double buttonWidth = 400;
        double buttonHeight = 50;
        toTheBitterEnd.setPrefSize(buttonWidth, buttonHeight);
        randomColor.setPrefSize(buttonWidth, buttonHeight);
        iChangeMyMind.setPrefSize(buttonWidth, buttonHeight);
        moveBack.setPrefSize(buttonWidth, buttonHeight);

        toTheBitterEnd.setOnAction(e -> App.changeUploadGraphScene(1));
        randomColor.setOnAction(e -> App.changeUploadGraphScene(2));
        iChangeMyMind.setOnAction(e -> App.changeUploadGraphScene(3));
        moveBack.setOnAction(e -> App.changeMainScene());

        Text header = new Text("Select Game Mode");
        header.getStyleClass().add("header");

        VBox root = new VBox(35); 
        root.setAlignment(Pos.CENTER); 
        root.getChildren().addAll(header, toTheBitterEnd, randomColor, iChangeMyMind, moveBack);

        Scene scene = new Scene(root, 900, 700);
        root.getStyleClass().add("scene");
        scene.getStylesheets().addAll("./css/style.css");

        return scene;
    }
}
