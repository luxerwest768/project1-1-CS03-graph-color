package components.ColorWheel;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.ArcType;
import javafx.scene.image.WritableImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColorWheel {
    private Canvas canvas;
    private Color currentColor = Color.WHITE;

    public ColorWheel(int radius) {
        this.canvas = new Canvas(radius*2, radius*2);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawColorWheel(gc,radius,radius,radius);
        this.canvas.setOnMouseClicked(event -> {
            Color color = colorAtPixel(event,canvas);
            if (color != null) {
                int red = (int) (color.getRed() * 255);
                int blue  = (int) (color.getBlue() * 255);
                int green = (int) (color.getGreen() * 255);
                //System.out.println(red + " " + green + " " + blue);
                this.currentColor = Color.rgb(red,green,blue);
            }
        });
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void drawColorWheel(GraphicsContext gc, int centerX, int centerY,int radius){
        for (int angle = 0; angle < 360; angle++) {
            double startAngle = angle;;
            double extend = 2;

            Color color = Color.hsb(angle, 1.0, 1.0); // Full saturation and brightness

            gc.setFill(color);
            gc.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2,
                    startAngle, extend, ArcType.ROUND);
        }
    }

    public Color getColor(){
        return this.currentColor;
    }

    public Color colorAtPixel(MouseEvent event, Canvas canvas){
        int x = (int) event.getX();
        int y = (int) event.getY();

        WritableImage pixel = canvas.snapshot(null, null);
        Color color = pixel.getPixelReader().getColor(x, y);
        return color;
    }
}
