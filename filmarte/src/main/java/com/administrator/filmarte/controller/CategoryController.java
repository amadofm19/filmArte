/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.administrator.filmarte.model.Category;
import com.administrator.filmarte.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

/**
 *
 * @author ARACELI
 */
@RestController
@RequestMapping("categories")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@Tag(name = "Category", description = "Provides methods for managing categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Operation(summary = "Get all categories")
    @ApiResponse(responseCode = "200", description = "Found categories", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class))) })
    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get all categories with pagination")
    @GetMapping(value = "/pagination", params = { "page", "size" })
    public List<Category> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        return service.getAll(page, pageSize);
    }

    @Operation(summary = "Get a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content) })
    @GetMapping("{idCategory}")
    public ResponseEntity<?> getById(@PathVariable Integer idCategory) {
        Category category = service.getById(idCategory);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @Operation(summary = "Register a category")
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Category category) {
        service.save(category);
        return new ResponseEntity<String>("Saved record", HttpStatus.OK);
    }

    @Operation(summary = "Update categories")
    @PutMapping("{idCategory}")
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable Integer idCategory) {
        Category auxCategory = service.getById(idCategory);
        category.setIdCategory(auxCategory.getIdCategory());
        service.save(category);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }

    @Operation(summary = "Delete category")
    @DeleteMapping("{idCategory}")
    public ResponseEntity<?> delete(@RequestBody Category category, @PathVariable Integer idCategory) {
        service.delete(idCategory);
        return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
    }
}