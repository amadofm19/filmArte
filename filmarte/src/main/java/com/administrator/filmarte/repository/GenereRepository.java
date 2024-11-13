package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Genre;

@Repository
public interface GenereRepository extends JpaRepository<Genre, Integer> {
    // List<Genre> findByName(String name); 
    // List<Genre> findByDescription(String description);



    // BY ID
    @Query(value = "SELECT * FROM genre WHERE idGenre = :idGenre", nativeQuery = true)
    Genre getGenreById(@Param("idGenre") int idGenre);

    @Query(value = "SELECT g FROM Genre g WHERE g.idGenre = :idGenre")
    Genre getGenreByIdJPQL(@Param("idGenre") int idGenre);

    // BY NAME
    @Query(value = "SELECT * FROM genre WHERE name = :name", nativeQuery = true)
    List<Genre> getGenresByName(@Param("name") String name);

    @Query(value = "SELECT g FROM Genre g WHERE g.name = :name")
    List<Genre> getGenresByNameJPQL(@Param("name") String name);

    // BY DESCRIPTION
    @Query(value = "SELECT * FROM genre WHERE description = :description", nativeQuery = true)
    List<Genre> getGenresByDescription(@Param("description") String description);

    @Query(value = "SELECT g FROM Genre g WHERE g.description = :description")
    List<Genre> getGenresByDescriptionJPQL(@Param("description") String description);

    // BY LETTER NAME STARTING WITH
    @Query(value = "SELECT * FROM genre WHERE name LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Genre> getGenresByNameStartingWith(@Param("letter") String letter);

    // BY LETTER DESCRIPTION STARTING WITH
    @Query(value = "SELECT * FROM genre WHERE description LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Genre> getGenresByDescriptionStartingWith(@Param("letter") String letter);

}
