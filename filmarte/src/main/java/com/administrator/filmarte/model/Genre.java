package com.administrator.filmarte.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Entity representing a genre in the system.")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the genre.", example = "1", required = false)
    @Column(name = "idGenre") // Ajustado para que coincida con el SQL
    @JsonProperty("idGenre")
    private int idGenre;

    @Schema(description = "Name of the genre.", example = "Action", required = false)
    @NotBlank(message = "The name must not be blank.")
    @Size(min = 1, max = 50, message = "The name must be between 1 and 50 characters.") // Ajustado para que coincida
                                                                                        // con el SQL
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Description of the genre.", example = "Fast-paced action movies.", required = false)
    @Size(max = 100, message = "The description must be at most 100 characters.") // Ajustado para que coincida con el
                                                                                  // SQL
    @Column(name = "description", nullable = false) // Añadido `nullable = false` para indicar que no puede ser nula
    @JsonProperty("description")
    private String description;

    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private List<Movie> movies;

   

    public Genre() {
 
    }

    public Genre(String description, int idGenre, List<Movie> movies, String name) {
        this.description = description;
        this.idGenre = idGenre;
        this.movies = movies;
        this.name = name;
    }



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

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
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
