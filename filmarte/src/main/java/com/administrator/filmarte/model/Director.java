package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Entity representing a director in the system.")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the director.", example = "1", required = true)
    @Column(name = "idDirector")
    @JsonProperty("idDirector")
    private int idDirector;

    @Schema(description = "First name of the director.", example = "John", required = true)
    @NotBlank(message = "El nombre no puede estar en blanco.")
    @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres.")
    @Column(name = "firstName")
    @JsonProperty("firstName")
    private String firstName;

    @Schema(description = "Father's last name of the director.", example = "Doe", required = true)
    @NotBlank(message = "El apellido paterno no puede estar en blanco.")
    @Size(min = 1, max = 50, message = "El apellido paterno debe tener entre 1 y 50 caracteres.")
    @Column(name = "lastNameFather")
    @JsonProperty("lastNameFather")
    private String lastNameFather;

    @Schema(description = "Mother's last name of the director.", example = "Smith", required = true)
    @NotBlank(message = "El apellido materno no puede estar en blanco.")
    @Size(min = 1, max = 50, message = "El apellido materno debe tener entre 1 y 50 caracteres.")
    @Column(name = "lastNameMother")
    @JsonProperty("lastNameMother")
    private String lastNameMother;

    // Getters and Setters
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

    // toString method
    @Override
    public String toString() {
        return idDirector + " :: " + firstName + " :: " + lastNameFather + " :: " + lastNameMother;
    }
}
