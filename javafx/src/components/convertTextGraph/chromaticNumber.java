package components.convertTextGraph;

import components.NodeHandle.Vertices;
import components.convertTextGraph.backTracking;
import components.convertTextGraph.welshAlgorithm;

public class chromaticNumber {
    private int chromaticNumber;
    private static int status = 0;
    private static backTracking BackTrackAlgorithm = new backTracking();
    private static welshAlgorithm WelshAlgorithm = new welshAlgorithm();

    public chromaticNumber() {}

    public void useBackTracking(int[][] graph) {
        this.status = 0;
        this.chromaticNumber = BackTrackAlgorithm.graphColoring(graph);
    }

    public void useWelshAlgorithm(int nOfVertices, ColVertices[] v) {
        this.status = 1;
        WelshAlgorithm.setnOfVertices(nOfVertices);
        int[][] degreeList = WelshAlgorithm.degreeList(nOfVertices, v);
        this.chromaticNumber = WelshAlgorithm.chromaticNumber(degreeList,v,nOfVertices);
    }

    public void showSolution(Vertices nodeSet){
        if (status == 0){
            BackTrackAlgorithm.solve(nodeSet);
        } else {
            WelshAlgorithm.solve(nodeSet);
        }
    }

    public String getAlgorithm(){
        if (status == 0){
            return "Backtracking Algorithm";
        } else {
            return "Welsh Algorithm";
        }
    }

    public int getChromaticNumber() {
        return chromaticNumber;
    }
}
