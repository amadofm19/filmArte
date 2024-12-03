package com.administrator.filmarte.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "The title cannot be blank.")
    @Size(min = 1, max = 100, message = "The title must have between 1 and 100 characters.")
    @Schema(description = "Title of the movie.", example = "Inception", required = true)
    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @NotBlank(message = "The year cannot be blank.")
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

    @ManyToOne
    @JoinColumn(name = "idCategory", nullable = false)
    @JsonProperty("category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "idGenere", nullable = false)
    @JsonProperty("genere")
    private Genere genere;

    @ManyToOne
    @JoinColumn(name = "idAdministrator", nullable = false)
    @JsonProperty("administrator")
    private Administrator administrator;

    @ManyToOne
    @JoinColumn(name = "idDirector", nullable = false)
    @JsonProperty("director")
    private Director director;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<History> historys;

    @OneToMany(mappedBy = "movie")
    @JsonProperty("rewards")
    private List<Reward> rewards;

    public Movie(Administrator administrator, Category category, String description, Director director, Genere genere, List<History> historys, int idMovie, List<Reward> rewards, String title, int year) {
        this.administrator = administrator;
        this.category = category;
        this.description = description;
        this.director = director;
        this.genere = genere;
        this.historys = historys;
        this.idMovie = idMovie;
        this.rewards = rewards;
        this.title = title;
        this.year = year;
    }

    public Movie() {

    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public List<History> getHistorys() {
        return historys;
    }

    public void setHistorys(List<History> historys) {
        this.historys = historys;
    }

    @Override
    public String toString() {
        return "Movie{"
                + "idMovie=" + idMovie
                + ", title='" + title + '\''
                + ", year=" + year
                + ", description='" + description + '\''
                + ", category=" + category
                + ", administrator=" + administrator
                + '}';
    }
}
