package com.administrator.filmarte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "La fecha de visualización no puede ser nula.")
    @NotBlank(message = "La fecha de visualización no puede estar en blanco.")
    private String viewingDate; // Fecha de visualización

    @NotNull(message = "La duración no puede ser nula.")
    private int duration; // Duración en minutos

    @NotBlank(message = "El género no puede estar en blanco.")
    @Size(max = 100, message = "El género debe tener un máximo de 100 caracteres.")
    private String genre; // Género de la película

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return id + " :: " + viewingDate + " :: " + duration + " minutes :: " + genre;
    }
}
