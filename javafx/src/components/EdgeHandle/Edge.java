package components.EdgeHandle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Edge {
    private Line line;

    public Edge(double x1,double y1,double x2,double y2) {
        this.line = new Line(x1,y1,x2,y2);
    }

    public Edge(){

    }

    public Line getLine() {
        return this.line;
    }



    public void setStroke(Color color) {
        this.line.setStroke(color);
    }



}
