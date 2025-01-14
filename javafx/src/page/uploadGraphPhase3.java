package page;

import components.ModelDetection.ModelDetection;
import components.convertTextGraph.chromaticNumber;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import components.UploadGraph.ImportGraph;
import components.convertTextGraph.ReadGraph;

public class uploadGraphPhase3 {
    private static Stage primaryStage = new Stage();
    private static ImportGraph getGraph = new ImportGraph();
    private static ModelDetection modelDetection = new ModelDetection();
    private static chromaticNumber solution = new chromaticNumber();

    public static Scene uploadGraphScenePhase3() {
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");



        Label label = new Label("No files selected.");
        FileChooser graphImport = new FileChooser();
        Button btnImportGraph = new Button("Import graph");
        Button btnPlay = new Button("Generate");
        Button backButton = new Button("Back");
        Button createGraphBtn = new Button("Create graph");

        label.setMaxWidth(Double.MAX_VALUE); 
        label.setStyle("-fx-wrap-text: true; -fx-font-size: 40px; -fx-alignment: center;");

        label.getStyleClass().add("header");
        btnImportGraph.getStyleClass().add("button");
        btnPlay.getStyleClass().add("button");
        backButton.getStyleClass().add("button");
        createGraphBtn.getStyleClass().add("button");

        double buttonWidth = 300;
        double buttonHeight = 50;
        Button[] buttons = {btnImportGraph, btnPlay, backButton, createGraphBtn};
        for (Button button : buttons) {
            button.getStyleClass().add("button"); 
            button.setPrefSize(buttonWidth, buttonHeight); 
        }

        createGraphBtn.setOnAction(e -> {
            App.changeCreateGraphScenePhase3();
        });

        backButton.setOnAction(e -> {
            App.changeMainScene();
        });

        EventHandler<ActionEvent> eventImport = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                File file = graphImport.showOpenDialog(primaryStage);
                if (file != null) {
                    try {
                        ReadGraph readGraph = new ReadGraph();
                        int[][] graph = readGraph.convertTextGraph(file);
                        solution = readGraph.getChromaticNumber();
                        if (modelDetection.detectModel(graph)) {
                            getGraph.setChromaticNum(modelDetection.getCN());
                        } else {
                            getGraph.setChromaticNum(readGraph.getCN());
                        }
                        getGraph.setGraph(graph);
                        label.setText(file.getAbsolutePath() + " selected");
                    } catch (NegativeArraySizeException erro) {
                        label.setText("Invalid file");
                    }
                }
            }
        };

        EventHandler<ActionEvent> changeScene = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                int[][] graph = getGraph.getGraph();
                int CN = getGraph.getChromaticNum();
                try {
                    if (graph != null) {
                        App.changeRenderGraphScenePhase3(graph, solution);
                    } else {
                        label.setText("You need to import file!");
                    }
                } catch (NullPointerException error) {
                    System.out.println("Invalid file " + error);
                }
            }
        };

        btnImportGraph.setOnAction(eventImport);
        btnPlay.setOnAction(changeScene);

        root.getChildren().addAll(
            label,
            btnImportGraph,
            createGraphBtn,
            btnPlay,
            backButton
        );

        Scene uploadGraphScene = new Scene(root, 900, 700);
        root.getStyleClass().add("scene");
        uploadGraphScene.getStylesheets().add("./css/style.css");

        return uploadGraphScene;
    }
}
