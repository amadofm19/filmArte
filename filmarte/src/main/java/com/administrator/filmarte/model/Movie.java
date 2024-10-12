package com.administrator.filmarte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El título no puede ser nulo.")
    @NotBlank(message = "El título no puede estar en blanco.")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres.")
    private String title;

    @NotNull(message = "El año no puede ser nulo.")
    private int year;

    @NotBlank(message = "La descripción no puede estar en blanco.")
    @Size(max = 500, message = "La descripción debe tener un máximo de 500 caracteres.")
    private String description;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    // toString method
    @Override
    public String toString() {
        return id + " :: " + title + " :: " + year + " :: " + description;
    }
}
