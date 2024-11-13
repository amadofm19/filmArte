package com.administrator.filmarte.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Entity representing a user in the system.")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the user.", example = "1", required = true)
    @Column(name = "idUsers")
    @JsonProperty("idUsers")
    private int idUsers;

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

    @OneToOne
    @JoinColumn(name = "idSubscription", referencedColumnName = "idSubscription")
    @JsonProperty("subscription")
    private Subscription subscription;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("favoritesList")
    private List<FavoritesList> favoritesList;

    // @OneToOne
    // @JoinColumn(name = "idFavoritesList", referencedColumnName =
    // "idFavoritesList")
    // @JsonProperty("favoritesList")
    // private FavoritesList favoritesList;

    @OneToOne
    @JoinColumn(name = "idHistory", referencedColumnName = "idHistory")
    @JsonProperty("history")
    private History history;

    // Relación uno a muchos con Comment
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("comments")
    private List<Comment> comments;


    // Getters y setters

    public User(List<Comment> comments, String email, List<FavoritesList> favoritesList, History history, int idUsers, String lastname, String membership, String name, String password, Subscription subscription, String username) {
        this.comments = comments;
        this.email = email;
        this.favoritesList = favoritesList;
        this.history = history;
        this.idUsers = idUsers;
        this.lastname = lastname;
        this.membership = membership;
        this.name = name;
        this.password = password;
        this.subscription = subscription;
        this.username = username;
    }

    public User() {
 
    }




    public int getIdUser() {
        return idUsers;
    }

    public void setIdUser(int idUsers) {
        this.idUsers = idUsers;
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

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    /*
     * public FavoritesList getFavoritesList() {
     * return (FavoritesList) favoritesList;
     * }
     * 
     * public void setFavoritesList(FavoritesList favoritesList) {
     * this.favoritesList = favoritesList;
     * }
     */

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUsers +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", membership='" + membership + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", subscription=" + subscription +
                ", favoritesList=" + favoritesList +
                ", history=" + history +
                ", comments=" + comments +
                '}';
    }
}
