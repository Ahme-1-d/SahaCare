package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the home page
        Parent homePage = FXMLLoader.load(getClass().getResource("/com/example/demo/view/HomePage.fxml"));

        // Set up the scene
        Scene scene = new Scene(homePage);

        // Configure the stage
        primaryStage.setTitle("Futuristic App");
        primaryStage.setScene(scene);

        // Set the stage to fullscreen mode
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint(""); // Remove the default exit hint
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}