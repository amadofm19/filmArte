package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovie;

    @NotNull(message = "El título no puede ser nulo.")
    @NotBlank(message = "El título no puede estar en blanco.")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres.")
    private String title;

    @NotNull(message = "El año no puede ser nulo.")
    private int year;

    @NotBlank(message = "La descripción no puede estar en blanco.")
    @Size(max = 500, message = "La descripción debe tener un máximo de 500 caracteres.")
    private String description;

    // RELACIONES
    // RELACION CON CATEGORIA
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // RELACION CON DIRECTOR
    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    // RELACION CON MovieGenre
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Movie_Genre> movieGenres = new HashSet<>();

    // RELACION CON ADMINISTRADOR
    @ManyToOne
    @JoinColumn(name = "idAdministrator", nullable = false)
    private Administrator administrator;

    // RELACION CON Movie_Actor
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Movie_Actor> movieActors = new HashSet<>();

    // RELACION CON PREMIO (Reward)
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reward> rewards = new HashSet<>();

    // RELACION MUCHOS A MUCHOS CON LA ENTIDAD USER
    @ManyToMany
    @JoinTable(name = "movie_user", joinColumns = @JoinColumn(name = "idMovie"), inverseJoinColumns = @JoinColumn(name = "idUser"))
    private Set<User> users = new HashSet<>();

    // RELACION CON USUARIO
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    @JsonProperty("user")
    private User user;

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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" + "idMovie=" + idMovie + ", title=" + title + ", year=" + year + ", description=" + description
                + ", category=" + category + ", director=" + director + '}';
    }

}
