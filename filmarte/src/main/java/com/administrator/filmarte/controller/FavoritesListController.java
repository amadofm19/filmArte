package com.administrator.filmarte.controller;

import java.util.List;
import java.util.NoSuchElementException;
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
import org.springframework.web.bind.annotation.RestController;

import com.administrator.filmarte.model.FavoritesList;
import com.administrator.filmarte.service.FavoritesListService;

import io.swagger.v3.oas.annotations.Operation; 
import io.swagger.v3.oas.annotations.responses.ApiResponse; 
import io.swagger.v3.oas.annotations.media.Content; 
import io.swagger.v3.oas.annotations.media.Schema; 
import io.swagger.v3.oas.annotations.tags.Tag; 
import io.swagger.v3.oas.annotations.media.ArraySchema; 

@RestController
@RequestMapping("favorites")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@Tag(name = "Favorites Lists", description = "Provides methods for managing favorites lists") 
public class FavoritesListController {
    @Autowired
    private FavoritesListService service;

    @Operation(summary = "Get all favorites lists") 
    @ApiResponse(responseCode = "200", description = "Found favorites lists", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FavoritesListService.class)))) // Descripción de la respuesta
    @GetMapping
    public List<FavoritesList> getAll() {
        return service.getAll();
    }


    @Operation(summary = "Get a favorites list by ID") 
    @ApiResponse(responseCode = "200", description = "Found the favorites list", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FavoritesListService.class))) // Descripción de la respuesta
    @ApiResponse(responseCode = "404", description = "Favorites list not found") 
    @GetMapping("{idFavoritesList}")
    public ResponseEntity<FavoritesList> getById(@PathVariable Integer idFavoritesList) {
        FavoritesList favoritesList = service.getById(idFavoritesList);
        return new ResponseEntity<FavoritesList>(favoritesList, HttpStatus.OK);
    }


    @Operation(summary = "Register a new favorites list") 
    @ApiResponse(responseCode = "201", description = "Favorites list registered successfully") 
    @PostMapping
    public ResponseEntity<String> register(@RequestBody FavoritesList favoritesList) {
        service.save(favoritesList);
        return new ResponseEntity<>("Favorites list registered successfully", HttpStatus.CREATED);
    }


    @Operation(summary = "Update a favorites list by ID") 
    @ApiResponse(responseCode = "200", description = "Record updated successfully") 
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @PutMapping("{idFavoritesList}")
    public ResponseEntity<String> update(@RequestBody FavoritesList favoritesList, @PathVariable Integer idFavoritesList) {
        try {
            FavoritesList auxFavoritesList = service.getById(idFavoritesList);
            favoritesList.setId(auxFavoritesList.getId());
            service.save(favoritesList);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a favorites list by ID") 
    @ApiResponse(responseCode = "200", description = "Favorites list deleted successfully") 
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") 
    @DeleteMapping("{idFavoritesList}")
    public ResponseEntity<String> delete(@PathVariable Integer idFavoritesList) {
        try {
            service.delete(idFavoritesList);
            return new ResponseEntity<>("Favorites list deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }
}
