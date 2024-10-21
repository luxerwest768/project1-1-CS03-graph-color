
public class BackTracking {
    // checking if is there any vertices next to have a same color
    static boolean isSafe (int vertex, int[][] graph, int color, int[] colorSet){
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
    
    
    // color every vertices
    static boolean graphColorUntil (int vertex, int[][] graph, int[] colorSet, int numColor){
        // return when every vertices have been colored
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

    // main method for graph coloring
    static int graphColoring (int[][] graph){
        int[] colorSet = new int[graph.length];
        // limit the number of color can be used for backtracking
        for (int numColor=1; numColor<graph.length+1; numColor++){
            fill(colorSet, -1); // reset every vertices to uncolored
            // if all the vertices is colored, return the number of color that is used
            if (graphColorUntil(1, graph, colorSet,numColor)){
                return numColor;
            }
        }
        System.out.println("Solution does not exist");
        return graph.length; // return the worst case when all vertices colored with different color

     }

    
    // assign every elements in array with k
    static void fill(int[] arr, int k){
        for (int i=0; i<arr.length; i++){
            arr[i] = k;
        }
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
        System.out.println("Chromatic Number: "+chromaticNumber); // the result should be 3
    }
}
