package page;

import components.ColorWheel.ColorWheel;
import components.EdgeHandle.Edge;
import components.EdgeHandle.Edges;
import components.NodeHandle.Vertex;
import components.NodeHandle.Vertices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class IChangeMyMind {
    public static Scene iChangeMyMindScene(int[][] graph, int CN){
        int width = 1000;
        int height = 700;

        // reset the index of vertex to 0
        Vertex testNode = new Vertex();
        testNode.setCount();

        Pane pane = new Pane();
        Vertices nodeSet = new Vertices(graph,width,height);
        Edges edgesSet = new Edges();
        nodeSet.setGraphPosition(width,height);
        int numVertices = graph.length;

        ColorWheel colorWheel = new ColorWheel(90);
        colorWheel.getCanvas().getStyleClass().add("color-wheel");

        Circle currentColor = new Circle();
        currentColor.setRadius(50);
        currentColor.setFill(Color.WHITE);
        currentColor.setStroke(Color.BLACK);
        currentColor.setStrokeWidth(3);
        currentColor.getStyleClass().add("current-color");
        pane.setOnMouseClicked(event -> {
            currentColor.setFill(colorWheel.getColor());
        });

        Button renderButton = new Button("Render");
        renderButton.getStyleClass().add("render-button");

        pane.getChildren().addAll(renderButton,colorWheel.getCanvas(),currentColor);


        // make an event to reload edge's location whenever change node's location
        EventHandler<ActionEvent> render = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                // remove all line from the current edgesSet
                for (int i = 0; i < edgesSet.getNumEdges(); i++) {
                    pane.getChildren().remove(edgesSet.getEdge(i).getLine());
                }
                edgesSet.removeAllEdges();

                // record the new location of node
                for (int i=0 ; i<numVertices ; i++){
                    Vertex node = nodeSet.getVertex(i);
                    nodeSet.setLocationX(i,node.getX());
                    nodeSet.setLocationY(i,node.getY());
                }

                //create new line
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
            }
        };
        renderButton.setOnAction(render);

        Label CNText = new Label("");
        CNText.getStyleClass().add("cn-text");
        Label uniqueColorText = new Label();
        uniqueColorText.getStyleClass().add("unique-color-text");
        Button getResultButton = new Button("Get Result");
        getResultButton.getStyleClass().add("get-result-button");
        pane.getChildren().addAll(CNText,uniqueColorText,getResultButton);
        EventHandler<ActionEvent> compareCN = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    nodeSet.checkUniqueColor();
                    CNText.setText("Chromatic Numbers: "+CN);
                    uniqueColorText.setText("Colors Used: "+nodeSet.getUniqueColors());
                    System.out.println(nodeSet.getUniqueColors());
                    if (nodeSet.getUniqueColors() != CN){
                        App.endScreenScene();
                    }
                } catch (NullPointerException e) {
                    System.out.println("There are no colored node!");
                }
            }
        };
        getResultButton.setOnAction(compareCN);

        Button redoBtn = new Button("Undo");
        redoBtn.getStyleClass().add("redo-button");
        pane.getChildren().addAll(redoBtn);
        redoBtn.setOnAction(event -> {
            nodeSet.reloadColor();
        });

        Label colorStatus = new Label("");
        colorStatus.getStyleClass().add("color-status");
        pane.getChildren().add(colorStatus);
        // render nodes
        for (int i = 0; i < numVertices; i++) {
            Vertex node = new Vertex();
            double x = nodeSet.getLocationX(i);
            double y = nodeSet.getLocationY(i);
            node.setPosition(x, y);
            nodeSet.addVertex(node);
            nodeSet.getVertex(i).drag(); // make node can be mouseDrag
            nodeSet.getVertex(i).setColorForRandom(colorWheel,nodeSet,colorStatus);
            pane.getChildren().add(nodeSet.getVertex(i).getCircle());
        }
        nodeSet.randomOrder();


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
                    pane.getChildren().addFirst(edge.getLine());
                }
            }
        }

        Scene scene = new Scene(pane, width, height);
        scene.getStylesheets().add("./css/iChangeMyMind.css");

        return scene;
    }
}
