package components.EdgeHandle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Edge {
    private Line line;
    private String startKey;
    private String endKey;

    public Edge(double x1,double y1,double x2,double y2) {
        this.line = new Line(x1,y1,x2,y2);
        getStartPosition();
        getEndPosition();
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
