package components.convertTextGraph;

import java.io.*;
import java.util.*;

class ColEdge {
    int u;
    int v;
    boolean printed;
}

class ColVertices { 
    // List of each edge connected to the vertex
    List<ColEdge> edges;
    // The degree of the vertex (the amount of other vertices they are connected to)
    int degree;
    // The colour of the vertex
    int colour;
    // The vertex's id 
    int id;

    public ColVertices() {
        // Makes each edges variable a list 
        this.edges = new ArrayList<>();
        // Sets the colour automatically to 0, which represents "not coloured"
        this.colour = 0; 
    }
}


public class ReadGraph{	
    public final static boolean DEBUG = true;
    
    public final static String COMMENT = "//";

    private static chromaticNumber CN = new chromaticNumber();

    private static int[][] myGraph;

    private static int numberOfVertices;

    private static int numberOfEdges;

    private static ColVertices verticesSet[] = null;
    
    public static void main( String args[] ) {
        
    }
    
    public static int[][] convertTextGraph(File file){  
        
        boolean seen[] = null;
        
        //! n is the number of vertices in the graph
        int n = -1;
        
        //! m is the number of edges in the graph
        int m = -1;
        
        //! e will contain the edges of the graph
        //! v will contain the vertices of the graph
        ColEdge e[] = null;
        ColVertices v[] = null;
        
        try { 
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String record = new String();
            
            //! THe first few lines of the file are allowed to be comments, staring with a // symbol.
            //! These comments are only allowed at the top of the file.
            
            //! -----------------------------------------
            while ((record = br.readLine()) != null) {
                if( record.startsWith("//") ) continue;
                break; // Saw a line that did not start with a comment -- time to start reading the data in!
            }

            if( record.startsWith("VERTICES = ") ) {
                n = Integer.parseInt( record.substring(11) );					
            }

            seen = new boolean[n+1];	
                
            record = br.readLine();
            
            if( record.startsWith("EDGES = ") ) {
                m = Integer.parseInt( record.substring(8) );					
            }

            e = new ColEdge[m];	
                                        
            for( int d=0; d<m; d++) {
                record = br.readLine();
                String data[] = record.split(" ");
                if( data.length != 2 ) {
                    System.out.println("Error! Malformed edge line: "+record);
                    System.exit(0);
                }
                e[d] = new ColEdge();
                
                e[d].u = Integer.parseInt(data[0]);
                e[d].v = Integer.parseInt(data[1]);
                e[d].printed = false;

                seen[ e[d].u ] = true;
                seen[ e[d].v ] = true;
            }
                            
            String surplus = br.readLine();
            if( surplus != null ) {
                if( surplus.length() >= 2 ) if(DEBUG) System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '"+surplus+"'");						
            }
            
        }
        catch (IOException ex) { 
            // catch possible io errors from readLine()
            System.out.println("Error! Problem reading file "+ file.getAbsolutePath());
            System.exit(0);
        }

        for( int x=1; x<=n; x++ ) {
            if( seen[x] == false ) {
                if(DEBUG) System.out.println(COMMENT + " Warning: vertex "+x+" didn't appear in any edge : it will be considered a disconnected vertex on its own.");
            }
        }

        //! At this point e[0] will be the first edge, with e[0].u referring to one endpoint and e[0].v to the other
        //! e[1] will be the second edge...
        //! (and so on)
        //! e[m-1] will be the last edge
        //! 
        //! there will be n vertices in the graph, numbered 1 to n

        //! INSERT YOUR CODE HERE!

        
        v = new ColVertices[n]; 

        // Creates the vertices and adds the edges to them
        for(int i=0; i <= (n-1); i++) {
            // Establishes a new vertice
            v[i] = new ColVertices();

            // Loops through all edges and checks whether they are connected to the vertex
            for(int t=0; t <= (m-1); t++) {
                if(e[t].u == (i+1) || e[t].v == (i+1)) {
                    (v[i].edges).add(e[t]); 
                }
            }

            // Adds the degree of each vertex
            v[i].degree = (v[i].edges).size();

            // Adds an id to each vertex
            v[i].id = i + 1;

            // TEST FOR VERTEX CLASS Prints out the each edge connected to each vertex 
            /* 
            System.out.println("Vertice " + (i+1) + " has these edges:");
            for(ColEdge edge : v[i].edges) {
                System.out.println(edge.u + " " + edge.v);
            }
            */
        }

        int[][] graph = textTranslation.graph(n, v);
        myGraph = graph;

        numberOfVertices = n;
        numberOfEdges = m;
        verticesSet = v;
        return graph;
    };

    public int getCN(){
        if (numberOfVertices < 500 && numberOfEdges < 200){
            CN.useBackTracking(myGraph);
        } else {
            CN.useWelshAlgorithm(numberOfVertices,verticesSet);
        }
        return CN.getChromaticNumber();
    }

    public chromaticNumber getChromaticNumber(){
        return CN;
    }

    public static int[][] createGraph(int vertices, int edges) {
        // Creates array of each edge
        ColEdge[] e = new ColEdge[edges];

        // Randomizes the vertices connected to each edge
        Random rand = new Random();
        for (int i = 0; i < edges; i++) {
            e[i] = new ColEdge();
            boolean used = false;

            // Random vertice points
            int u = rand.nextInt(vertices) + 1;
            int v = rand.nextInt(vertices) + 1;

            // Checks if the edge connects a vertice to itself 
            if (u == v) {
                used = true;
            }

            // Checks whether the edge has been made
            for (int j = 0; j <= i; j++) {
                if(
                    (e[j].u == u && e[j].v == v) 
                    ||
                    (e[j].u == v && e[j].u == v)
                ) {
                    used = true;
                }
            }
            
            // If the edge has been used before it restarts
            if(used == false) {
                e[i].u = u;
                e[i].v = v;
            }
            else {
                i = i - 1;
            }
        }

        ColVertices[] v = new ColVertices[vertices];
        // Creates the vertices and adds the edges to them
        for(int i=0; i <= (vertices-1); i++) {
            // Establishes a new vertice
            v[i] = new ColVertices();

            // Loops through all edges and checks whether they are connected to the vertex
            for(int t=0; t <= (edges-1); t++) {
                if(e[t].u == (i+1) || e[t].v == (i+1)) {
                    (v[i].edges).add(e[t]); 
                }
            }

            // Adds the degree of each vertex
            v[i].degree = (v[i].edges).size();

            // Adds an id to each vertex
            v[i].id = i + 1;
        }
        welshAlgorithm welshAlgorithm = new welshAlgorithm();
        // Checks whether there has to be isolated vertices
        if (vertices  <= (2 * edges)) { 
            int[][] degreelist = welshAlgorithm.degreeList(vertices, v);
            int[][] editablevertices = editableVertices(degreelist);
            
            for (int[] vertex : degreelist) {
                // If there is a vertex with zero connected edges
                if (vertex[1] == 0) {
                    if(v[editablevertices[0][0]].edges.get(0).u == editablevertices[0][0]) {
                        v[editablevertices[0][0]].edges.get(0).u = vertex[0] + 1;
                    }
                    if(v[editablevertices[0][0]].edges.get(0).v == editablevertices[0][0]) {
                        v[editablevertices[0][0]].edges.get(0).v = vertex[0] + 1;
                    }

                    // Adds and removes the edge from the respective vertices
                    v[vertex[0]].edges.add(v[editablevertices[0][0]].edges.get(0));
                    v[editablevertices[0][0]].edges.remove(0);
                    

                    // Resets the degreelist as the edges have been changed
                    for (ColVertices vertice : v) {
                        vertice.degree = (vertice.edges).size();
                    }

                    degreelist = welshAlgorithm.degreeList(vertices, v);
                    editablevertices = editableVertices(degreelist);
                }
            }
        }

        int[][] graph = textTranslation.graph(vertices, v);
        myGraph = graph;
        numberOfVertices = graph.length;
        verticesSet = v;

        return graph;
    }

    public static int[][] editableVertices(int[][] degreelist) {
        // Finds the am
        int length = 0;
        for (int[] vertex : degreelist) {
            if (vertex[1] >= 2) {
                length += 1;
            }
        }

        int[][] editablevertices = new int[length][2];

        for (int i = 0; i < length; i++) {
            editablevertices[i][0] = degreelist[i][0];
            editablevertices[i][1] = degreelist[i][1];
        }
        return editablevertices;
    }

}

