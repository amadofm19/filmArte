package com.administrator.filmarte.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @NotNull(message = "The title cannot be null.")
    @NotBlank(message = "The title cannot be blank.")
    @Size(min = 1, max = 100, message = "The title must have between 1 and 100 characters.")
    @Schema(description = "Title of the movie.", example = "Inception", required = true)
    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @NotNull(message = "The year cannot be null.")
    @Schema(description = "Year the movie was released.", example = "2010", required = true)
    @Column(name = "year")
    @JsonProperty("year")
    private int year;

    @NotBlank(message = "The description cannot be blank.")
    @Size(max = 500, message = "The description must have a maximum of 500 characters.")
    @Schema(description = "Description of the movie.", example = "A thief who steals corporate secrets through the use of dream-sharing technology.", required = true)
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategory")
    @JsonProperty("idCategory")
    @JsonBackReference
    private Category idCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDirector")
    @JsonProperty("idDirector")
    @JsonBackReference
    private Director idDirector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAdministrator")
    @JsonProperty("idAdministrator")
    @JsonBackReference
    private Administrator idAdministrator;

    //Relation OneToMany whit reward
    @Column(name = "Reward")
    @JsonProperty("Reward")
    @JsonManagedReference
    @OneToMany(mappedBy = "idMovie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reward> rewards;

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


    @Override
    public String toString() {
        return "Movie{" +
                "idMovie=" + idMovie +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                '}';
    }
}