package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Entity representing a genre in the system.")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the genre.", example = "1", required = true)
    @Column(name = "idGenre")
    @JsonProperty("idGenre")
    private int idGenre;

    @Schema(description = "Name of the genre.", example = "Action", required = true)
    @NotBlank(message = "El nombre no puede estar en blanco.")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres.")
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Description of the genre.", example = "Action movies include a lot of exciting scenes.", required = false)
    @Size(max = 500, message = "La descripción debe tener un máximo de 500 caracteres.")
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    // Getters and Setters
    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return idGenre + " :: " + name + " :: " + description;
    }
}
