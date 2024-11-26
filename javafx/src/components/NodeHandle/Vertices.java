package components.NodeHandle;

import components.EdgeHandle.Edges;

import java.util.ArrayList;

public class Vertices {
    private ArrayList<Vertex> verticesSet = new ArrayList<>();;

    public int getNumberVertices(){
        return verticesSet.size();
    }

    public void addVertex(Vertex vertex){
        verticesSet.add(vertex);
    }

    public Vertex getVertex(int index){
        return this.verticesSet.get(index);
    }
}
