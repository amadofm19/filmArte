package com.administrator.filmarte.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "administrator")
@Schema(description = "Entity representing an administrator in the system.")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the administrator.", example = "1", required = true)
    @Column(name = "idAdministrador")
    @JsonProperty("idAdministrator")
    private int idAdministrator;

    @Schema(description = "First name of the administrator.", example = "John", required = true)
    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 30, message = "The content must be at most 30 characters, and has at least one character")
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Last name of the administrator.", example = "Doe", required = true)
    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 30, message = "The content must be at most 30 characters, and has at least one character")
    @Column(name = "lastname")
    @JsonProperty("lastname")
    private String lastname;

    @Schema(description = "Email address of the administrator.", example = "john.doe@example.com", required = true)
    @Email(message = "The email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Schema(description = "Password for the administrator's account.", example = "password123", required = true)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "The password must be at least 8 characters")
    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    //Relation OneToMany with movie 
    @Column(name = "Movie")
    @JsonProperty("Movie")
    @JsonManagedReference
    @OneToMany(mappedBy = "idAdministrator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movies;

    public int getIdAdministrator() {
        return idAdministrator;
    }

    public void setIdAdministrator(int idAdministrator) {
        this.idAdministrator = idAdministrator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return idAdministrator + " :: " + name + " :: " + lastname + " :: " + email + " :: " + password;
    }
}
