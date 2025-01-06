package phase3Page;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

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

    public static void changeCreateGraphScene(){
        primaryStage.setScene(createGraphPage.createGraph());
    }

    public static void changeUploadGraphScene(){
        primaryStage.setScene(uploadGraph.uploadGraphScene());
    }
  
    public static void changeRenderGraphScene(int[][] graph){
        secondaryStage.setScene(renderGraph.renderGraphScene(graph));
        secondaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
