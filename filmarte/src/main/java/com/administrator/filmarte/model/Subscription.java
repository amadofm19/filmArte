/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 *
 * @author ARACELI
 */
@Entity
@Schema(description = "Entity representing a subscription in the system.")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the subscription.", example = "1", required = true)
    @Column(name = "idSubscription")
    @JsonProperty("idSubscription")
    private int idSubscription;

    @Schema(description = "Type of membership associated with the subscription.", example = "Premium", required = true)
    @NotBlank(message = "Membership type is mandatory")
    @Size(min = 1, max = 50, message = "Membership type must be between 1 and 50 characters")
    @Column(name = "membershipType")
    @JsonProperty("membershipType")
    private String membershipType;

    @Schema(description = "Cost of the subscription.", example = "29.99", required = true)
    @Positive(message = "Cost must be positive")
    @Column(name = "cost")
    @JsonProperty("cost")
    private double cost;

    @Schema(description = "Duration of the subscription in months.", example = "12", required = true)
    @Positive(message = "Duration must be positive")
    @Column(name = "duration")
    @JsonProperty("duration")
    private int duration;

    @Schema(description = "Payment method used for the subscription.", example = "Credit Card", required = true)
    @NotBlank(message = "Payment method is mandatory")
    @Size(min = 1, max = 50, message = "Payment method must be between 1 and 50 characters")
    @Column(name = "paymentMethod")
    @JsonProperty("paymentMethod")
    private String paymentMethod;

    @Schema(description = "Start date of the subscription.", example = "2023-01-01")
    @Column(name = "startDate")
    @JsonProperty("startDate")
    private Date startDate;

    @Schema(description = "Renewal date of the subscription.", example = "2024-01-01")
    @Column(name = "renewalDate")
    @JsonProperty("renewalDate")
    private Date renewalDate;

    // RELACION CON USUARIO
    @OneToOne
    @JoinColumn(name = "idUser", nullable = false)
    @JsonProperty("user")
    private User user;

    // RELACION CON PAGO
    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty("payments")
    private List<Pay> payments;

    public int getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    @Override
    public String toString() {
        return "Subscription{" + "idSubscription=" + idSubscription + ", membershipType=" + membershipType + ", cost="
                + cost + ", duration=" + duration + ", paymentMethod=" + paymentMethod + ", startDate=" + startDate
                + ", renewalDate=" + renewalDate + '}';
    }
}
