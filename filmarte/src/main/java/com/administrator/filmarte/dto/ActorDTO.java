package com.administrator.filmarte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ActorDTO {
    private int idActor;
    
    @NotBlank(message = "DTO: First name is mandatory")
    @Size(min = 1, max = 30, message = "DTO:First name must be between 1 and 30 characters")
    private String firstName;

    @NotBlank(message = "DTO: Last name is mandatory")
    @Size(min = 1, max = 30, message = "Last name must be between 1 and 30 characters")
    private String lastName;

    @Size(max = 255, message = "DTO: Description must be at most 255 characters")
    private String description;

    public int getIdActor(){
        return idActor;
    }

    public void setIdActor(int idActor){
        this.idActor = idActor;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
