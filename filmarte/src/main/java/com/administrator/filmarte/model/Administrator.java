package com.administrator.filmarte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entity representing an administrator in the system.")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the administrator.", example = "1", required = true)
    private int idAdministrator;

    @Schema(description = "First name of the administrator.", example = "John", required = true)
    private String name;

    @Schema(description = "Last name of the administrator.", example = "Doe", required = true)
    private String lastname;

    @Schema(description = "Email address of the administrator.", example = "john.doe@example.com", required = true)
    private String email;

    @Schema(description = "Password for the administrator's account.", example = "password123", required = true)
    private String password;

    public int getIdAdministrator(){
        return idAdministrator;
    }
    public void setIdAdministrator(int idAdministrator){
        this.idAdministrator = idAdministrator;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return idAdministrator + " :: " + name + " :: " + lastname + " :: " + email + " :: " + password;
    }
}
