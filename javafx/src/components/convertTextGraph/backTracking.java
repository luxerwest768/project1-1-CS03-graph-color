package components.convertTextGraph;

import components.NodeHandle.Vertices;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class backTracking {

    // checking if is there any vertices next to have a same color
    public boolean isSafe (int vertex, int[][] graph, int color, int[] colorSet){
        // loop through all the neighbor vertices of that vertex
        for (int neighborEdge = 0; neighborEdge < graph[vertex-1].length ; neighborEdge++){
            // check there is an edge between 2 vertices
            if (graph[vertex-1][neighborEdge] == 1){
                // check if the neighbor vertex has the same color
                if (colorSet[neighborEdge] == color ){
                    return false;
                }
            }
        }
        return true; // other return true
    }


    // color every vertex
    public boolean graphColorUntil (int vertex, int[][] graph, int[] colorSet, int numColor){
        // return when every vertex have been colored
        if (vertex == graph.length+1){
            return true;
        }
        // loop through all the color in color set
        for (int color = 0; color < numColor; color++){
            if (isSafe(vertex, graph, color, colorSet)){ //
                colorSet[vertex-1] = color; // assign the color to uncolored vertex
                // move to the next vertex
                if (graphColorUntil(vertex+1, graph, colorSet,numColor)){
                    return true;
                }

                // backtracking, if the current color does not lead to solution
                colorSet[vertex-1] = -1;
            }

        }
        return false;
    }

    public int[] globalColorSet;

    // main method for graph coloring
    public int graphColoring (int[][] graph){
        int[] colorSet = new int[graph.length];
        globalColorSet = new int[graph.length];
        // limit the number of color can be used for backtracking
        for (int numColor=1; numColor<graph.length+1; numColor++){
            fill(colorSet, -1); // reset every vertices to uncolored
            // if all the vertices is colored, return the number of color that is used
            if (graphColorUntil(1, graph, colorSet,numColor)){
                System.arraycopy(colorSet, 0, globalColorSet, 0, graph.length);
                return numColor;
            }
        }
        System.out.println("Solution does not exist");
        System.arraycopy(colorSet, 0, globalColorSet, 0, graph.length);
        return graph.length; // return the worst case when all vertices colored with different color

    }


    // assign every elements in array with k
    public void fill(int[] arr, int k){
        for (int i=0; i<arr.length; i++){
            arr[i] = k;
        }
    }

    public void solve(Vertices node){
        HashMap<Integer, int[]> colorSet = new HashMap<>();

        for(int i = 0; i < globalColorSet.length; i++){
            if(colorSet.containsKey(globalColorSet[i])){
                int[] temp = colorSet.get(globalColorSet[i]);
                Color colorVar = Color.rgb(temp[0],temp[1],temp[2]);
                node.getVertex(i).setFill(colorVar);
            } else {
                int[] color = new int[3];
                color[0] = (int) (Math.random() * 255);
                color[1] = (int) (Math.random() * 255);
                color[2] = (int) (Math.random() * 255);
                colorSet.put(globalColorSet[i], color);
                Color colorVar = Color.rgb(color[0],color[1],color[2]);
                node.getVertex(i).setFill(colorVar);
            }
        }
    }
}
