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
@Schema(description = "Entity representing a payment in the system.")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the payment.", example = "1", required = true)
    @JsonProperty("idPay")
    private int idPay;

    @Schema(description = "Amount of the payment.", example = "99.99", required = true)
    @NotNull(message = "The amount must not be null")
    @Column(name = "amount")
    @JsonProperty("amount")
    private float amount;

    @Schema(description = "Currency of the payment.", example = "USD", required = true)
    @NotBlank(message = "The currency must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 3, message = "The currency must be at most 3 characters, and has at least one character")
    @Column(name = "currency")
    @JsonProperty("currency")
    private String currency;

    @Schema(description = "Date of the payment.", example = "2023-01-01", required = true)
    @NotNull(message = "The payment date must not be null")
    @Column(name = "paymentDay")
    @JsonProperty("paymentDay")
    private Date paymentDay;

    @Schema(description = "Method of the payment.", example = "Credit Card", required = true)
    @NotBlank(message = "The payment method must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 20, message = "The payment method must be at most 20 characters, and has at least one character")
    @Column(name = "paymentMethod")
    @JsonProperty("paymentMethod")
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "idSubscription", nullable = false)
    @JsonIgnore
    @JsonProperty("subscription")
    private Subscription subscription;

    public Pay(float amount, String currency, int idPay, Date paymentDay, String paymentMethod, Subscription subscription) {
        this.amount = amount;
        this.currency = currency;
        this.idPay = idPay;
        this.paymentDay = paymentDay;
        this.paymentMethod = paymentMethod;
        this.subscription = subscription;
    }

    public Pay() {

    }

    public int getIdPay() {
        return idPay;
    }

    public void setIdPay(int idPay) {
        this.idPay = idPay;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getPaymentDay() { 
        return paymentDay;
    }

    public void setPaymentDay(Date paymentDay) { 
        this.paymentDay = paymentDay;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return idPay + " :: " + amount + " :: " + currency + " :: " + paymentDay + " :: " + paymentMethod;
    }
}
