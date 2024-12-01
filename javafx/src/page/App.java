package page;

import javafx.stage.Stage;
import javafx.application.Application;


public class App extends Application {
    private static Stage primaryStage = new Stage();
    private static Stage secondaryStage = new Stage();

    @Override
    public void start(Stage primaryStage){
        App.primaryStage = primaryStage;
        primaryStage.setTitle("Two(Three) Musketeers - Graph Coloring Game");
        primaryStage.setScene(mainPage.mainScene());
        primaryStage.show();
    }

    public static void changeMainScene(){
        primaryStage.setScene(mainPage.mainScene());
    }

    public static void changeMenuScene(){
        primaryStage.setScene(menu.menuPage());
    }

    public static void changeSelectGameScene(){
        primaryStage.setScene(selectGame.selectGameScene());
    }

    public static void changeCreateGraphScene(){
        primaryStage.setScene(CreateGraphPage.CreateGraph());
    }

    public static void changeUploadGraphScene(){
        primaryStage.setScene(uploadGraph.uploadGraphScene());
    }

    public static void changeRenderGraphScene(int[][] graph){
        secondaryStage.setScene(renderGraph.renderGraphScene(graph));
        secondaryStage.show();
    }

    public static void endScreenScene(){
        primaryStage.setScene(endScreen.endScreenScene());
    }




    public static void main(String[] args) {
        launch(args);
    }
}