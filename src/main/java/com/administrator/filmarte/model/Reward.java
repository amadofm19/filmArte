package com.administrator.filmarte.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Entity representing a reward in the system.")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the reward.", example = "1", required = true)
    @Column(name = "idReward")
    @JsonProperty("idReward")
    private int idReward;

    @Schema(description = "Name of the reward.", example = "Best Film", required = true)
    @NotBlank(message = "The name must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 50, message = "The name must be at most 50 characters, and has at least one character")
    @Column(name = "nameReward")
    @JsonProperty("nameReward")
    private String nameReward;

    @Schema(description = "Delivery date of the reward.", example = "2023-01-01", required = true)
    @NotNull(message = "The delivery date must not be null")
    @Column(name = "deliveryDate")
    @JsonProperty("deliveryDate")
    private Date deliveryDate;

    @Schema(description = "Nomination associated with the reward.", example = "Best Director", required = true)
    @NotBlank(message = "The nomination must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 50, message = "The nomination must be at most 50 characters, and has at least one character")
    @Column(name = "nomination")
    @JsonProperty("nomination")
    private String nomination;

    @ManyToOne
    @JoinColumn(name = "idMovie", nullable = false)
    @JsonIgnore
    private Movie movie;

    public Reward(Date deliveryDate, int idReward, Movie movie, String nameReward, String nomination) {
        this.deliveryDate = deliveryDate;
        this.idReward = idReward;
        this.movie = movie;
        this.nameReward = nameReward;
        this.nomination = nomination;
    }

    public Reward() {

    }

    public int getIdReward() {
        return idReward;
    }

    public void setIdReward(int idReward) {
        this.idReward = idReward;
    }

    public String getNameReward() {
        return nameReward;
    }

    public void setNameReward(String nameReward) {
        this.nameReward = nameReward;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return idReward + " :: " + nameReward + " :: " + deliveryDate + " :: " + nomination;
    }
}
