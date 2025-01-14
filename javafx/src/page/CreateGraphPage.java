package page;

import components.ModelDetection.ModelDetection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.Random;
import components.convertTextGraph.ReadGraph;

public class CreateGraphPage {

    static Scene CreateGraph(int gamemode) {
        // Declares the headers 
        Text header = new Text("Create Graph");
        Text subheader = new Text("Specify the graph details");

        header.getStyleClass().add("header");
        subheader.getStyleClass().add("subheader");

        // Declares the container and items within for the input of the vertices
        HBox verticescon = new HBox(15); // Adjusted spacing
        verticescon.setAlignment(javafx.geometry.Pos.CENTER);
        Label verticetxt = new Label("Number of vertices");
        TextField verticeinput = new TextField();
        verticeinput.setPromptText("Type Here...");
        Button randbtn1 = new Button("Randomize");
        randbtn1.setPrefSize(200, 50); // Increased width for "Randomize"
        randbtn1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;"); // Adjusted font size
        verticescon.getChildren().addAll(verticetxt, verticeinput, randbtn1);

        // Declares the container and items within for the input of the edges
        HBox edgescon = new HBox(15); // Adjusted spacing
        edgescon.setAlignment(javafx.geometry.Pos.CENTER);
        Label edgestxt = new Label("Number of Edges");
        TextField edgesinput = new TextField();
        edgesinput.setPromptText("Type Here...");
        Button randbtn2 = new Button("Randomize");
        randbtn2.setPrefSize(200, 50); // Increased width for "Randomize"
        randbtn2.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;"); // Adjusted font size
        edgescon.getChildren().addAll(edgestxt, edgesinput, randbtn2);

        // Sets the function for the random buttons
        randbtn1.setOnAction(e -> {
            String number = Integer.toString(randomNumber());
            verticeinput.setText(number);
        });

        randbtn2.setOnAction(e -> {
            String number = Integer.toString(randomNumber());
            edgesinput.setText(number);
        });

        // Declares the back and create button
        Button createbtn = new Button("Create");
        createbtn.setPrefSize(200, 50);
        createbtn.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Button backbtn = new Button("Back");
        backbtn.setPrefSize(200, 50);
        backbtn.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Event handler for the "Create" button
        EventHandler<ActionEvent> changeScene = e -> {
            try {
                ReadGraph readGraph = new ReadGraph();
                ModelDetection modelDetection = new ModelDetection();
                if (Integer.valueOf(verticeinput.getText()) < 50 && Integer.valueOf(edgesinput.getText()) < 50) {
                    int[][] graph = readGraph.createGraph(
                            Integer.valueOf(verticeinput.getText()), 
                            Integer.valueOf(edgesinput.getText())
                    );
                    int CN = modelDetection.detectModel(graph) 
                            ? modelDetection.getCN() 
                            : readGraph.getCN();
                    if (graph != null) {
                        switch (gamemode) {
                            case 1 -> App.changeToTheBitterEndScene(graph, CN);
                            case 2 -> App.changeRandomOrderScene(graph, CN);
                            case 3 -> App.changeIChangeMyMindScene(graph, CN);
                        }
                    } else {
                        subheader.setText("Invalid Input!");
                    }
                } else {
                    subheader.setText("Invalid Input!: edges and vertices must be under 50");
                }
            } catch (NumberFormatException error) {
                subheader.setText("Invalid Input!");
            } catch (IllegalArgumentException error) {
                subheader.setText("There must be at least 1 vertex!");
            }
        };

        // Assign event handlers
        createbtn.setOnAction(changeScene);
        backbtn.setOnAction(e -> App.changeUploadGraphScene(gamemode));

        // Add components to the root layout
        VBox root = new VBox(20);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getChildren().addAll(header, subheader, verticescon, edgescon, createbtn, backbtn);
        root.setStyle("-fx-padding: 30;");

        // Create the scene
        Scene scene = new Scene(root, 900, 700);
        root.getStyleClass().add("scene");
        scene.getStylesheets().add("./css/style.css");

        return scene;
    }

    static int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(50);
    }
}
