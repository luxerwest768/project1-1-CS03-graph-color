package components.ModelDetection;

import java.util.ArrayList;
import java.util.HashMap;


public class ModelDetection {
    private HashMap<Integer, ArrayList<Integer>> nodeConnections = new HashMap<>();
    private int[][] graph;
    private int largestDegree = 0;
    private int CN = -1;
    private int model = -1;
    // 0: tree
    // 1: wheel
    // 2: complete

    public ModelDetection() {}

    public int getCN() {
        return this.CN;
    }

    public int getModel() {
        return this.model;
    }

    public boolean detectModel(int[][] graph){
        this.graph = graph;

        for (int node=0; node<graph.length; node++) {
            ArrayList<Integer> neighbors = new ArrayList<>();
            for (int edge = 0; edge < graph[node].length; edge++) {
                if (graph[node][edge] == 1) {
                    neighbors.add(edge);
                }
            }
            this.largestDegree = Math.max(this.largestDegree, neighbors.size());
            this.nodeConnections.put(node, neighbors);
        }

        if (this.isTree()){
            this.CN = 2;
            this.model = 0;
            return true;
        } else if (this.isWheel()){
            this.CN = (this.graph.length%2 == 0)? 4 : 3;
            this.model = 1;
            return true;
        } else if (this.isComplete()){
            this.CN = this.graph.length;
            this.model = 2;
            return true;
        }

        return false;
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

    public boolean isWheel(){
        if (this.largestDegree != this.graph.length-1){return false;}

        int centralVertex = -1;
        int numberOfCycle = 0;
        for (int i=0; i<this.graph.length; i++){
            if (this.nodeConnections.get(i).size() == this.graph.length - 1){
                if (centralVertex != -1){
                    return false;
                }
                centralVertex = i;
            }else if (this.nodeConnections.get(i).size() == 3){
                numberOfCycle++;
            }
        }

        return numberOfCycle == this.graph.length-1;
    }

    public boolean isComplete(){
        for (int i=0; i<this.nodeConnections.size(); i++){
            int numberOfConnections = this.nodeConnections.get(i).size();
            if (numberOfConnections != this.graph.length-1){
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

        int[][] test1 = {
                {0, 1, 1, 1, 1}, // Central vertex
                {1, 0, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 1, 0, 1, 0}
        };

        int[][] test = new int[6001][6001];

        // Connect the central vertex (vertex 0) to all other vertices
        for (int i = 1; i < 6001; i++) {
            test[0][i] = 1;
            test[i][0] = 1;
        }

        // Create the cycle for vertices 1 to 49
        for (int i = 1; i < 6000; i++) {
            test[i][i + 1] = 1;
            test[i + 1][i] = 1;
        }
        // Connect the last vertex in the cycle to the first (1 to 49)
        test[1][6000] = 1;
        test[6000][1] = 1;

        ModelDetection obj = new ModelDetection();

        System.out.println(obj.detectModel(test));
    }
}
