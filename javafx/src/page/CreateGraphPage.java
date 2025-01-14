package page;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Random;

import components.convertTextGraph.ReadGraph;

public class CreateGraphPage {

    static Scene CreateGraph(int gamemode) {

        VBox root = new VBox(20); 
        root.setStyle("-fx-alignment: center; -fx-padding: 30;");

        Text header = new Text("Create Graph");
        header.getStyleClass().add("header");

        Text subheader = new Text("Specify the graph details");
        subheader.getStyleClass().add("subheader"); 

        HBox verticescon = new HBox(10); 
        verticescon.setAlignment(Pos.CENTER);
        Text verticetxt = new Text("Number of vertices");
        verticetxt.getStyleClass().add("text"); 
        TextField verticeinput = createPlaceholderTextField("Type Here...");
        Button randbtn1 = createSizedButton("Randomize");
        randbtn1.getStyleClass().add("buttonSmall");
        verticescon.getChildren().addAll(verticetxt, verticeinput, randbtn1);

        HBox edgescon = new HBox(10); 
        edgescon.setAlignment(Pos.CENTER);
        Text edgestxt = new Text("Number of Edges");
        edgestxt.getStyleClass().add("text"); 
        TextField edgesinput = createPlaceholderTextField("Type Here...");
        Button randbtn2 = createSizedButton("Randomize");
        randbtn2.getStyleClass().add("buttonSmall");
        edgescon.getChildren().addAll(edgestxt, edgesinput, randbtn2);

        randbtn1.setOnAction(e -> verticeinput.setText(String.valueOf(randomNumber())));
        randbtn2.setOnAction(e -> edgesinput.setText(String.valueOf(randomNumber())));

        Button createbtn = createSizedButton("Create");
        createbtn.setOnAction(e -> {
            try {
                ReadGraph readGraph = new ReadGraph();
                int vertices = Integer.parseInt(verticeinput.getText());
                int edges = Integer.parseInt(edgesinput.getText());

                if (vertices < 50 && edges < 50) {
                    int[][] graph = readGraph.createGraph(vertices, edges);
                    if (graph != null) {
                        int CN = readGraph.getCN();
                        switch (gamemode) {
                            case 1 -> App.changeToTheBitterEndScene(graph, CN);
                            case 2 -> App.changeRandomOrderScene(graph, CN);
                            case 3 -> App.changeIChangeMyMindScene(graph, CN);
                        }
                    } else {
                        subheader.setText("Invalid Input!");
                    }
                } else {
                    subheader.setText("Invalid Input: edges and vertices must be under 50");
                }
            } catch (NumberFormatException error) {
                subheader.setText("Invalid Input!");
            } catch (IllegalArgumentException error) {
                subheader.setText("There must be at least 1 vertex!");
            }
        });

        Button backbtn = createSizedButton("Back");
        backbtn.setOnAction(e -> App.changeUploadGraphScene(gamemode));

        root.getChildren().addAll(header, subheader, verticescon, edgescon, createbtn, backbtn);

        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().addAll("./css/style.css");

        return scene;
    }

    static TextField createPlaceholderTextField(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder); 
        textField.setPrefSize(200, 40); 
        textField.setStyle("-fx-font-size: 14px;"); 
        return textField;
    }

    static Button createSizedButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(150, 50); 
        button.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        return button;
    }

    static int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(50);
    }
}
