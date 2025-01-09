package page;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EndScreenBase {
    protected String titleText = "You Lost!";
    protected String messageText = "You actually couldn't color some circles..?";

    public Scene createEndScreen() {
        StackPane root = new StackPane();

        Label title = new Label(titleText);
        title.getStyleClass().add("title");

        Label message = new Label(messageText);
        message.getStyleClass().add("end-message");

        //Button tryAgainButton = new Button("Try again");
        //tryAgainButton.getStyleClass().addAll("button", "try-again");

        Button homeButton = new Button("Home");
        homeButton.getStyleClass().add("home");

        //tryAgainButton.setOnAction(e -> {
        //    App.changeGameScene();
        //});

        homeButton.setOnAction(e -> {
            App.changeMainScene();
        });

        VBox container = new VBox();
        container.getStyleClass().add("container");
        container.getChildren().addAll(message, homeButton,title);

        root.getChildren().addAll(container);

        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add("./css/endScreen.css");

        return scene;
    }
}
