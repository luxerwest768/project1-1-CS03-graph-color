package components.convertTextGraph;

import components.convertTextGraph.backTracking;
import components.convertTextGraph.welshAlgorithm;

public class chromaticNumber {
    private int chromaticNumber;

    public void useBackTracking(int[][] graph) {
        this.chromaticNumber = backTracking.graphColoring(graph);
    }

    public void useWelshAlgorithm(int nOfVertices, ColVertices[] v) {
        int[][] degreeList = welshAlgorithm.degreeList(nOfVertices, v);
        this.chromaticNumber = welshAlgorithm.chromaticNumber(degreeList,v,nOfVertices);
    }

    public int getChromaticNumber() {
        return chromaticNumber;
    }
}
