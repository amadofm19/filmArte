package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Entity representing a viewing history record in the system.")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the history record.", example = "1", required = true)
    @Column(name = "idHistory")
    @JsonProperty("idHistory")
    private int idHistory;

    @NotNull(message = "The viewing date cannot be null.")
    @NotBlank(message = "The viewing date cannot be blank.")
    @Schema(description = "Date when the movie was viewed.", example = "2024-10-21", required = true)
    @Column(name = "viewingDate")
    @JsonProperty("viewingDate")
    private String viewingDate;

    @NotNull(message = "The duration cannot be null.")
    @Schema(description = "Duration of the movie in minutes.", example = "120", required = true)
    @Column(name = "duration")
    @JsonProperty("duration")
    private int duration;

    @NotBlank(message = "The genere cannot be blank.")
    @Size(max = 100, message = "The genere must have a maximum of 100 characters.")
    @Schema(description = "Genere of the movie.", example = "Action", required = true)
    @Column(name = "genere")
    @JsonProperty("genere")
    private String genere;

    @ManyToOne
    @JoinColumn(name = "idMovie")
    private Movie movie;

    public History(int duration, String genere, int idHistory, Movie movie, String viewingDate) {
        this.duration = duration;
        this.genere = genere;
        this.idHistory = idHistory;
        this.movie = movie;
        this.viewingDate = viewingDate;
    }

    public History() {

    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public String getViewingDate() {
        return viewingDate;
    }

    public void setViewingDate(String viewingDate) {
        this.viewingDate = viewingDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "History{"
                + "idHistory=" + idHistory
                + ", viewingDate='" + viewingDate + '\''
                + ", duration=" + duration
                + ", genere='" + genere + '\''
                + ", movie='" + movie + '\''
                + '}';
    }

}
