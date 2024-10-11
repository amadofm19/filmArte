package com.administrator.filmarte.model;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entity representing a reward in the system.")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the reward.", example = "1", required = true)
    private int idReward;

    @Schema(description = "Name of the reward.", example = "Best Film", required = true)
    private String nameReward;

    @Schema(description = "Delivery date of the reward.", example = "2023-01-01", required = true)
    private Date deliveryDate;

    @Schema(description = "Nomination associated with the reward.", example = "Best Director", required = true)
    private String nomination;
    
    public int getIdReward(){
        return idReward;
    }
    public void setIdReward(int idReward){
        this.idReward = idReward;
    }
    public String getNameReward(){
        return nameReward;
    }
    public void setNameReward(String nameReward){
        this.nameReward = nameReward;
    }
    public Date getDeliveryDate(){
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate){
        this.deliveryDate = deliveryDate;
    }
    public String getNomination(){
        return nomination;
    }
    public void setNomination(String nomination){
        this.nomination = nomination;
    }
}
