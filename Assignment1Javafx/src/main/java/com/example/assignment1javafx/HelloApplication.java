package com.example.assignment1javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Main application class extending JavaFX Application
public class HelloApplication extends Application {
    // Override the start method to set up the primary stage
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file and create a Scene
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);

        // Set the title of the primary stage
        stage.setTitle("Movie dataset");

        // Set the Scene for the primary stage
        stage.setScene(scene);

        // Display the primary stage
        stage.show();
    }

    // The main method, which is the entry point of the Java application
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }
}
