package com.administrator.filmarte.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "genre")
@Schema(description = "Entity representing a genre in the system.")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the genre.", example = "1", required = true)
    @Column(name = "idGenre") // Ajustado para que coincida con el SQL
    @JsonProperty("idGenre")
    private int idGenre;

    @Schema(description = "Name of the genre.", example = "Action", required = true)
    @NotBlank(message = "The name must not be blank.")
    @Size(min = 1, max = 50, message = "The name must be between 1 and 50 characters.") 
                                                                                    
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Description of the genre.", example = "Fast-paced action movies.", required = false)
    @Size(max = 100, message = "The description must be at most 100 characters.") // Ajustado para que coincida con el
                                                                                  // SQL
    @Column(name = "description", nullable = false) // Añadido `nullable = false` para indicar que no puede ser nula
    @JsonProperty("description")
    private String description;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;

    // Getters y setters
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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "idGenre=" + idGenre +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
