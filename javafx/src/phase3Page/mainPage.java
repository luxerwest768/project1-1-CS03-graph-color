package phase3Page;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class mainPage {
    static Scene mainScene(){
     Button btn = new Button();
     Text txt = new Text("                            Welcome to \n Two-Three Musketeers - Graph Coloring Game");
     btn.getStyleClass().add("play-button");
        txt.getStyleClass().add("txt");
        btn.setText("Click Here!");

        btn.setOnAction(e -> {
            App.changeUploadGraphScene();
        });


        StackPane root = new StackPane();
        root.getChildren().addAll(txt,btn);
        Scene scene = new Scene(root,900,700);
        scene.getStylesheets().addAll("./css/homePage.css");

        return scene;
    }
}
