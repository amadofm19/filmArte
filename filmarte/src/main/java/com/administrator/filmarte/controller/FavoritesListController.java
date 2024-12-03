package com.administrator.filmarte.controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.administrator.filmarte.model.FavoritesList;
import com.administrator.filmarte.service.FavoritesListService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@Validated
@RestController
@RequestMapping("favorites")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Favorites Lists", description = "Provides methods for managing favorites lists")
public class FavoritesListController {

    @Autowired
    private FavoritesListService service;

    @Operation(summary = "Get all favorites list or favorites list with pagination")
    @GetMapping
    public ResponseEntity<List<FavoritesList>> getAllFavorites(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        if (page >= 0 && pageSize > 0) {
            List<FavoritesList> favorites = service.getAll(page, pageSize);
            return new ResponseEntity<>(favorites, HttpStatus.OK);
        } else {
            List<FavoritesList> favorites = service.getAll();
            return new ResponseEntity<>(favorites, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get a favorites list by ID")
    @ApiResponse(responseCode = "200", description = "Found the favorites list", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FavoritesList.class))) // Descripción

    @ApiResponse(responseCode = "404", description = "Favorites list not found")
    @GetMapping("{id}")
    public ResponseEntity<FavoritesList> getById(@PathVariable Integer id) {
        FavoritesList favoritesList = service.getById(id);
        return new ResponseEntity<FavoritesList>(favoritesList, HttpStatus.OK);
    }

    @Operation(summary = "Register a new favorites list")
    @ApiResponse(responseCode = "201", description = "Favorites list registered successfully")

    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody FavoritesList favoritesList) {
        service.save(favoritesList);
        return new ResponseEntity<>("Favorites list registered successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Update a favorites list by ID")
    @ApiResponse(responseCode = "200", description = "Record updated successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @PutMapping("{id}")
    public ResponseEntity<String> update(@Valid @RequestBody FavoritesList favoritesList, @PathVariable Integer id) {
        try {
            FavoritesList auxFavoritesList = service.getById(id);
            favoritesList.setIdFavoritesList(auxFavoritesList.getIdFavoritesList());
            service.save(favoritesList);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a favorites list by ID")
    @ApiResponse(responseCode = "200", description = "Favorites list deleted successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<>("Favorites list deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get favorites lists by movie title")
    @ApiResponse(responseCode = "200", description = "Found favorites lists by title", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FavoritesList.class))))
    @GetMapping("/search/title")
    public List<FavoritesList> getByTitle(@RequestParam String title) {
        return service.findByTitle(title);
    }

    @Operation(summary = "Get favorites lists by genere")
    @ApiResponse(responseCode = "200", description = "Found favorites lists by genere", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FavoritesList.class))))
    @GetMapping("/search/genere")
    public List<FavoritesList> getByGenere(@RequestParam String genere) {
        return service.findByGenere(genere);
    }

    @Operation(summary = "Get favorites lists by viewing status")
    @ApiResponse(responseCode = "200", description = "Found favorites lists by viewing status", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FavoritesList.class))))
    @GetMapping("/search/viewing-status")
    public List<FavoritesList> getByViewingStatus(@RequestParam String status) {
        return service.findByViewingStatus(status);
    }

}
