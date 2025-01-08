package components.ModelDetection;

import java.util.ArrayList;
import java.util.HashMap;


public class modelDetection {
    private HashMap<Integer, ArrayList<Integer>> nodeConnections = new HashMap<>();
    private int[][] graph;
    private int CN = -1;
    // 0: tree
    // 1: star
    // 2: wheel

    public modelDetection() {}

    public int getCN() {
        return this.CN;
    }

    public int detectModel(int[][] graph){
        this.graph = graph;
        int modelType = 0;

        for (int node=0; node<graph.length; node++) {
            ArrayList<Integer> neighbors = new ArrayList<>();
            for (int edge = 0; edge < graph[node].length; edge++) {
                if (graph[node][edge] == 1) {
                    neighbors.add(edge);
                }
            }
            this.nodeConnections.put(node, neighbors);
        }

        if (this.isTree()){
            this.CN = 2;
            return 0;
        }

        return -1;
    };

    public boolean isCycle(boolean[] visited, int vertex, int parent){
        visited[vertex] = true;
        ArrayList<Integer> neighbors = this.nodeConnections.get(vertex);
        for (Integer neighbor : neighbors){
            if (!visited[neighbor]){
                if (isCycle(visited, neighbor, vertex)){
                    return true;
                }
            } else if (neighbor != parent){
                return true;
            }
        }
        return false;
    }

    public boolean isTree(){
        boolean[] visited = new boolean[this.graph.length];
        for (int i=0; i<visited.length; i++){visited[i] = false;}

        if (this.isCycle(visited, 0, -1)){return false;}

        for (int i=0; i<this.graph.length; i++){
            if (!visited[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0,1,1,1,0,0,0,0,0,0},
                {1,0,0,0,1,1,1,0,0,0},
                {1,0,0,0,0,0,0,1,1,0},
                {1,0,0,0,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0},
        };

        modelDetection obj = new modelDetection();

        System.out.println("this is tree: "+obj.detectModel(graph));
    }
}
