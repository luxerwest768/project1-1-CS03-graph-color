package page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import components.UploadGraph.ImportGraph;
import components.convertTextGraph.ReadGraph;

public class uploadGraphPhase3 {
    private static Stage primaryStage = new Stage();
    private static ImportGraph getGraph = new ImportGraph();

    public static Scene uploadGraphScenePhase3() {

        VBox root = new VBox(35); 
        root.setStyle("-fx-alignment: center;"); 

        Button btnImportGraph = new Button("Import graph");
        Button btnPlay = new Button("Generate");
        Button backButton = new Button("Back");
        Button createGraphBtn = new Button("Create graph");
        Text statusText = new Text("No files selected"); 
        FileChooser graphImport = new FileChooser();

        statusText.getStyleClass().add("header");

        double buttonWidth = 400;
        double buttonHeight = 50;
        Button[] buttons = {btnImportGraph, btnPlay, backButton, createGraphBtn};
        for (Button button : buttons) {
            button.getStyleClass().add("button"); 
            button.setPrefSize(buttonWidth, buttonHeight); 
        }

        createGraphBtn.setOnAction(e -> App.changeCreateGraphScenePhase3());
        backButton.setOnAction(e -> App.changeMainScene());

        btnImportGraph.setOnAction(e -> {
            File file = graphImport.showOpenDialog(primaryStage);

            if (file != null) {
                try {
                    ReadGraph readGraph = new ReadGraph();
                    int[][] graph = readGraph.convertTextGraph(file);
                    getGraph.setChromaticNum(readGraph.getCN());
                    getGraph.setGraph(graph);
                    statusText.setText(file.getAbsolutePath() + " selected");
                } catch (NegativeArraySizeException error) {
                    statusText.setText("Invalid file");
                }
            }
        });

        btnPlay.setOnAction(e -> {
            int[][] graph = getGraph.getGraph();
            int CN = getGraph.getChromaticNum();

            try {
                if (graph != null) {
                    App.changeRenderGraphScenePhase3(graph);
                } else {
                    statusText.setText("You need to import a file!");
                }
            } catch (NullPointerException error) {
                System.out.println("Invalid file " + error);
            }
        });

        root.getChildren().addAll(statusText, btnImportGraph, btnPlay, createGraphBtn, backButton);

        Scene uploadGraphScene = new Scene(root, 900, 700);
        root.getStyleClass().add("scene");
        uploadGraphScene.getStylesheets().add("./css/style.css");

        return uploadGraphScene;
    }
}
