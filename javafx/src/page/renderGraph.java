package page;

import components.EdgeHandle.Edge;
import components.EdgeHandle.Edges;
import components.NodeHandle.Vertex;
import components.NodeHandle.Vertices;
import components.ColorWheel.ColorWheel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class renderGraph {


    private renderGraph(){}
    

    public static Scene renderGraphScene(int[][] graph){
        int width = 1000;
        int height = 700;

        Pane pane = new Pane();
        Vertices nodeSet = new Vertices();
        Edges edgesSet = new Edges();
        nodeSet.setGraph(graph,width,height);
        int numVertices = graph.length;

        ColorWheel colorWheel = new ColorWheel(90);
        colorWheel.getCanvas().getStyleClass().add("color-wheel");

        Rectangle currentColor = new Rectangle(30,30);
        currentColor.setFill(Color.WHITE);
        currentColor.setStroke(Color.BLACK);
        currentColor.getStyleClass().add("current-color");
        pane.setOnMouseClicked(event -> {
            currentColor.setFill(colorWheel.getColor());
        });

        Button renderButton = new Button("Render");
        renderButton.getStyleClass().add("render-button");

        pane.getChildren().addAll(renderButton,colorWheel.getCanvas(),currentColor);


        // render nodes
        for (int i = 0; i < numVertices; i++) {
            Vertex node = new Vertex();
            nodeSet.addVertex(node);
            node.setPosition(nodeSet.getLocationX(i), nodeSet.getLocationY(i));
            node.drag(); // make node can be mouseDrag
            node.setColor(colorWheel,nodeSet);
            pane.getChildren().add(1,node.getCircle());
        }

        // render edges
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph[i][j] == 1) {
                    Edge edge = new Edge(
                            nodeSet.getLocationX(i), nodeSet.getLocationY(i),
                            nodeSet.getLocationX(j), nodeSet.getLocationY(j)
                    );
                    edgesSet.addEdge(edge);
                    edge.setStroke(Color.BLACK);
                    pane.getChildren().add(0,edge.getLine());
                }
            }
        }
        edgesSet.setUpPosition();

        Scene scene = new Scene(pane, width, height);
        scene.getStylesheets().add("./css/renderGraph.css");

        return scene;
    }
}
