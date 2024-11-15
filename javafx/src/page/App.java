package page;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class App extends Application {
    @Override
    public void start(Stage primaryStage){
        Button btn = new Button();
        Text txt = new Text("                        Welcome to \n Three Musketeers - Graph Coloring Game");
        btn.getStyleClass().add("play-button");
        txt.getStyleClass().add("txt");
        btn.setText("Play");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                System.out.println("Let Play");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(txt,btn);
        Scene scene = new Scene(root,1300,1000);
        scene.getStylesheets().addAll("./css/homePage.css");
        primaryStage.setTitle("Three Musketeers - Graph Coloring Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}