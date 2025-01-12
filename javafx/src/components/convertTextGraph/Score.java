package components.convertTextGraph;

import components.EdgeHandle.Edge;
import components.EdgeHandle.Edges;
import components.NodeHandle.Vertex;
import components.NodeHandle.Vertices;
import java.io.*;
//import java.util.*;

public class Score {

    //checks total number of different colors used
    // public static int fakechrom(ColVertices[] v)
    // {

    //     //we assume at least 1 color is used
    //     int f = 1;

    //     //program loops throu all verticies, checking if they have a different color
    //     for (int i = 1; i < v.length; i++) 
    //     {
    //         int j = 0;

    //         //loops until same color is detected; if not, loop finishes meaning different color
    //         for (j = 0; j < i; j++)
    //         {
    //             if (v[i].colour == v[j].colour)
    //                 break;
    //         }
    //         if (i == j)
    //             f++;
    //     }
    //     return f;
    // }

    //total number of mistakes(number of times 2 connected verticies have the same color) of the colored graph
    // public static int mistakes(ColVertices[] v, ColEdge[] e)
    // {
    //     int mis = 0;
    //     for(int d=0; d<e.length; d++)
    //     {

    //         //goes throu all edges and checks if the 2 verticies attached to it have the same color using that expression
    //         if(v[e[d].u-1].colour==v[e[d].v-1].colour)
    //         {
    //             mis++;
    //         }
    //     }
    //     return mis;
    // }

    // public static int time()
    // {
    //     return 0;
    // }

    // public static int hints()
    // {
    //     return 0;
    // }

    //saves highscore for each gamemode
    public static void highscore(int p, File f)
    {
        int hs = 0;
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();

            // reads the score file line by line
            while (line != null)                 
            {
                try 
                {
                    //keeps track of the largest int
                    int score = Integer.parseInt(line.trim()); 
                    if (score > hs)                       
                    { 
                        hs = score; 
                    }
                } catch (NumberFormatException e1) {
                // ignore invalid scores
                //System.err.println("ignoring invalid score: " + line);
                }
            line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
        //System.err.println("ERROR reading scores from file");
        }

        try
        {
            BufferedWriter output = new BufferedWriter(new FileWriter(f, true));
            output.newLine();
            output.append("" + p);
            output.close();

        } catch (IOException ex1) {
            System.out.printf("ERROR writing score to file: %s\n", ex1);
        }   
    }

    //sets score depending on the gamemode
    public static int main(Vertices v, Edges e, int getMistakes, int s)
    {

        int m = 0;
        int f = v.getUniqueColors();
        int mis = getMistakes;
        int vl = v.getNumberVertices();
        int el = e.getNumEdges();

        if(s==0)
        {
            m = 10 * (vl * el) - (f + mis) * (vl * el / 4);
            if(m < 0)
            {
                m = 0;
            }
            File fi = new File("score_ToTheBitterEnd.txt");
            highscore(m,fi);
        }
        else
        {
            if(s==1)
            {
                m = 10 * (vl * el) - (f + mis) * (vl * el / 4);
                if(m < 0)
                {
                    m = 0;
                }
                File fi = new File("score_RandomOrder.txt");
                highscore(m,fi);
            }
            else 
            {
                if(s==2)
                {
                    m = 10 * (vl * el) - (f + mis) * (vl * el / 4);
                    if(m < 0)
                    {
                        m = 0;
                    }
                    File fi = new File("score_IChangeMyMind.txt");
                    highscore(m,fi);
                }
                else
                {
                    m = 0;
                }
            }
        }      
        return m;
    }
}
