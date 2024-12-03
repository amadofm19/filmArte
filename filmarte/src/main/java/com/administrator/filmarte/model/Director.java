package com.administrator.filmarte.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Entity representing a director in the system.")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the director.", example = "1", required = true)
    @JsonProperty("idDirector")
    private int idDirector;

    @NotBlank(message = "First name cannot be blank.")
    @Size(max = 30, message = "First name must have a maximum of 30 characters.")
    @Schema(description = "First name of the director.", example = "Christopher", required = true)
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank(message = "Last name (father) cannot be blank.")
    @Size(max = 30, message = "Last name (father) must have a maximum of 30 characters.")
    @Schema(description = "Last name of the director's father.", example = "Nolan", required = true)
    @JsonProperty("lastNameFather")
    private String lastNameFather;

    @NotBlank(message = "Last name (mother) cannot be blank.")
    @Size(max = 30, message = "Last name (mother) must have a maximum of 30 characters.")
    @Schema(description = "Last name of the director's mother.", example = "Smith", required = true)
    @JsonProperty("lastNameMother")
    private String lastNameMother;

    @OneToMany(mappedBy = "director")
    @JsonIgnore
    private List<Movie> movies;

    public Director(String firstName, int idDirector, String lastNameFather, String lastNameMother, List<Movie> movies) {
        this.firstName = firstName;
        this.idDirector = idDirector;
        this.lastNameFather = lastNameFather;
        this.lastNameMother = lastNameMother;
        this.movies = movies;
    }

    public Director() {

    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNameFather() {
        return lastNameFather;
    }

    public void setLastNameFather(String lastNameFather) {
        this.lastNameFather = lastNameFather;
    }

    public String getLastNameMother() {
        return lastNameMother;
    }

    public void setLastNameMother(String lastNameMother) {
        this.lastNameMother = lastNameMother;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Director{"
                + "idDirector=" + idDirector
                + ", firstName='" + firstName + '\''
                + ", lastNameFather='" + lastNameFather + '\''
                + ", lastNameMother='" + lastNameMother + '\''
                + '}';
    }
}
