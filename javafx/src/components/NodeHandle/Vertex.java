package components.NodeHandle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Vertex {
    private Circle circle = new Circle(0,0,20,Color.WHITE);
    private static int index = -1;

    public Vertex(){
        this.index++;
        this.circle.setStroke(Color.BLACK);

    }

    public void setPosition(double x, double y){
        this.circle.setCenterX(x);
        this.circle.setCenterY(y);
    }

    public void drag(){
        this.circle.setOnMouseDragged(event -> {
            this.circle.setCenterX(event.getSceneX());
            this.circle.setCenterY(event.getSceneY());
        });
    }

    public void setX(double x){
        this.circle.setCenterX(x);
    }

    public void setY(double y){
        this.circle.setCenterY(y);
    }

    public double getX(){
        return this.circle.getCenterX();
    }

    public double getY(){
        return this.circle.getCenterY();
    }

    public void setColor(Color color){
        this.circle.setFill(color);
    }

    public int getIndex(){
        return index;
    }

    public Circle getCircle(){
        return this.circle;
    }
}
