/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.model;


/**
 *
 * @author ARACELI
 */
import java.io.Serializable;
import java.util.Objects;

public class MovieUserPK implements Serializable {

    private int movie;
    private int user;

    public MovieUserPK() {}

    public MovieUserPK(int movie, int user) {
        this.movie = movie;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieUserPK)) return false;
        MovieUserPK that = (MovieUserPK) o;
        return movie == that.movie && user == that.user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, user);
    }

    // Getters y Setters

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
