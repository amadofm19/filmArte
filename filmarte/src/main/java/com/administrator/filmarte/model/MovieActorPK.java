package com.administrator.filmarte.model;

import java.io.Serializable;
import java.util.Objects;
public class MovieActorPK implements Serializable {

    private int movie;
    private int actor;

    public MovieActorPK() {}

    public MovieActorPK(int movie, int actor) {
        this.movie = movie;
        this.actor = actor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieActorPK)) return false;
        MovieActorPK that = (MovieActorPK) o;
        return movie == that.movie && actor == that.actor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, actor);
    }

    // Getters y Setters
    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    public int getActor() {
        return actor;
    }

    public void setActor(int actor) {
        this.actor = actor;
    }
}