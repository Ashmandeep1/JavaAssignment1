package com.example.assignment1javafx;

// Creating Movie class representing a movie with title, genre, release year, and ratings

public class Movie {
    private String title;
    private String genre;
    private int releaseYear;
    private double ratings;

    // Constructor to initialize Movie object with provided values
    public Movie(String title, String genre, int releaseYear, double ratings) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.ratings = ratings;
    }

    //Getter methods
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRatings() {
        return ratings;
    }
}

