package components.EdgeHandle;

import components.NodeHandle.Vertex;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Edge {
    private Line line;
    private String startKey;
    private String endKey;
    private Vertex startVertex;
    private Vertex endVertex;

    public Edge(double x1,double y1,double x2,double y2) {
        this.line = new Line(x1,y1,x2,y2);
        getStartPosition();
        getEndPosition();
    }

    public Edge(){

    }

    public void setStartVertex(Vertex vertex) {
        this.startVertex = vertex;
    }

    public void setEndVertex(Vertex vertex) {
        this.endVertex = vertex;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public double getStartX() {
        return line.getStartX();
    }

    public double getStartY() {
        return line.getStartY();
    }

    public double getEndX() {
        return line.getEndX();
    }

    public double getEndY() {
        return line.getEndY();
    }

    public Line getLine() {
        return this.line;
    }

    public String getStartPosition() {
        StringBuilder startKey = new StringBuilder();
        startKey.append(this.line.getStartX());
        startKey.append(",");
        startKey.append(this.line.getStartY());
        this.startKey = startKey.toString();
        return startKey.toString();
    }

    public String getEndPosition() {
        StringBuilder endKey = new StringBuilder();
        endKey.append(this.line.getEndX());
        endKey.append(",");
        endKey.append(this.line.getEndY());
        this.endKey = endKey.toString();
        return endKey.toString();
    }


    public void setStroke(Color color) {
        this.line.setStroke(color);
    }



}
