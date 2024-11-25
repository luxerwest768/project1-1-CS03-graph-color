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
        Edges edgeSet = new Edges(graph);
        Pane pane = new Pane();
        Vertices nodeSet = new Vertices();
        int numVertices = graph.length;

        Button renderButton = new Button("Render");
        renderButton.getStyleClass().add("render-button");
        Button backButton = new Button("Back");
        backButton.getStyleClass().add("back-button");
        backButton.setOnAction((ActionEvent e) -> {
            App.changeSelectGameScene();
        });
        pane.getChildren().addAll(renderButton,backButton);



        // make an event to reload edge's location whenever change node's location
        EventHandler<ActionEvent> render = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                for (int i = 0; i < edgeSet.getNumEdges(); i++) {
                    pane.getChildren().remove(edgeSet.getEdge(i));
                }
                edgeSet.removeAllEdges();
                for (int i=0 ; i<numVertices ; i++){
                    Vertex node = nodeSet.getVertex(i);
                    edgeSet.setLocationX(i,node.getX());
                    edgeSet.setLocationY(i,node.getY());
                }
                for (int i = 0; i < numVertices; i++) {
                    for (int j = 0; j < numVertices; j++) {
                        if (graph[i][j] == 1) {
                            Line edge = new Line(
                                    edgeSet.getLocationX(i), edgeSet.getLocationY(i),
                                    edgeSet.getLocationX(j), edgeSet.getLocationY(j)
                            );
                            edgeSet.addEdge(edge);
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
            node.setPosition(edgeSet.getLocationX(i), edgeSet.getLocationY(i));
            node.drag(); // make node can be mouseDrag
            pane.getChildren().add(node.getCircle());
        }

        // render edges
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph[i][j] == 1) {
                    Line edge = new Line(
                            edgeSet.getLocationX(i), edgeSet.getLocationY(i),
                            edgeSet.getLocationX(j), edgeSet.getLocationY(j)
                    );
                    edgeSet.addEdge(edge);
                    edge.setStroke(Color.BLACK);
                    pane.getChildren().add(edge);
                }
            }
        }

        Scene scene = new Scene(pane, 1200, 900);
        scene.getStylesheets().add("./css/renderGraph.css");

        return scene;
    }
}