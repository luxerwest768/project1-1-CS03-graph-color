package page;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.control.Button;


public class winScreen {

    public static Scene winScreenScene(int gameMode, int CN, int score) {

        StackPane pane = new StackPane();


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
        gameModeLabel.getStyleClass().add("header");

        Label winMessage = new Label("You Win!");
        winMessage.getStyleClass().add("subheader");

        Label scoreLabel = new Label("Your score is: " + score);
        scoreLabel.setFont(new Font(20));
        scoreLabel.setStyle("-fx-translate-y: 50");
        scoreLabel.getStyleClass().add("text");

        Label chromaticNumberLabel = new Label("Chromatic number: " + CN);
        chromaticNumberLabel.setFont(new Font(20));
        chromaticNumberLabel.getStyleClass().add("text");
        chromaticNumberLabel.setLayoutX(350);
        chromaticNumberLabel.setLayoutY(250);

        Button homeButton = new Button("Home");
        homeButton.getStyleClass().add("button");
        homeButton.setStyle("-fx-translate-y: 20px");
        homeButton.setOnAction(event -> {
            App.changeMainScene();
        });

        pane.getChildren().addAll(gameModeLabel, scoreLabel, chromaticNumberLabel, homeButton,winMessage);


        Scene scene = new Scene(pane, 800, 600);
        scene.getStylesheets().add("./css/style.css");

        return scene;
    }

}
