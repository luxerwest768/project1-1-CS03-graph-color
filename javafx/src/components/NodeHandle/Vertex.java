package components.NodeHandle;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import components.ColorWheel.ColorWheel;

public class Vertex{
    private Circle circle = new Circle(0,0,20,Color.WHITE);
    private int index;
    private static int count = -1;

    public Vertex(){
        this.count++;
        this.index = this.count;
        this.circle.setStroke(Color.BLACK);
    }

    public void setPosition(double x, double y){
        this.circle.setCenterX(x);
        this.circle.setCenterY(y);
    }

    public void drag(){
        this.circle.setOnMouseDragged(event -> {
            this.circle.toFront();
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

    public void setColor(ColorWheel colorWheel, Vertices nodeSet, Label status){
        this.circle.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                if (nodeSet.checkValidColor(index, colorWheel.getColor())){
                    this.circle.setFill(colorWheel.getColor());
                    nodeSet.setColorIndex(index, colorWheel.getColor());
                    status.setText("");
                } else {
                    System.out.println("Invalid color");
                    status.setText("Invalid color!");
                }

            }
        });
    }


    public Circle getCircle(){
        return this.circle;
    }

}
