package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Entity representing a favorites list in the system.")
public class FavoritesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the favorites list.", example = "1", required = true)
    @Column(name = "idFavoritesList")
    @JsonProperty("idFavoritesList")
    private int idFavoritesList;

    @Schema(description = "Title of the movie.", example = "Inception", required = true)
    @NotBlank(message = "El título de la película no puede estar en blanco.")
    @Column(name = "movieTitle")
    @JsonProperty("movieTitle")
    private String movieTitle;

    @Schema(description = "Description of the movie.", example = "A mind-bending thriller.", required = false)
    @Size(max = 500, message = "La descripción debe tener un máximo de 500 caracteres.")
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Genre of the movie.", example = "Sci-Fi", required = true)
    @NotBlank(message = "El género no puede estar en blanco.")
    @Column(name = "genre")
    @JsonProperty("genre")
    private String genre;

    @Schema(description = "Viewing status of the movie.", example = "Watched", required = true)
    @NotBlank(message = "El estado de visualización no puede estar en blanco.")
    @Column(name = "viewingStatus")
    @JsonProperty("viewingStatus")
    private String viewingStatus;

    @Schema(description = "Rating of the movie.", example = "5", required = true)
    @NotNull(message = "La calificación no puede ser nula.")
    @Column(name = "rating")
    @JsonProperty("rating")
    private int rating;

    @Schema(description = "Duration of the movie in minutes.", example = "148", required = true)
    @NotNull(message = "La duración no puede ser nula.")
    @Column(name = "duration")
    @JsonProperty("duration")
    private int duration;

    // Getters y Setters
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    // Método toString para mostrar la información de la lista de favoritos
    @Override
    public String toString() {
        return idFavoritesList + " :: " + movieTitle + " :: " + description + " :: " + genre +
                " :: " + viewingStatus + " :: " + rating + " :: " + duration + " mins";
    }
}
