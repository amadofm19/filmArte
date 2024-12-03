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
@Schema(description = "Entity representing a genere in the system.")
public class Genere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the genere.", example = "1", required = false)
    @Column(name = "idGenere")
    @JsonProperty("idGenere")
    private int idGenere;

    @Schema(description = "Name of the genere.", example = "Action", required = false)
    @NotBlank(message = "The name must not be blank.")
    @Size(min = 1, max = 50, message = "The name must be between 1 and 50 characters.")
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Description of the genere.", example = "Fast-paced action movies.", required = false)
    @NotBlank(message = "The description must not be blank.")
    @Size(max = 100, message = "The description must be at most 100 characters.")
    @Column(name = "description", nullable = false)
    @JsonProperty("description")
    private String description;

    @OneToMany(mappedBy = "genere")
    @JsonIgnore
    private List<Movie> movies;

    public Genere() {

    }

    public Genere(String description, int idGenere, List<Movie> movies, String name) {
        this.description = description;
        this.idGenere = idGenere;
        this.movies = movies;
        this.name = name;
    }

    public int getIdGenere() {
        return idGenere;
    }

    public void setIdGenere(int idGenere) {
        this.idGenere = idGenere;
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
        return "Genere{"
                + "idGenere=" + idGenere
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
