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
@Schema(description = "Entity representing a viewing history in the system.")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the history record.", example = "1", required = true)
    @Column(name = "idHistory")
    @JsonProperty("idHistory")
    private int idHistory;

    @Schema(description = "Viewing date of the movie.", example = "2024-10-18", required = true)
    @NotNull(message = "La fecha de visualización no puede ser nula.")
    @NotBlank(message = "La fecha de visualización no puede estar en blanco.")
    @Column(name = "viewingDate")
    @JsonProperty("viewingDate")
    private String viewingDate;

    @Schema(description = "Duration of the movie in minutes.", example = "120", required = true)
    @NotNull(message = "La duración no puede ser nula.")
    @Column(name = "duration")
    @JsonProperty("duration")
    private int duration;

    @Schema(description = "Genre of the movie.", example = "Action", required = true)
    @NotBlank(message = "El género no puede estar en blanco.")
    @Size(max = 100, message = "El género debe tener un máximo de 100 caracteres.")
    @Column(name = "genre")
    @JsonProperty("genre")
    private String genre;

    // Getters y Setters
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return idHistory + " :: " + viewingDate + " :: " + duration + " minutes :: " + genre;
    }
}
