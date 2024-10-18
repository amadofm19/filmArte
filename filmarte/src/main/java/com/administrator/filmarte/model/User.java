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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Schema(description = "Entity representing a user in the system.")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the user.", example = "1", required = true)
    @Column(name = "idUser")
    @JsonProperty("idUser")
    private int idUser;

    @Schema(description = "First name of the user.", example = "John", required = true)
    @NotBlank(message = "First name is mandatory")
    @Size(min = 1, max = 30, message = "First name must be between 1 and 30 characters")
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Last name of the user.", example = "Doe", required = true)
    @NotBlank(message = "Last name is mandatory")
    @Size(min = 1, max = 30, message = "Last name must be between 1 and 30 characters")
    @Column(name = "lastname")
    @JsonProperty("lastname")
    private String lastname;

    @Schema(description = "Email address of the user.", example = "john.doe@example.com", required = true)
    @Email(message = "The email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Schema(description = "Membership type of the user.", example = "Premium", required = true)
    @NotBlank(message = "Membership type is mandatory")
    @Column(name = "membership")
    @JsonProperty("membership")
    private String membership;

    @Schema(description = "Username of the user.", example = "johndoe", required = true)
    @NotBlank(message = "Username is mandatory")
    @Size(min = 1, max = 30, message = "Username must be between 1 and 30 characters")
    @Column(name = "username")
    @JsonProperty("username")
    private String username;

    @Schema(description = "Password for the user's account.", example = "password123", required = true)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "The password must be at least 8 characters")
    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    // RELACION N:M CON PELICULA
    @ManyToMany(mappedBy = "users")
    private Set<Movie> movies = new HashSet<>();

    // RELACION CON COMENTARIOS
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty("comments")
    private List<Comment> comments = new ArrayList<>();

    // RELACION CON LISTA DE FAVORITOS
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Indica que un usuario puede tener muchas listas de favoritos
    @JsonProperty("favoritesLists")
    private List<FavoritesList> favoritesLists;

    // RELACION CON SUSCRIPCION
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Indica que un usuario tiene una única suscripción
    @JsonProperty("subscription")
    private Subscription subscription;

    //RELACION CON HISTORIAL
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<History> histories;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", name=" + name + ", lastname=" + lastname + ", email=" + email + ", membership=" + membership + ", username=" + username + ", password=" + password + '}';
    }
}
