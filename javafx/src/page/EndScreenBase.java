package page;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class EndScreenBase {
    protected String titleText = "You Lost!";
    protected String messageText = "You actually couldn't color some circles..?";

    public Scene createEndScreen() {
        Text header = new Text(titleText);
        header.setTextAlignment(TextAlignment.CENTER);
        header.getStyleClass().add("header");

        Text subheader = new Text(messageText);
        subheader.setTextAlignment(TextAlignment.CENTER);
        subheader.getStyleClass().add("subheader");

        Button homeButton = new Button("Home");
        homeButton.getStyleClass().add("button");
        homeButton.setOnAction(e -> {
            App.changeMainScene();
        });

        VBox root = new VBox(30);
        root.getChildren().addAll(header, subheader, homeButton);
        root.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(root, 900, 700);
        root.getStyleClass().add("scene");
        scene.getStylesheets().addAll("./css/style.css");

        return scene;
    }
}
