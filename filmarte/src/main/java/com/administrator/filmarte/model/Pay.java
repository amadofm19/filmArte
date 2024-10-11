package com.administrator.filmarte.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entity representing a payment in the system.")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the payment.", example = "1", required = true)
    private int idPay;

    @Schema(description = "Amount of the payment.", example = "99.99", required = true)
    private float amount;

    @Schema(description = "Currency of the payment.", example = "USD", required = true)
    private String currency;

    @Schema(description = "Date of the payment.", example = "2023-01-01", required = true)
    private Date paymentDate;

    @Schema(description = "Method of the payment.", example = "Credit Card", required = true)
    private String paymentMethod;

    public int getIdPay() {
        return idPay;
    }
    public void setIdPay(int idPay) {
        this.idPay = idPay;
    }
    public float getAmount(){
        return amount;
    }
    public void setAmount(float amount){
        this.amount = amount;
    }
    public String getCurrency(){
        return currency;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    public Date getPaymentDate(){
        return paymentDate;
    }
    public void setPaymentDate(Date paymentDate){
        this.paymentDate = paymentDate;
    }
    public String getPaymentMethod(){
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
    public int getIdReward() {
        throw new UnsupportedOperationException("Unimplemented method 'getIdReward'");
    }
}
