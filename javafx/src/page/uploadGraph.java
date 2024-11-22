package page;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import components.convertTextGraph.ReadGraph;


import java.io.*;



public class uploadGraph {
    
    private static Stage primaryStage = new Stage();

    public static Scene uploadGraphScene(){
        StackPane root = new StackPane();
        Button btnImportGraph = new Button("Import graph");
        Button backButton = new Button("Back");
        FileChooser graphImport = new FileChooser();
        Label label = new Label("no files selected");

        label.getStyleClass().add("label-status");
        btnImportGraph.getStyleClass().add("btn-import");
        backButton.getStyleClass().add("back-button");

        backButton.setOnAction(e -> {
            App.changeSelectGameScene();
        });

        EventHandler<ActionEvent> eventImport = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e){
                    File file = graphImport.showOpenDialog(primaryStage);

                    if (file != null){
                        int[][] graph = ReadGraph.convertTextGraph(file);
                        label.setText(file.getAbsolutePath()+" selected");
                    }
                }
        };

        

        btnImportGraph.setOnAction(eventImport);

        root.getChildren().addAll(btnImportGraph,label,backButton);

        Scene testScene = new Scene(root,900,700);
        testScene.getStylesheets().add("./css/uploadGraph.css");

        return testScene;

    }
}
