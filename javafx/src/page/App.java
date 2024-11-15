package page;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class App extends Application {
    private static Stage primaryStage;
    private static Scene mainScene = mainPage.mainScene();
    private static Scene menuScene = menu.menuPage();

    @Override
    public void start(Stage primaryStage){
        App.primaryStage = primaryStage;
        primaryStage.setTitle("Three Musketeers - Graph Coloring Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void changeMainScene(){
        primaryStage.setScene(mainScene);
    }

    public static void changeMenuScene(){
        primaryStage.setScene(menuScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}