package com.example.demo.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;

import java.io.IOException;

import com.example.demo.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomePageController {

    @FXML
    private Button getStartedButton; // This must match the fx:id in FXML

    @FXML
    private Label homeTitle; // Add this to match the fx:id in FXML

    @FXML
    public void initialize() {
        // Fade-in animation for the title
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), homeTitle);
        fadeTransition.setFromValue(0); // Start fully transparent
        fadeTransition.setToValue(1);   // End fully opaque
        fadeTransition.play(); // Play the animation
    }

    public void loadDataFromDatabase() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";  // Example query
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                System.out.println("Username: " + username + ", Email: " + email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleGetStartedButtonClick() {
        try {
            // Load the sidebar page
            Parent sidebarPage = FXMLLoader.load(getClass().getResource("/com/example/demo/view/SidebarView.fxml"));

            // Get the current stage (window)
            Stage stage = (Stage) getStartedButton.getScene().getWindow();

            // Set the new scene (sidebar page)
            Scene scene = new Scene(sidebarPage);

            // Use Platform.runLater to ensure smooth transition
            Platform.runLater(() -> {
                stage.setScene(scene);
                stage.setFullScreen(true); // Ensure fullscreen mode is maintained
                stage.setFullScreenExitHint(""); // Remove the default exit hint
                stage.show();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}