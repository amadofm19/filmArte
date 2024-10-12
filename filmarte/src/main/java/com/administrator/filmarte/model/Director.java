package com.administrator.filmarte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastNameFather;
    private String lastNameMother;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNameFather() {
        return lastNameFather;
    }

    public void setLastNameFather(String lastNameFather) {
        this.lastNameFather = lastNameFather;
    }

    public String getLastNameMother() {
        return lastNameMother;
    }

    public void setLastNameMother(String lastNameMother) {
        this.lastNameMother = lastNameMother;
    }

    // toString method
    @Override
    public String toString() {
        return id + " :: " + firstName + " :: " + lastNameFather + " :: " + lastNameMother;
    }
}

