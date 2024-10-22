/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author ARACELI
 */
    @Entity
@Table(name = "movie_genre")
public class Movie_Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Movie_Genre{" + "id=" + id + ", movie=" + ", genre=" + '}';
    }
    
    
}
    

