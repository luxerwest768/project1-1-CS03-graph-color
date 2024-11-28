package components.EdgeHandle;

import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.HashMap;

public class Edges {
    private int[][] graph;
    private ArrayList<Edge> edgeSet = new ArrayList<>();
    private HashMap<String,Edge> edgesPositions = new HashMap<>();


    public Edges(){
    }

    public void setUpPosition(){
        for (int i = 0; i < edgeSet.size(); i++){
            StringBuilder key = new StringBuilder();
            Edge edge = edgeSet.get(i);
            key.append(edge.getStartPosition());
            key.append(":");
            key.append(edge.getEndPosition());
            edgesPositions.put(key.toString(),edgeSet.get(i));
        }
    }


    public void  addEdge(Edge line){
        this.edgeSet.add(line);
    }

    public Edge getEdge(int index){
        return this.edgeSet.get(index);
    }

    public int getNumEdges(){
        return this.edgeSet.size();
    }

    public void removeAllEdges(){
        this.edgeSet.clear();
    }



}
