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
@Schema(description = "Entity representing a movie in the system.")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the movie.", example = "1", required = true)
    @Column(name = "idMovie")
    @JsonProperty("idMovie")
    private int idMovie;

    @Schema(description = "Title of the movie.", example = "Inception", required = true)
    @NotNull(message = "El título no puede ser nulo.")
    @NotBlank(message = "El título no puede estar en blanco.")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres.")
    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @Schema(description = "Year the movie was released.", example = "2010", required = true)
    @NotNull(message = "El año no puede ser nulo.")
    @Column(name = "year")
    @JsonProperty("year")
    private int year;

    @Schema(description = "Description of the movie.", example = "A mind-bending thriller.", required = false)
    @NotBlank(message = "La descripción no puede estar en blanco.")
    @Size(max = 500, message = "La descripción debe tener un máximo de 500 caracteres.")
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    // Getters and Setters
    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method
    @Override
    public String toString() {
        return idMovie + " :: " + title + " :: " + year + " :: " + description;
    }
}
