package page;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class mainPage {
    static Scene mainScene() {
        Button buttonphase2 = new Button();
        Text header = new Text("Welcome to \nTwo-Three Musketeers\n Graph Coloring Game");
        header.setTextAlignment(TextAlignment.CENTER);
        buttonphase2.getStyleClass().add("button");
        header.getStyleClass().add("header");
        buttonphase2.setText("Play Phase 2");
        

        Button buttonPhase3 = new Button();
        buttonPhase3.getStyleClass().add("button");
        buttonPhase3.setText("Play Phase 3");

        buttonPhase3.setOnAction(e -> {
            App.changeUploadGraphScenePhase3();
        });
        buttonphase2.setOnAction(e -> {
            App.changeSelectGameScene();
        });

        // VBox instead of stackPane
        VBox root = new VBox(50);
        root.getChildren().addAll(header, buttonphase2, buttonPhase3);
        root.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(root, 900, 700);
        root.getStyleClass().add("scene");
        scene.getStylesheets().addAll("./css/style.css");

        return scene;
    }
}
