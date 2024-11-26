package page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class endScreen {
    private static Stage primaryStage = new Stage();

    public static Scene endScreenScene() {
        StackPane root = new StackPane();

        Label title = new Label("You lost!");
        title.getStyleClass().add("title");

        Label message = new Label("You did not finish coloring the graph :(");
        message.getStyleClass().add("message");

        Button tryAgainButton = new Button("Try again");
        tryAgainButton.getStyleClass().addAll("button", "try-again-button");

        Button homeButton = new Button("Home");
        homeButton.getStyleClass().addAll("button", "home-button");

        Button leaderboardButton = new Button("Leaderboard");
        leaderboardButton.getStyleClass().addAll("button", "leaderboard-button");

/*       tryAgainButton.setOnAction(e -> {
            App.changeModeOneScene(); 
        }); */ 

        homeButton.setOnAction(e -> {
            App.changeMainScene(); 
        });

        /*leaderboardButton.setOnAction(e -> {
            App.changeLeaderboardScene(); 
        }); */ 

        VBox container = new VBox();
        container.getStyleClass().add("container");
        container.getChildren().addAll(message, tryAgainButton, homeButton, leaderboardButton);

        root.getChildren().addAll(title, container);

        Scene endScreenScene = new Scene(root, 900, 700);
        endScreenScene.getStylesheets().add("./css/endScreen.css");

        return endScreenScene;
    }
}
