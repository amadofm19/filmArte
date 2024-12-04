package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
    List<Director> findByFirstName(String firstName);
    List<Director> findByLastNameFather(String lastNameFather);
    List<Director> findByLastNameMother(String lastNameMother);

    @Query(value = "SELECT * FROM director WHERE idDirector = :idDirector", nativeQuery = true)
    Director getDirectorById(@Param("idDirector") int idDirector);

    @Query(value = "SELECT d FROM Director d WHERE d.idDirector = :idDirector")
    Director getDirectorByIdJPQL(@Param("idDirector") int idDirector);

    @Query(value = "SELECT * FROM director WHERE firstName = :firstName", nativeQuery = true)
    List<Director> getDirectorsByFirstName(@Param("firstName") String firstName);

    @Query(value = "SELECT d FROM Director d WHERE d.firstName = :firstName")
    List<Director> getDirectorsByFirstNameJPQL(@Param("firstName") String firstName);

    @Query(value = "SELECT * FROM director WHERE lastNameFather = :lastNameFather", nativeQuery = true)
    List<Director> getDirectorsByLastNameFather(@Param("lastNameFather") String lastNameFather);

    @Query(value = "SELECT d FROM Director d WHERE d.lastNameFather = :lastNameFather")
    List<Director> getDirectorsByLastNameFatherJPQL(@Param("lastNameFather") String lastNameFather);

    @Query(value = "SELECT * FROM director WHERE lastNameMother = :lastNameMother", nativeQuery = true)
    List<Director> getDirectorsByLastNameMother(@Param("lastNameMother") String lastNameMother);

    @Query(value = "SELECT d FROM Director d WHERE d.lastNameMother = :lastNameMother")
    List<Director> getDirectorsByLastNameMotherJPQL(@Param("lastNameMother") String lastNameMother);

    @Query(value = "SELECT * FROM director WHERE firstName LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Director> getDirectorsByFirstNameStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM director WHERE lastNameFather LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Director> getDirectorsByLastNameFatherStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM director WHERE lastNameMother LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Director> getDirectorsByLastNameMotherStartingWith(@Param("letter") String letter);

}
