public class textTranslation {
    public static int[][] graph(int nOfVertices, ColVertices[] v) {
        // Initializes the graph
        int[][] graph = new int[nOfVertices][nOfVertices];
        
        // Loops through the vertices
        for(int i = 0; i < nOfVertices; i++) {
            // Loopes through each edge the vertice is connected to
            for(ColEdge edge : v[i].edges) {
                if(edge.u != (i + 1)) {
                    graph[i][(edge.u - 1)] = 1;
                }
                else if(edge.v != (i + 1)) {
                    graph[i][(edge.v - 1)] = 1;
                }
            }
        }
        
        return graph;
    }
}
