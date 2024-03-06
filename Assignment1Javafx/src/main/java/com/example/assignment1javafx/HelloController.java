package com.example.assignment1javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    // JavaFX controls defined in the FXML file
    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private TableView<Movie> tableView;

    @FXML
    private TableColumn<Movie, String> titleColumn;

    @FXML
    private TableColumn<Movie, String> genreColumn;

    @FXML
    private TableColumn<Movie, Integer> releaseYearColumn;

    @FXML
    private TableColumn<Movie, Double> ratingsColumn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button switchButton;

    // Database connection variables
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    // Creating a method to establish a database connection
    public Connection connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment1_data", "root", "1234");

            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Creating a method to load data into the BarChart
    private void loadBarChartData() {
        String query = "SELECT movie_title, user_ratings FROM moviesdata";
        connect = connectDb();

        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            XYChart.Series series = new XYChart.Series();

            // Retrieve data from the database and add it to the BarChart series
            while (result.next()) {
                String movieTitle = result.getString("movie_title");
                double userRatings = result.getDouble("user_ratings");

                // Add data to BarChart series
                series.getData().add(new XYChart.Data<>(movieTitle, userRatings));
            }

            // Add the series to the BarChart
            barChart.getData().add(series);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to load data into the TableView
    private void loadTableViewData() {
        String query = "SELECT * FROM moviesdata";
        connect = connectDb();

        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            // Retrieve data from the database and add it to the TableView
            while (result.next()) {
                tableView.getItems().add(new Movie(
                        result.getString("movie_title"),
                        result.getString("genre"),
                        result.getInt("release_year"),
                        result.getDouble("user_ratings")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Switching method to toggle between BarChart and TableView visibility
    @FXML
    public void switchToTableView() {
        // Hide the BarChart and show the TableView
        barChart.setVisible(false);
        tableView.setVisible(true);
    }

    // Initialize method called by JavaFX during the controller initialization
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize TableView columns with Movie class properties
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        ratingsColumn.setCellValueFactory(new PropertyValueFactory<>("ratings"));

        // Load data into TableView
        loadTableViewData();

        // Initially, hide the TableView
        tableView.setVisible(false);

        // Load data into BarChart
        loadBarChartData();
    }
}
