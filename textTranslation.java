public class textTranslation {
    public static int[][] graph(int nOfVertices, ColVertices[] v) {
        // Initializes the graph
        int[][] graph = new int[nOfVertices][nOfVertices];
        
        // Loops through the vertices
        for(int i = 0; i < nOfVertices; i++) {
            // Loopes through each edge the vertice is connected to
            for(ColEdge edge : v[i].edges) {
                // If the edge.u does not equal the vertex being looked at
                if(edge.u != (i + 1)) {
                    // Then it shows in the matrix that it is connected to the vertex being looked at
                    graph[i][(edge.u - 1)] = 1;
                }
                // If the edge.v does not equal the vertex being looked at
                else if(edge.v != (i + 1)) {
                    // Then it shows in the matrix that it is connected to the vertex being looked at
                    graph[i][(edge.v - 1)] = 1;
                }
            }
        }
        
        return graph;
    }
}
