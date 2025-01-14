package page;

import components.ModelDetection.ModelDetection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import components.UploadGraph.ImportGraph;
import components.convertTextGraph.ReadGraph;

public class uploadGraph {
    private static Stage primaryStage = new Stage();
    private static ImportGraph getGraph = new ImportGraph();

    public static Scene uploadGraphScene(int gamemode) {
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");

        Label label = new Label("No files selected.");
        label.setMaxWidth(Double.MAX_VALUE); // Allow full width
        label.getStyleClass().add("header");
        label.setStyle("-fx-wrap-text: true; -fx-font-size: 40px; -fx-alignment: center;");

        FileChooser graphImport = new FileChooser();
        Button btnImportGraph = new Button("Import graph");
        Button btnPlay = new Button("Generate");
        Button backButton = new Button("Back");
        Button createGraphBtn = new Button("Create graph");

        double buttonWidth = 300;
        double buttonHeight = 50;
        Button[] buttons = {btnImportGraph, btnPlay, backButton, createGraphBtn};
        for (Button button : buttons) {
            button.getStyleClass().add("button");
            button.setPrefSize(buttonWidth, buttonHeight);
        }

        createGraphBtn.setOnAction(e -> {
            App.changeCreateGraphScene(gamemode);
        });

        backButton.setOnAction(e -> {
            App.changeSelectGameScene();
        });

        EventHandler<ActionEvent> eventImport = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                File file = graphImport.showOpenDialog(primaryStage);

                if (file != null) {
                    try {
                        ReadGraph readGraph = new ReadGraph();
                        ModelDetection modelDetection = new ModelDetection();
                        int[][] graph = readGraph.convertTextGraph(file);
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
                        switch (gamemode) {
                            case 1:
                                App.changeToTheBitterEndScene(graph, CN);
                                break;
                            case 2:
                                App.changeRandomOrderScene(graph, CN);
                                break;
                            case 3:
                                App.changeIChangeMyMindScene(graph, CN);
                                break;
                        }
                    } else {
                        label.setText("You need to import a file!");
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
