package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    List<Administrator> findByName(String name);
    List<Administrator> findByLastname(String lastname);
    List<Administrator> findByPassword(String password);

    @Query(value = "SELECT * FROM administrator WHERE idAdministrator = :idAdministrator", nativeQuery = true)
    Administrator getAdministratorById(@Param("idAdministrator") int idAdministrator);

    @Query(value = "SELECT a FROM Administrator a WHERE a.idAdministrator = :idAdministrator")
    Administrator getAdministratorByIdJPQL(@Param("idAdministrator") int idAdministrator);

    @Query(value = "SELECT * FROM administrator WHERE name = :name", nativeQuery = true)
    List<Administrator> getAdministratorsByName(@Param("name") String name);

    @Query(value = "SELECT a FROM Administrator a WHERE a.name = :name")
    List<Administrator> getAdministratorsByNameJPQL(@Param("name") String name);

    @Query(value = "SELECT * FROM administrator WHERE lastname = :lastname", nativeQuery = true)
    List<Administrator> getAdministratorsByLastName(@Param("lastname") String lastname);

    @Query(value = "SELECT a FROM Administrator a WHERE a.lastname = :lastname")
    List<Administrator> getAdministratorsByLastNameJPQL(@Param("lastname") String lastname);

    @Query(value = "SELECT * FROM administrator WHERE email = :email", nativeQuery = true)
    List<Administrator> getAdministratorsByEmail(@Param("email") String email);

    @Query(value = "SELECT a FROM Administrator a WHERE a.email = :email")
    List<Administrator> getAdministratorsByEmailJPQL(@Param("email") String email);

    @Query(value = "SELECT * FROM administrator WHERE name LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Administrator> getAdministratorsByNameStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM administrator WHERE lastname LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Administrator> getAdministratorsByLastNameStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM administrator WHERE email LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Administrator> getAdministratorsByEmailStartingWith(@Param("letter") String letter);
}
