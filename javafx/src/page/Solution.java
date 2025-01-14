package page;

import components.EdgeHandle.Edge;
import components.EdgeHandle.Edges;
import components.NodeHandle.Vertex;
import components.NodeHandle.Vertices;

import components.convertTextGraph.chromaticNumber;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Solution {

    public static Scene renderSolutionGraph(int[][] graph, chromaticNumber solution){
        int width = 1000;
        int height = 700;

        Pane pane = new Pane();
        Vertices nodeSet = new Vertices(graph,width,height);
        Edges edgesSet = new Edges();
        nodeSet.setGraphPosition(width,height);
        int numVertices = graph.length;

        String strCN = "Chromatic Number: " + solution.getChromaticNumber();
        Label CN = new Label(strCN);
        CN.setStyle("-fx-font-size: 16pt; -fx-fill: #3e2974; -fx-font-weight: bold; -fx-translate-y: 20; -fx-translate-x: 10;");

        Label algorithm = new Label(solution.getAlgorithm());
        algorithm.setStyle(" -fx-font-size: 16pt; -fx-translate-y: 60; -fx-fill: #3e2974; -fx-font-weight: bold; -fx-translate-x: 10;");
        pane.getChildren().addAll(CN,algorithm);

        // render nodes
        for (int i = 0; i < numVertices; i++) {
            Vertex node = new Vertex();
            double x = nodeSet.getLocationX(i);
            double y = nodeSet.getLocationY(i);
            node.setPosition(x, y);
            nodeSet.addVertex(node);
            pane.getChildren().add(nodeSet.getVertex(i).getCircle());
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
                    pane.getChildren().addFirst(edge.getLine());
                }
            }
        }

        solution.showSolution(nodeSet);

        Scene scene = new Scene(pane, width, height);
        pane.getStyleClass().add("pane");
        scene.getStylesheets().add("./css/style.css");

        return scene;
    }

}
