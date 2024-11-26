package page;

import components.EdgeHandle.Edges;
import components.NodeHandle.Vertex;
import components.NodeHandle.Vertices;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class renderGraph {

    private renderGraph(){}
    

    public static Scene renderGraphScene(int[][] graph){
        Pane pane = new Pane();
        Vertices nodeSet = new Vertices();
        Edges edgesSet = new Edges();
        edgesSet.setEdge(graph);
        int numVertices = graph.length;

        Button renderButton = new Button("Render");
        renderButton.getStyleClass().add("render-button");

        pane.getChildren().addAll(renderButton);



        // make an event to reload edge's location whenever change node's location
        EventHandler<ActionEvent> render = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                for (int i = 0; i < edgesSet.getNumEdges(); i++) {
                    pane.getChildren().remove(edgesSet.getEdge(i));
                }
                edgesSet.removeAllEdges();
                for (int i=0 ; i<numVertices ; i++){
                    Vertex node = nodeSet.getVertex(i);
                    edgesSet.setLocationX(i,node.getX());
                    edgesSet.setLocationY(i,node.getY());
                }
                for (int i = 0; i < numVertices; i++) {
                    for (int j = 0; j < numVertices; j++) {
                        if (graph[i][j] == 1) {
                            Line edge = new Line(
                                    edgesSet.getLocationX(i), edgesSet.getLocationY(i),
                                    edgesSet.getLocationX(j), edgesSet.getLocationY(j)
                            );
                            edgesSet.addEdge(edge);
                            edge.setStroke(Color.BLACK);
                            pane.getChildren().add(edge);
                        }
                    }
                }
            }
        };
        renderButton.setOnAction(render); 

        // render nodes
        for (int i = 0; i < numVertices; i++) {
            Vertex node = new Vertex();
            nodeSet.addVertex(node);
            node.setPosition(edgesSet.getLocationX(i), edgesSet.getLocationY(i));
            node.drag(); // make node can be mouseDrag
            pane.getChildren().add(node.getCircle());
        }

        // render edges
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph[i][j] == 1) {
                    Line edge = new Line(
                            edgesSet.getLocationX(i), edgesSet.getLocationY(i),
                            edgesSet.getLocationX(j), edgesSet.getLocationY(j)
                    );
                    edgesSet.addEdge(edge);
                    edge.setStroke(Color.BLACK);
                    pane.getChildren().add(edge);
                }
            }
        }

        Scene scene = new Scene(pane, 1000, 700);
        scene.getStylesheets().add("./css/renderGraph.css");

        return scene;
    }
}
