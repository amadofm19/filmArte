/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 *
 * @author ARACELI
 */
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
    @Size(max = 255, message = "Description must be at most 255 characters")
    @Column(name = "description")
    @JsonProperty("description")
    private String description;
    
    //RELACIONES
    //Relación con Category 1:N
     @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movie> movies;


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

    @Override
    public String toString() {
        return "Category{" + "idCategory=" + idCategory + ", categoryType='" + categoryType + '\'' + ", description='" + description + '\'' + '}';
    }
}