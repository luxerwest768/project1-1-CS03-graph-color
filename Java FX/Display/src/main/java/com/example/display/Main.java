package com.example.display;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Welcome to 2(Three Musketeers) Graph Coloring game!");
        stage.setScene(scene);
        stage.show();

        // stage.setWidth(900);
        // stage.setHeight(600);
        // stage.setFullScreen(true);

    }

    public static void main(String[] args) {
        launch();
    }
}