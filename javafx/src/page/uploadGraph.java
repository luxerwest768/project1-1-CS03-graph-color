package page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

import components.UploadGraph.ImportGraph;
import components.convertTextGraph.ReadGraph;




public class uploadGraph {
    private static Stage primaryStage = new Stage();
    private static  ImportGraph getGraph = new ImportGraph();


    public static Scene uploadGraphScene(){
        StackPane root = new StackPane();
        Button btnImportGraph = new Button("Import graph");
        Button btnPlay = new Button("Generate");
        Button backButton = new Button("Back");
        FileChooser graphImport = new FileChooser();
        Label label = new Label("no files selected");

        label.getStyleClass().add("label-status");
        btnImportGraph.getStyleClass().add("btn-import");
        btnPlay.getStyleClass().add("btn-play");
        backButton.getStyleClass().add("back-button");


        backButton.setOnAction(e -> {
            App.changeSelectGameScene();
        });

        EventHandler<ActionEvent> eventImport = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e){
                    File file = graphImport.showOpenDialog(primaryStage);

                    if (file != null){
                        int[][] graph = ReadGraph.convertTextGraph(file);
                        getGraph.setGraph(graph);
                        label.setText(file.getAbsolutePath()+" selected");
                    }
                }
        };
        
        EventHandler<ActionEvent> changeScene = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                int[][] graph = getGraph.getGraph();
                try {
                    if (graph != null){
                        App.changeRenderGraphScene(graph);
                    } else {
                        label.setText("You need to import file!");
                    }
                } catch (NullPointerException error){
                    System.out.println("Invalid file"+error);
                }
                
            }
        };

        btnImportGraph.setOnAction(eventImport);
        btnPlay.setOnAction(changeScene);
        


        root.getChildren().addAll(btnImportGraph,label,backButton,btnPlay);

        Scene uploadGraphScene = new Scene(root,900,700);
        uploadGraphScene.getStylesheets().add("./css/uploadGraph.css");

        return uploadGraphScene;
    }

}
