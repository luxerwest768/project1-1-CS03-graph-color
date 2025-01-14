package page;

import components.convertTextGraph.chromaticNumber;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

import components.convertTextGraph.ReadGraph;

public class createGraphPagePhase3 {
    private static chromaticNumber solution = new chromaticNumber();
    
    static Scene createGraphPhase3() {

        VBox root = new VBox(35); 
        root.setStyle("-fx-alignment: center; -fx-padding: 30;");

        Text header = new Text("Create Graph");
        header.getStyleClass().add("header");
        header.setFont(Font.font("Arial", 40)); 
        Text subheader = new Text("Specify the graph details");
        subheader.getStyleClass().add("subheader");
        subheader.setFont(Font.font("Arial", 20));

        HBox verticescon = new HBox(10); 
        verticescon.setAlignment(Pos.CENTER); 
        Text verticetxt = new Text("Number of vertices");
        verticetxt.getStyleClass().add("text");
        verticetxt.setFont(Font.font("Arial", 16)); 
        TextField verticeinput = createPlaceholderTextField("Type Here...");
        Button randbtn1 = createSizedButton("Randomize");
        verticescon.getChildren().addAll(verticetxt, verticeinput, randbtn1);

        HBox edgescon = new HBox(10); 
        edgescon.setAlignment(Pos.CENTER); 
        Text edgestxt = new Text("Number of Edges");
        edgestxt.getStyleClass().add("text");
        edgestxt.setFont(Font.font("Arial", 16)); 
        TextField edgesinput = createPlaceholderTextField("Type Here...");
        Button randbtn2 = createSizedButton("Randomize");
        edgescon.getChildren().addAll(edgestxt, edgesinput, randbtn2);

        randbtn1.setOnAction(e -> verticeinput.setText(String.valueOf(randomNumber())));
        randbtn2.setOnAction(e -> edgesinput.setText(String.valueOf(randomNumber())));

        Button createbtn = createSizedButton("Create");
        createbtn.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        createbtn.setOnAction(e -> {
            try {
                ReadGraph readGraph = new ReadGraph();
                int vertices = Integer.parseInt(verticeinput.getText());
                int edges = Integer.parseInt(edgesinput.getText());

        randbtn2.setOnAction(e -> {
            String number = Integer.toString(randomNumber());
            edgesinput.setText(number);
        });

        // Declares the back and create button
        Button createbtn = new Button();
        Button backbtn = new Button();

        createbtn.getStyleClass().add("crbutton");
        backbtn.getStyleClass().add("backbutton");

        EventHandler<ActionEvent> changeScene = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                try {
                    ReadGraph readGraph = new ReadGraph();
                    if (Integer.valueOf(verticeinput.getText()) < 0 || Integer.valueOf(edgesinput.getText()) < 0){
                        subheader.setText("Invalid Input!: edges and vertices cam not be negative");
                    } else {
                        int[][] graph = ReadGraph.createGraph(Integer.valueOf(verticeinput.getText()), Integer.valueOf(edgesinput.getText()));
                        int CN = readGraph.getCN();
                        solution = readGraph.getChromaticNumber();
                        if (graph != null){
                            App.changeRenderGraphScenePhase3(graph, solution);
                        } else {
                            subheader.setText("Invalid Input!");
                        }
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
        backbtn.setStyle("-fx-font-size: 14;");
        backbtn.setOnAction(e -> App.changeUploadGraphScenePhase3());

        root.getChildren().addAll(header, subheader, verticescon, edgescon, createbtn, backbtn);

        Scene scene = new Scene(root, 900, 700);
        root.getStyleClass().add("scene");
        scene.getStylesheets().add("./css/style.css");

        return scene;
    }

    static TextField createPlaceholderTextField(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder); 
        textField.setPrefSize(200, 40); 
        textField.setStyle("-fx-font-size: 14;"); 
        return textField;
    }

    static Button createSizedButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(150, 40); // size for buttons
        button.setStyle("-fx-font-size: 14; -fx-font-weight: bold;"); 
        return button;
    }

    static int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(50);
    }
}
