package components.convertTextGraph;

import components.NodeHandle.Vertices;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashMap;

public class welshAlgorithm {
    private int nOfVertices;
    private int[] globalColorSet;

    public void setnOfVertices(int nOfVertices) {
        this.nOfVertices = nOfVertices;
        this.globalColorSet = new int[nOfVertices];
    }

    // Method that returns an order list of each vertex's degree
    public int[][] degreeList(int nOfVertices, ColVertices[] v) {
        int[][] degreelist = new int[nOfVertices][2];
        int[][] temp = new int[nOfVertices][2];

        // Adds the vertex id and degree to a matrix
        for(int i = 0; i < nOfVertices; i++) {
            temp[i][0] = i;
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

    public int chromaticNumber(int[][] degreeList, ColVertices[] v, int nOfVertices) {

        // Initializes the colour and finished variable.
        int colour = 1;
        boolean finished = false;

        // Only stops loops once each vertex is coloured
        while(finished == false) {
            // Loops through each vertex
            for(int i = 0; i < nOfVertices; i++) {
                // Checks whether its been coloured
                if(v[degreeList[i][0]].colour == 0) {
                    // Checks whether a neighbour has the same colour
                    if(canColour(v, v[degreeList[i][0]], colour)) {
                        // Colours the vertex if no neighbour has the same colour
                        v[degreeList[i][0]].colour = colour;
                        globalColorSet[i] = colour;
                    }
                }
            }

            // Go to the next colour
            colour += 1;

            // Checks whether each vertex has been coloured
            finished = done(v, nOfVertices);
        }

        // Returns the colour - 1 because it starts at 1, so the chromatic number is colour - 1
        return (colour - 1);
    }

    public boolean canColour(ColVertices[] v,ColVertices vertex, int colour) {
        boolean isFine = true;

        // Loops through each edge connected to the vertex
        for(ColEdge edge : vertex.edges) {
            // If the vertex in u is not the one we are looking at
            if(edge.u != vertex.id) {
                // If the vertex connected has the same colour we are looking at then it is no longer fine
                if(v[edge.u - 1].colour == colour) {
                    isFine = false;
                }
            }
            // If the vertex in u is not the one we are looking at
            else if(edge.v != vertex.id) {
                // If the vertex connected has the same colour we are looking at then it is no longer fine
                if(v[edge.v - 1].colour == colour) {
                    isFine = false;
                }
            }
        }

        return isFine;
    }

    public boolean done(ColVertices[] v, int nOfVertices) {
        boolean done = false;
        // n is the number of coloured vertices
        int n = 0;

        // Loops through each vertex
        for(int i = 0; i < nOfVertices; i++) {
            // Checks whether they have been coloured
            if(v[i].colour != 0) {
                n += 1;
            }
        }

        // Checks whether the number of coloured vertices is equal to the total vertices
        if(n == nOfVertices) {
            done = true;
        }

        return done;
    }

    public void solve(Vertices nodeSet){
        HashMap<Integer, int[]> colorSet = new HashMap<>();

        for(int i = 0; i < nOfVertices; i++){
            if(colorSet.containsKey(globalColorSet[i])){
                int[] temp = colorSet.get(globalColorSet[i]);
                Color colorVar = Color.rgb(temp[0],temp[1],temp[2]);
                nodeSet.getVertex(i).setFill(colorVar);
            } else {
                int[] color = new int[3];
                color[0] = (int) (Math.random() * 255);
                color[1] = (int) (Math.random() * 255);
                color[2] = (int) (Math.random() * 255);
                colorSet.put(globalColorSet[i], color);
                Color colorVar = Color.rgb(color[0],color[1],color[2]);
                nodeSet.getVertex(i).setFill(colorVar);
            }
        }
    }
}
