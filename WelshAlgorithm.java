import java.util.*;
public class WelshAlgorithm {
    public static void main(String[] args) {
        ReadGraph.main(args);


    }

    // Method that returns an order list of each vertex's degree
    public static int[][] degreeList(int nOfVertices, ColVertices[] v) {
        int[][] degreelist = new int[nOfVertices][2];
        int[][] temp = new int[nOfVertices][2];

        // Adds the vertex id and degree to a matrix
        for(int i = 0; i < nOfVertices; i++) {
            temp[i][0] = i + 1;
            temp[i][1] = v[i].degree;
        }

        // Sorts the array
        Arrays.sort(temp, (a, b) -> Integer.compare(a[1], b[1]));

        // Reverses the sort, so that the array is in ascending order
        for(int i = 0; i < nOfVertices; i++) {
            degreelist[i][0] = temp[(nOfVertices - i - 1)][0];
            degreelist[i][1] = temp[(nOfVertices - i - 1)][1];
        }
        
        return degreelist;
    }

    public static void colouring(int[][] degreeList, ColVertices[] v) {
        int colour = 1; 
        boolean finished = false;

        while(finished == false) {
            
        }

    }
}
