package com.administrator.filmarte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre no puede estar en blanco.")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres.")
    private String name;

    @Size(max = 500, message = "La descripción debe tener un máximo de 500 caracteres.")
    private String description;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    // toString method
    @Override
    public String toString() {
        return id + " :: " + name + " :: " + description;
    }
}
