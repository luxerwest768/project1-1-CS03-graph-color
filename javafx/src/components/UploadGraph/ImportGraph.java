package components.UploadGraph;

public class ImportGraph {
    private int[][] myGraph;

    public ImportGraph(){}

   public void setGraph(int[][] graph){
        this.myGraph = graph;
   }

    public int[][] getGraph(){
        return this.myGraph;
    }
}
