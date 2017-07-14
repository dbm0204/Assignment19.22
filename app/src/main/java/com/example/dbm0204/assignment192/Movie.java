package com.example.dbm0204.assignment192;

import java.util.HashMap;

/**
 * Created by dbm0204 on 7/12/17.
 * The fields for Movie
 */

public class Movie {
    private String imageURL;
    private String name;
    private double rating;

    public Movie() {}

    public Movie(String imageURL, String name, double rating) {
        this.imageURL = imageURL;
        this.name = name;
        this.rating = rating;
    }

    public Movie(Movie m1){
        this.imageURL=m1.getImageURL();
        this.name=m1.getName();
        this.rating=m1.getRating();
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}

