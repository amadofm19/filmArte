package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Genere;

@Repository
public interface GenereRepository extends JpaRepository<Genere, Integer> {

    @Query(value = "SELECT * FROM genere WHERE idGenere = :idGenere", nativeQuery = true)
    Genere getGenereById(@Param("idGenere") int idGenere);

    @Query(value = "SELECT g FROM Genere g WHERE g.idGenere = :idGenere")
    Genere getGenereByIdJPQL(@Param("idGenere") int idGenere);

    @Query(value = "SELECT * FROM genere WHERE name = :name", nativeQuery = true)
    List<Genere> getGeneresByName(@Param("name") String name);

    @Query(value = "SELECT g FROM Genere g WHERE g.name = :name")
    List<Genere> getGeneresByNameJPQL(@Param("name") String name);

    @Query(value = "SELECT * FROM genere WHERE description = :description", nativeQuery = true)
    List<Genere> getGeneresByDescription(@Param("description") String description);

    @Query(value = "SELECT g FROM Genere g WHERE g.description = :description")
    List<Genere> getGeneresByDescriptionJPQL(@Param("description") String description);

    @Query(value = "SELECT * FROM genere WHERE name LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Genere> getGeneresByNameStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM genere WHERE description LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Genere> getGeneresByDescriptionStartingWith(@Param("letter") String letter);
}
