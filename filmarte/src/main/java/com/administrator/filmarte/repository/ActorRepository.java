/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    List<Actor> findByFirstNameContainingIgnoreCase(String firstName);
    List<Actor> findByLastNameContainingIgnoreCase(String lastName);
    List<Actor> findByDescriptionContainingIgnoreCase(String description);

    @Query(value = "SELECT a FROM Actor a WHERE a.firstName = :firstName")
    List<Actor> getActorsByFirstName(@Param("firstName") String firstName);

    @Query(value = "SELECT * FROM actor WHERE last_name = :lastName", nativeQuery = true)
    List<Actor> getActorsByLastName(@Param("lastName") String lastName);

    @Query(value = "SELECT a FROM Actor a WHERE a.firstName = :firstName AND a.lastName = :lastName")
    List<Actor> getActorsByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = "SELECT a FROM Actor a WHERE a.firstName LIKE :letter%")
    List<Actor> getActorsByFirstNameStartingWith(@Param("letter") String letter);
}
