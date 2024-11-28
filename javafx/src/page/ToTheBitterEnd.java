package page;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import components.ColorWheel.ColorWheel;


public class ToTheBitterEnd extends Application{
    @Override
    public void start(Stage primaryStage){
        //Grid to display colours

        int width = 900;
        int height = 700;
        int radius = 180;

        ColorWheel colorWheel = new ColorWheel(radius);

        StackPane root = new StackPane(colorWheel.getCanvas());
        Scene scene =  new Scene(root, 900, 700);
        primaryStage.setTitle("Colors");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}