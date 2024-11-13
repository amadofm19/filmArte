package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByMembership(String membership);


    // BY ID
    @Query(value = "SELECT * FROM user WHERE idUsers = :idUsers", nativeQuery = true)
    User getUserById(@Param("idUsers") int idUsers);

    // @Query(value = "SELECT u FROM User u WHERE u.idUser = :idUser")
    // User getUserByIdJPQL(@Param("idUser") int idUser);

    // BY NAME
    @Query(value = "SELECT * FROM user WHERE name = :name", nativeQuery = true)
    List<User> getUsersByName(@Param("name") String name);

    @Query(value = "SELECT u FROM User u WHERE u.name = :name")
    List<User> getUsersByNameJPQL(@Param("name") String name);

    // BY LASTNAME
    @Query(value = "SELECT * FROM user WHERE lastname = :lastname", nativeQuery = true)
    List<User> getUsersByLastName(@Param("lastname") String lastname);

    @Query(value = "SELECT u FROM User u WHERE u.lastname = :lastname")
    List<User> getUsersByLastNameJPQL(@Param("lastname") String lastname);

    // BY EMAIL
    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    List<User> getUsersByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    List<User> getUsersByEmailJPQL(@Param("email") String email);

    // BY MEMBERSHIP
    @Query(value = "SELECT * FROM user WHERE membership = :membership", nativeQuery = true)
    List<User> getUsersByMembership(@Param("membership") String membership);

    @Query(value = "SELECT u FROM User u WHERE u.membership = :membership")
    List<User> getUsersByMembershipJPQL(@Param("membership") String membership);

    // BY USERNAME
    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    List<User> getUsersByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM User u WHERE u.username = :username")
    List<User> getUsersByUsernameJPQL(@Param("username") String username);

    // BY PASSWORD
    @Query(value = "SELECT u FROM User u WHERE u.password = :password")
    List<User> getUsersByPassword(@Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.password = :password")
    List<User> getUsersByPasswordJPQL(@Param("password") String password);

    // BY NAME STARTING WITH
    @Query(value = "SELECT * FROM user WHERE name LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<User> getUsersByNameStartingWith(@Param("letter") String letter);

    // BY LASTNAME STARTING WITH
    @Query(value = "SELECT * FROM user WHERE lastname LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<User> getUsersByLastNameStartingWith(@Param("letter") String letter);

}
