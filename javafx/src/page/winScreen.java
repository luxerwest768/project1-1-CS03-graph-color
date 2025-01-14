package page;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class winScreen {

    public static Scene winScreenScene(int gameMode, int CN, int score) {

        VBox pane = new VBox(20); // Use VBox with 20px spacing
        pane.setStyle("-fx-alignment: center; -fx-padding: 30; -fx-alignment: center;"); // Center alignment and padding

        String gameModeTitle = "To the Bitter End";
        switch (gameMode) {
            case 1:
                gameModeTitle = "Random Order";
                break;
            case 2:
                gameModeTitle = "I Change My Mind";
                break;
        }

        Label gameModeLabel = new Label(gameModeTitle);
        gameModeLabel.setFont(new Font(30));
        gameModeLabel.setMaxWidth(Double.MAX_VALUE); // Allow full width
        gameModeLabel.setStyle("-fx-wrap-text: true; -fx-text-alignment: center; -fx-alignment: center;");
        gameModeLabel.getStyleClass().add("header");

        Label winMessage = new Label("You Win!");
        winMessage.setFont(new Font(24));
        winMessage.setMaxWidth(Double.MAX_VALUE); // Allow full width
        winMessage.setStyle("-fx-wrap-text: true; -fx-text-alignment: center; -fx-alignment: center;");
        winMessage.getStyleClass().add("subheader");

        Label scoreLabel = new Label("Your score is: " + score);
        scoreLabel.setFont(new Font(20));
        scoreLabel.setMaxWidth(Double.MAX_VALUE); // Allow full width
        scoreLabel.setStyle("-fx-wrap-text: true; -fx-text-alignment: center; -fx-alignment: center;");
        scoreLabel.getStyleClass().add("text");

        Label chromaticNumberLabel = new Label("Chromatic number: " + CN);
        chromaticNumberLabel.setFont(new Font(20));
        chromaticNumberLabel.setMaxWidth(Double.MAX_VALUE); // Allow full width
        chromaticNumberLabel.setStyle("-fx-wrap-text: true; -fx-text-alignment: center; -fx-alignment: center;");
        chromaticNumberLabel.getStyleClass().add("text");

        Button homeButton = new Button("Home");
        homeButton.getStyleClass().add("button");
        homeButton.setOnAction(event -> {
            App.changeMainScene();
        });

        pane.getChildren().addAll(
            gameModeLabel,
            winMessage,
            scoreLabel,
            chromaticNumberLabel,
            homeButton
        );

        Scene scene = new Scene(pane, 800, 600);
        pane.getStyleClass().add("scene");
        scene.getStylesheets().add("./css/style.css");

        return scene;
    }
}