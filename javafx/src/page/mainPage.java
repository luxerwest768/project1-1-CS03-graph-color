package page;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class mainPage  {

    static Scene mainScene(){
        Button btn = new Button();
        Text txt = new Text("                            Welcome to \n Two-Three Musketeers - Graph Coloring Game");
        btn.getStyleClass().add("play-button");
        txt.getStyleClass().add("txt");
<<<<<<< HEAD
        btn.setText("Phase2");

        Button newBtn = new Button();
        newBtn.getStyleClass().add("clickHere-button");
        newBtn.setText("Phase3");
=======
        btn.setText("Play Phase 2");

        Button newBtn = new Button();
        newBtn.getStyleClass().add("clickHere-button");
        newBtn.setText(" Play Phase 3");
>>>>>>> 5724b57ce89a1e2cf1d9e987fa1f4d120572ebb2

        newBtn.setOnAction(e -> {
            App.changeUploadGraphScenePhase3();
        });
        btn.setOnAction(e -> {
            App.changeSelectGameScene();
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(txt,btn, newBtn);
        Scene scene = new Scene(root,900,700);
        scene.getStylesheets().addAll("./css/homePage.css");

        return scene;
    }
}
