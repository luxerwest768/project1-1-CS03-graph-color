package components.EdgeHandle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import components.NodeHandle.Vertex;

public class Edge {
    private Line line;
    private String startKey;
    private String endKey;

    public Edge(double x1,double y1,double x2,double y2) {
        this.line = new Line(x1,y1,x2,y2);
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

    public void drag(Vertex node){
        node.getCircle().setOnMouseDragged(event -> {
            double x = node.getX();
            double y = node.getY();
            StringBuilder key = new StringBuilder(x+","+y);
            if (key.equals(this.startKey)) {
                this.line.setStartX(event.getX());
                this.line.setStartY(event.getY());
            } else if (key.equals(this.endKey)) {
                this.line.setEndX(event.getX());
                this.line.setEndY(event.getY());
            }
        });
    }

    public void setStroke(Color color) {
        this.line.setStroke(color);
    }



}
