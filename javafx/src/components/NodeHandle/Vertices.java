package components.NodeHandle;

import components.EdgeHandle.Edges;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Vertices {
    private ArrayList<Vertex> verticesSet = new ArrayList<>();
    private Color[] colorSet;
    private double[][] positions;
    private int[][] graph;

    public Vertices() {
    }

    public void  setGraph(int[][] graph, int width, int height) {
        this.graph = graph;
        this.colorSet = new Color[graph.length];
        this.positions = new double[graph.length][2];
        setGraphPosition(width, height);
    }

    public int getNumberVertices(){
        return this.verticesSet.size();
    }

    public void setGraphPosition(int width, int height){
        int numVertices = this.graph.length;
        double centerX = width/2; // ensure that the first position is in the center
        double centerY = height/2; // ensure that the first position is in the center
        double radius = Math.min(centerX, centerY);
        for (int i = 0; i < numVertices; i++) {
            double angle = 2 * Math.PI * i / numVertices;
            this.positions[i][0] = centerX + radius * Math.cos(angle);
            this.positions[i][1] = centerY + radius * Math.sin(angle);
        }
    }

    public void setColorIndex(int index,Color c){
        this.colorSet[index] = c;
    }


    public Color getColorIndex(int index){
        return this.colorSet[index];
    }

    public void addVertex(Vertex vertex){
        verticesSet.add(vertex);
    }

    public double[][] getPositions(){
        return this.positions;
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

    public Vertex getVertex(int index){
        return this.verticesSet.get(index);
    }
}
