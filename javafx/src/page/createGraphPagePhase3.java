package page;

import components.convertTextGraph.chromaticNumber;
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

public class createGraphPagePhase3 {
    private static chromaticNumber solution = new chromaticNumber();

    static Scene createGraphPhase3() {
        // Declares the headers 
        Text header = new Text("Create Graph");
        Text subheader = new Text("Specify the graph details");

        header.getStyleClass().add("header");
        subheader.getStyleClass().add("subheader");

        // Declares the container and items within for the input of the vertices
        HBox verticescon = new HBox(10);
        verticescon.setAlignment(javafx.geometry.Pos.CENTER);
        verticescon.setMaxWidth(800);
        verticescon.setMaxHeight(50);
        Label verticetxt = new Label("Number of vertices");
        TextField verticeinput = new TextField();
        verticeinput.setPromptText("Type Here...");
        Button randbtn1 = new Button("Randomize");
        randbtn1.getStyleClass().add("button");
        verticescon.getChildren().addAll(verticetxt, verticeinput, randbtn1);

        verticescon.getStyleClass().add("containerV");

        // Declares the container and items within for the input of the edges
        HBox edgescon = new HBox(10);
        edgescon.setAlignment(javafx.geometry.Pos.CENTER);
        edgescon.setMaxWidth(800);
        edgescon.setMaxHeight(50);
        Label edgestxt = new Label("Number of Edges");
        TextField edgesinput = new TextField();
        edgesinput.setPromptText("Type Here...");

        Button randbtn2 = new Button("Randomize");
                randbtn2.setPrefSize(200, 50);
        randbtn2.getStyleClass().add("button");

        edgescon.getChildren().addAll(edgestxt, edgesinput, randbtn2);

        edgescon.getStyleClass().add("containerE");

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
        createbtn.getStyleClass().add("button");
        Button backbtn = new Button("Back");
        backbtn.getStyleClass().add("button");

        backbtn.setPrefSize(150, 50); 
        backbtn.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        randbtn1.setPrefSize(160, 50); 
        randbtn1.setStyle("-fx-font-size: 9px; -fx-font-weight: bold;");
        randbtn2.setPrefSize(160, 50); 
        randbtn2.setStyle("-fx-font-size: 9px; -fx-font-weight: bold;");
        createbtn.setPrefSize(150, 50); 
        createbtn.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        EventHandler<ActionEvent> changeScene = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    ReadGraph readGraph = new ReadGraph();
                    if (Integer.valueOf(verticeinput.getText()) < 0 || Integer.valueOf(edgesinput.getText()) < 0) {
                        subheader.setText("Invalid Input!: edges and vertices cannot be negative");
                    } else {
                        int[][] graph = ReadGraph.createGraph(Integer.valueOf(verticeinput.getText()), Integer.valueOf(edgesinput.getText()));
                        int CN = readGraph.getCN();
                        solution = readGraph.getChromaticNumber();
                        if (graph != null) {
                            App.changeRenderGraphScenePhase3(graph, solution);
                        } else {
                            subheader.setText("Invalid Input!");
                        }
                    }
                } catch (NumberFormatException error) {
                    subheader.setText("Invalid Input!");
                } catch (IllegalArgumentException error) {
                    subheader.setText("There has to be at least 1 vertex!");
                }

            }
        };

        // Sets the text and the function of the create button
        createbtn.setOnAction(changeScene);

        // Sets the text and the function of the back button
        backbtn.setOnAction(e -> {
            App.changeUploadGraphScenePhase3();
        });

        VBox root = new VBox(20);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getChildren().addAll(header, subheader, verticescon, edgescon, createbtn, backbtn);
        root.setStyle("-fx-padding: 30;");

        Scene scene = new Scene(root, 900, 700);
        root.getStyleClass().add("scene");
        scene.getStylesheets().addAll("./css/style.css");

        return scene;
    }

    static int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(50);
    }
}
