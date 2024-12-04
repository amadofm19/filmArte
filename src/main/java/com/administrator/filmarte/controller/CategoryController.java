/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.administrator.filmarte.model.Category;
import com.administrator.filmarte.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("categories")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
    RequestMethod.PUT})
@Tag(name = "Category", description = "Provides methods for managing categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Operation(summary = "Get all categories or categories with pagination")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        if (page >= 0 && pageSize > 0) {
            List<Category> categories = service.getAll(page, pageSize);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } else {
            List<Category> categories = service.getAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get a category by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)})
    @GetMapping("{idCategory}")
    public ResponseEntity<?> getById(@PathVariable Integer idCategory) {
        Category category = service.getById(idCategory);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @Operation(summary = "Register a category")
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Category category) {
        service.save(category);
        return new ResponseEntity<String>("Saved record", HttpStatus.OK);
    }

    @Operation(summary = "Update categories")
    @PutMapping("{idCategory}")
    public ResponseEntity<?> update(@Valid @RequestBody Category category, @PathVariable Integer idCategory) {
        Category auxCategory = service.getById(idCategory);
        category.setIdCategory(auxCategory.getIdCategory());
        service.save(category);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }

    @Operation(summary = "Delete category")
    @DeleteMapping("{idCategory}")
    public ResponseEntity<Void> delete(@PathVariable Integer idCategory) {
        service.delete(idCategory);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get categories by categoryType")
    @GetMapping("/search/type/{categoryType}")
    public ResponseEntity<?> getByCategoryType(@PathVariable String categoryType) {
        List<Category> categories = service.findByCategoryType(categoryType);
        if (categories.isEmpty()) {
            return new ResponseEntity<>("No categories found with type: " + categoryType, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Operation(summary = "Get categories by description")
    @GetMapping("/search/description/{description}")
    public ResponseEntity<?> getByDescription(@PathVariable String description) {
        List<Category> categories = service.findByDescription(description);
        if (categories.isEmpty()) {
            return new ResponseEntity<>("No categories found containing: " + description, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
