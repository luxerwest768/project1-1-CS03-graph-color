package components.UploadGraph;

public class ImportGraph {
    private int[][] myGraph;
    private int chromaticNum;

    public ImportGraph(){}

    public void setGraph(int[][] graph){
        this.myGraph = graph;
    }

    public void setChromaticNum(int chromaticNum){
        this.chromaticNum = chromaticNum;
    }

    public int getChromaticNum(){
        return chromaticNum;
    }

    public int[][] getGraph(){
        return this.myGraph;
    }
}