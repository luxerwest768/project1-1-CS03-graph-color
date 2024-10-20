
import java.util.Arrays;

public class CountChromaticNum {
    // checking if is there any vertices next to have a same color
    static boolean isSafe (int vertex, int[][] graph, int color, int[] colorSet){
        for (int neighborEdge = 0; neighborEdge < graph[vertex-1].length ; neighborEdge++){
            if (graph[vertex-1][neighborEdge] == 1){ // there is an edge between 2 vertices
                if (colorSet[neighborEdge] == color ){ // if the neighbor vertex has the same color, return false
                    return false;
                }
            }
        }
        return true; // other return true
    } 
    
    
    // color every vertices
    static boolean graphColorUntil (int vertex, int[][] graph, int[] colorSet, int numColor){
        if (vertex == graph.length+1){ // every vertices have been colored
            return true; 
        }
        for (int color = 0; color < numColor; color++){
            if (isSafe(vertex, graph, color, colorSet)){
                colorSet[vertex-1] = color; // assign the color to uncolored vertex
                if (graphColorUntil(vertex+1, graph, colorSet,numColor)){ // move to the next vertex
                    return true;
                }

                // backtracking, if the color does not lead to solution
                colorSet[vertex-1] = -1;
            }

        }
        return false;
    }

    // main method for graph coloring
    static int graphColoring (int[][] graph){
        int[] colorSet = new int[graph.length];
        for (int numColor=1; numColor<graph.length+1; numColor++){
            Arrays.fill(colorSet, -1);
            if (graphColorUntil(1, graph, colorSet,numColor)){
                return numColor;
            }
        }
        System.out.println("Solution does not exist");
        return graph.length;

    }

    public static void main(String[] args) {
    /*   (1)----(2)
        / \     / \
      (3)  (4)-(5)-(6)
       |    /\  | /
      (7)(10)-(9)
       |    
      (8)  
    */
 
        int[][] graph = 
        {
            {0,1,1,1,0,0,0,0,0,0},
            {1,0,0,0,1,1,0,0,0,0},
            {1,0,0,0,0,0,1,0,0,0},
            {1,0,0,0,1,0,0,0,1,1},
            {0,1,0,1,0,1,0,0,1,0},
            {0,1,0,0,1,0,0,0,1,0},
            {0,0,1,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,1,0,0,0},
            {0,0,0,1,1,1,0,0,0,1},
            {0,0,0,1,0,0,0,0,1,0}
        };

        int chromaticNumber = graphColoring(graph);
        System.out.println(chromaticNumber); // the result should be 3 but it's actually 4-
    }
}
