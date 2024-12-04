/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ARACELI
 */

 @Getter
 @Setter
@Entity
@Table(name = "actor")
@Schema(description = "Entity representing an actor in the system.")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the actor.", example = "1", required = true)
    @Column(name = "idActor")
    @JsonProperty("idActor")
    private int idActor;

    @Schema(description = "First name of the actor.", example = "John", required = true)
    @NotBlank(message = "First name is mandatory")
    @Size(min = 1, max = 30, message = "First name must be between 1 and 30 characters")
    @Column(name = "firstName")
    @JsonProperty("firstName")
    private String firstName;

    @Schema(description = "Last name of the actor.", example = "Doe", required = true)
    @NotBlank(message = "Last name is mandatory")
    @Size(min = 1, max = 30, message = "Last name must be between 1 and 30 characters")
    @Column(name = "lastName")
    @JsonProperty("lastName")
    private String lastName;

    @Schema(description = "Description of the actor.", example = "An experienced actor in theater and film.")
    @NotBlank(message = "The description must not be blank.")
    @Size(max = 255, message = "Description must be at most 255 characters")
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    public Actor() {
    }

    public Actor(String description, String firstName, int idActor, String lastName) {
        this.description = description;
        this.firstName = firstName;
        this.idActor = idActor;
        this.lastName = lastName;
    }

    // RELACIONES
    // RELACION CON Movie_Actor

    // public int getIdActor() {
    //     return idActor;
    // }

    // public void setIdActor(int idActor) {
    //     this.idActor = idActor;
    // }

    // public String getFirstName() {
    //     return firstName;
    // }

    // public void setFirstName(String firstName) {
    //     this.firstName = firstName;
    // }

    // public String getLastName() {
    //     return lastName;
    // }

    // public void setLastName(String lastName) {
    //     this.lastName = lastName;
    // }

    // public String getDescription() {
    //     return description;
    // }

    // public void setDescription(String description) {
    //     this.description = description;
    // }


    @Override
    public String toString() {
        return "Actor{" + "idActor=" + idActor + ", firstName=" + firstName + ", lastName=" + lastName
                + ", description=" + description + '}';
    }
}
