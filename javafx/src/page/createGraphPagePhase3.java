package page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


import java.util.Random;
import components.convertTextGraph.ReadGraph;

public class createGraphPagePhase3 {
    
    static Scene createGraphPhase3() {
        // Declares the headers 
        Text header = new Text("Create Graph");
        Text subheader = new Text("Specify the graph details");

        header.getStyleClass().add("header");
        subheader.getStyleClass().add("subheader");
        
        // Declares the container and items within for the input of the vertices
        HBox verticescon = new HBox();
        verticescon.setMaxWidth(800);
        verticescon.setMaxHeight(50);
        Label verticetxt = new Label("Number of vertices");
        TextField verticeinput = new TextField();
        verticeinput.setText("Type Here");
        Button randbtn1 = new Button();
        randbtn1.setText("Randomize");
        verticescon.getChildren().addAll(verticetxt, verticeinput, randbtn1);
        verticescon.setSpacing(10);

        verticescon.getStyleClass().add("containerV");

        // Declares the container and items within for the input of the edges
        HBox edgescon = new HBox();
        edgescon.setMaxWidth(800);
        edgescon.setMaxHeight(50);
        Label edgestxt = new Label("Number of Edges");
        TextField edgesinput = new TextField();
        edgesinput.setText("Type Here");
        Button randbtn2 = new Button();
        randbtn2.setText("Randomize");
        edgescon.getChildren().addAll(edgestxt, edgesinput, randbtn2);
        edgescon.setSpacing(10);

        edgescon.getStyleClass().add("containerE");

        //Sets the function for the random buttons
        randbtn1.setOnAction(e -> {
            String number = Integer.toString(randomNumber());
            verticeinput.setText(number);
        });

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
                    if (Integer.valueOf(verticeinput.getText()) < 50 && Integer.valueOf(edgesinput.getText()) < 50){
                        int[][] graph = ReadGraph.createGraph(Integer.valueOf(verticeinput.getText()), Integer.valueOf(edgesinput.getText()));
                        int CN = readGraph.getCN();
                        if (graph != null){
                            App.changeRenderGraphScenePhase3(graph);
                        } else {
                            subheader.setText("Invalid Input!");
                        }
                    } else {
                        subheader.setText("Invalid Input!: edges and vertices are over 50");
                    }
                } catch (NumberFormatException error){
                    subheader.setText("Invalid Input!");
                } catch (IllegalArgumentException error){
                    subheader.setText("There has to be at least 1 vertex!");
                }
                
            }
        };

        // Sets the text and the function of the create button
        createbtn.setText("Create");
        createbtn.setOnAction(changeScene);

        // Sets the text and the function of the back button
        backbtn.setText("Back");
        backbtn.setOnAction(e -> {
            App.changeUploadGraphScenePhase3();
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(header, subheader, verticescon, edgescon, createbtn, backbtn);
        Scene scene = new Scene(root,900,700);
        scene.getStylesheets().addAll("./css/creategraphPage.css");

        return scene;
    }

    static int randomNumber() {
        Random rand = new Random();
        int randnbr = rand.nextInt(50);
        return randnbr;
    }
}
