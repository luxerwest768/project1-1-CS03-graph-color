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

    public static void changeCreateGraphScene(int gamemode){
        primaryStage.setScene(CreateGraphPage.CreateGraph(gamemode));
    }

    public static void changeUploadGraphScene(int gamemode){
        primaryStage.setScene(uploadGraph.uploadGraphScene(gamemode));
    }

    public static void changeRenderGraphScene(int[][] graph){
        secondaryStage.setScene(renderGraph.renderGraphScene(graph));
        secondaryStage.show();
    }

    public static void changeToTheBitterEndScene(int[][] graph, int CN){
        secondaryStage.setScene(ToTheBitterEnd.toTheBitterEndScene(graph,CN));
        secondaryStage.show();
    }

    public static void changeRandomOrderScene(int[][] graph,int CN){
        secondaryStage.setScene(RandomOrder.randomOrderScene(graph,CN));
        secondaryStage.show();
    }

    public static void changeIChangeMyMindScene(int[][] graph, int CN){
        secondaryStage.setScene(IChangeMyMind.iChangeMyMindScene(graph,CN));
        secondaryStage.show();
    }

    public static void endScreenScene(String gameMode) {
        EndScreenBase endScreen;

        switch (gameMode) {
            case "BitterEnd":
                endScreen = new BitterEndModeEndScreen();
                break;

            case "RandomOrder":
                endScreen = new RandomColorsModeEndScreen();
                break;

            case "IChangeMyMind":
                endScreen = new RandomColorsModeEndScreen();
                break;

            default:
                endScreen = new EndScreenBase();
                break;
        }


        primaryStage.setScene(endScreen.createEndScreen());
    }

    public static void winScreenScene(int gamemode, int CN, int score){
        primaryStage.setScene(winScreen.winScreenScene(gamemode,CN,score));
    }

    public static void closeGameScene(){
        secondaryStage.close();
    }




    public static void main(String[] args) {
        launch(args);
    }
}