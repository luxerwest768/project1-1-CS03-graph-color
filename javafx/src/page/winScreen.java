package page;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class winScreen {
    public static Scene winScreenScene() {
        StackPane root = new StackPane();

        Label title = new Label("You Win!");
        title.getStyleClass().add("title");

        Label message = new Label("Congratulations! You Win! :)");
        message.getStyleClass().add("win-message");

        Button homeButton = new Button("Home");
        homeButton.getStyleClass().add("home-button");

        homeButton.setOnAction(e -> {
            App.changeMainScene();
        });

        VBox container = new VBox();
        container.getStyleClass().add("container");
        container.getChildren().addAll(message, homeButton,title);

        root.getChildren().addAll(container);

        Scene endScreenScene = new Scene(root, 900, 700);
        endScreenScene.getStylesheets().add("./css/winScreen.css");

        return endScreenScene;
    }
}
