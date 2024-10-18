package com.administrator.filmarte.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDirector;
    private String firstName;
    private String lastNameFather;
    private String lastNameMother;

    // RELACIONES
    // RRELACION CON PELICULA
    @OneToMany(mappedBy = "director")
    private Set<Movie> movies = new HashSet<>();

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
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

    @Override
    public String toString() {
        return "Director{" + "idDirector=" + idDirector + ", firstName=" + firstName + ", lastNameFather="
                + lastNameFather + ", lastNameMother=" + lastNameMother + ", movies=" + movies + '}';
    }

}
