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
import com.administrator.filmarte.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByCategoryType(String categoryType);
    List<Category> findByDescriptionContaining(String description);

    @Query(value = "SELECT * FROM category WHERE idCategory = :idCategory", nativeQuery = true)
    Category getCategoryById(@Param("idCategory") int idCategory);

    @Query(value = "SELECT c FROM Category c WHERE c.idCategory = :idCategory")
    Category getCategoryByIdJPQL(@Param("idCategory") int idCategory);

    @Query(value = "SELECT * FROM category WHERE categoryType = :categoryType", nativeQuery = true)
    List<Category> getCategoriesByCategoryType(@Param("categoryType") String categoryType);

    @Query(value = "SELECT c FROM Category c WHERE c.categoryType = :categoryType")
    List<Category> getCategoriesByCategoryTypeJPQL(@Param("categoryType") String categoryType);

    @Query(value = "SELECT * FROM category WHERE description = :description", nativeQuery = true)
    List<Category> getCategoriesByDescription(@Param("description") String description);

    @Query(value = "SELECT c FROM Category c WHERE c.description = :description")
    List<Category> getCategoriesByDescriptionJPQL(@Param("description") String description);

    @Query(value = "SELECT * FROM category WHERE categoryType LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Category> getCategoriesByCategoryTypeStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM category WHERE description LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Category> getCategoriesByDescriptionStartingWith(@Param("letter") String letter);

}