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
@Schema(description = "Entity representing a category in the system.")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the category.", example = "1", required = true)
    @Column(name = "idCategory")
    @JsonProperty("idCategory")
    private int idCategory;

    @Schema(description = "Type of the category.", example = "Entertainment", required = true)
    @NotBlank(message = "Category type is mandatory")
    @Size(min = 1, max = 50, message = "Category type must be between 1 and 50 characters")
    @Column(name = "categoryType")
    @JsonProperty("categoryType")
    private String categoryType;

    @Schema(description = "Description of the category.", example = "Includes movies, music, and games.")
    @NotBlank(message = "The description must not be blank.")
    @Size(max = 255, message = "Description must be at most 255 characters")
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonIgnore 
    private List<Movie> movies;

    public Category(String categoryType, String description, int idCategory, List<Movie> movies) {
        this.categoryType = categoryType;
        this.description = description;
        this.idCategory = idCategory;
        this.movies = movies;
    }

    public Category() {

    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
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
        return "Category{"
                + "idCategory=" + idCategory
                + ", categoryType='" + categoryType + '\''
                + ", description='" + description + '\''
                + ", movies=" + movies
                + '}';
    }
}
