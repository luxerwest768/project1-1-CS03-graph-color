package page;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class endScreen {

    public static Scene endScreenScene() {
        StackPane root = new StackPane();

        Label title = new Label("You lost!");
        title.getStyleClass().add("title");

        Label message = new Label("You did not finish coloring the graph :(");
        message.getStyleClass().add("end-message");

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
        endScreenScene.getStylesheets().add("./css/endScreen.css");

        return endScreenScene;
    }
}
