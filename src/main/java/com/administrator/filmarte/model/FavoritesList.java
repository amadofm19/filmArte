package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Schema(description = "Entity representing a favorite movie in the user's favorites list.")
public class FavoritesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the favorite movie.", example = "1", required = true)
    @Column(name = "idFavoritesList")
    @JsonProperty("idFavoritesList")
    private int idFavoritesList;

    @Schema(description = "Title of the movie.", example = "Inception", required = true)
    @NotBlank(message = "El título de la película no puede estar en blanco.")
    @Size(max = 100, message = "El título debe tener un máximo de 100 caracteres.")
    @Column(name = "movieTitle")
    @JsonProperty("movieTitle")
    private String movieTitle;

    @Schema(description = "Description of the movie.", example = "A mind-bending thriller.", required = false)
    @Size(max = 500, message = "La descripción debe tener un máximo de 500 caracteres.")
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Genere of the movie.", example = "Sci-Fi", required = true)
    @NotBlank(message = "El género no puede estar en blanco.")
    @Column(name = "genere")
    @JsonProperty("genere")
    private String genere;

    @Schema(description = "Viewing status of the movie.", example = "Watched", required = true)
    @NotBlank(message = "El estado de visualización no puede estar en blanco.")
    @Column(name = "viewingStatus")
    @JsonProperty("viewingStatus")
    private String viewingStatus;

    @Schema(description = "Rating given to the movie.", example = "5", required = true)
    @NotBlank(message = "The rating must not be blank.")
    @NotNull(message = "The rating cannot be null.")
    @Column(name = "rating")
    @JsonProperty("rating")
    private int rating;

    @Schema(description = "Duration of the movie in minutes.", example = "148", required = true)
    @NotBlank(message = "The duration must not be blank.")
    @NotNull(message = "La duración no puede ser nula.")
    @Column(name = "duration")
    @JsonProperty("duration")
    private int duration;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @JsonBackReference
    private User user;

    public FavoritesList() {

    }

    public FavoritesList(String description, int duration, String genere, int idFavoritesList, String movieTitle, int rating, User user, String viewingStatus) {
        this.description = description;
        this.duration = duration;
        this.genere = genere;
        this.idFavoritesList = idFavoritesList;
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.user = user;
        this.viewingStatus = viewingStatus;
    }

    public int getIdFavoritesList() {
        return idFavoritesList;
    }

    public void setIdFavoritesList(int idFavoritesList) {
        this.idFavoritesList = idFavoritesList;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getViewingStatus() {
        return viewingStatus;
    }

    public void setViewingStatus(String viewingStatus) {
        this.viewingStatus = viewingStatus;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FavoritesList {idFavoritesList=" + idFavoritesList + ", movieTitle=" + movieTitle + ", description="
                + description + ", genere=" + genere + ", viewingStatus=" + viewingStatus + ", rating=" + rating
                + ", duration=" + duration + ", user=" + user + '}';
    }
}
