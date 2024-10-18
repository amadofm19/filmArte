package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class FavoritesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El título de la película no puede estar en blanco.")
    private String movieTitle;

    @Size(max = 500, message = "La descripción debe tener un máximo de 500 caracteres.")
    private String description;

    @NotBlank(message = "El género no puede estar en blanco.")
    private String genre;

    @NotBlank(message = "El estado de visualización no puede estar en blanco.")
    private String viewingStatus; // Estado de visualización

    @NotNull(message = "La calificación no puede ser nula.")
    private int rating; // Calificación

    @NotNull(message = "La duración no puede ser nula.")
    private int duration; // Duración en minutos
    
     // Relación muchos a uno con la entidad User
    @ManyToOne // Indica que una lista de favoritos pertenece a un único usuario
    @JoinColumn(name = "idUser", nullable = false) // Clave foránea que referencia a User
    @JsonProperty("user")
    private User user;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id + " :: " + movieTitle + " :: " + description + " :: " + genre +
                " :: " + viewingStatus + " :: " + rating + " :: " + duration + " mins";
    }
}
