package page;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class mainPage  {

    static Scene mainScene(){
        Button btn = new Button();
        Text txt = new Text("                        Welcome to \n Two-Three Musketeers - Graph Coloring Game");
        btn.getStyleClass().add("play-button");
        txt.getStyleClass().add("txt");
        btn.setText("Play");

        btn.setOnAction(e -> {
            App.changeMenuScene();
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(txt,btn);
        Scene scene = new Scene(root,1300,1000);
        scene.getStylesheets().addAll("./css/homePage.css");

        return scene;
    }
}
