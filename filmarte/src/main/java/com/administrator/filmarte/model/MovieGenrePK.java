package com.administrator.filmarte.model;

import java.io.Serializable;
import java.util.Objects;

public class MovieGenrePK implements Serializable {

    private int movie;
    private int genre;

    public MovieGenrePK() {}

    public MovieGenrePK(int movie, int genre) {
        this.movie = movie;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieGenrePK)) return false;
        MovieGenrePK that = (MovieGenrePK) o;
        return movie == that.movie && genre == that.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, genre);
    }

    // Getters y Setters

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
}
