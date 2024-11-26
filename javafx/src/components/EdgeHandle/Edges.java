package components.EdgeHandle;

import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Edges {
    private int[][] graph;
    private double[][] positions;
    private ArrayList<Line> edgeSet = new ArrayList<>();


    public Edges(int[][] graph){
        this.graph = graph;
        this.positions = new double[graph.length][2];
        setGraphPosition();
    }

    public void  addEdge(Line line){
        this.edgeSet.add(line);
    }

    public Line getEdge(int index){
        return this.edgeSet.get(index);
    }

    public int getNumEdges(){
        return this.edgeSet.size();
    }

    public void removeAllEdges(){
        this.edgeSet.clear();
    }

    public void setGraphPosition(){
        int numVertices = this.graph.length;
        double centerX = 900/2; // ensure that the first position is in the center
        double centerY = 700/2; // ensure that the first position is in the center
        double radius = Math.min(centerX, centerY);
        for (int i = 0; i < numVertices; i++) {
            double angle = 2 * Math.PI * i / numVertices;
            positions[i][0] = centerX + radius * Math.cos(angle);
            positions[i][1] = centerY + radius * Math.sin(angle);
        }
    }

    public void setLocationX(int i, double x){
        this.positions[i][0] = x;
    }

    public void setLocationY(int i, double y){
        this.positions[i][1] = y;
    }

    public double getLocationX(int i){
        return this.positions[i][0];
    }

    public double getLocationY(int i){
        return this.positions[i][1];
    }

}
