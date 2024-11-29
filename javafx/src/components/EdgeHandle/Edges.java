package components.EdgeHandle;


import java.util.ArrayList;

public class Edges {
    private int[][] graph;
    private ArrayList<Edge> edgeSet = new ArrayList<>();


    public Edges(){
    }

    public int size(){
        return edgeSet.size();
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
