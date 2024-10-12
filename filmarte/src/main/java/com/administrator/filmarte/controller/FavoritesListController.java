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

import io.swagger.v3.oas.annotations.Operation; // Importar la anotación Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Importar la anotación ApiResponse
import io.swagger.v3.oas.annotations.media.Content; // Importar la anotación Content
import io.swagger.v3.oas.annotations.media.Schema; // Importar la anotación Schema
import io.swagger.v3.oas.annotations.tags.Tag; // Importar la anotación Tag
import io.swagger.v3.oas.annotations.media.ArraySchema; // Importar la anotación ArraySchema

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Favorites Lists", description = "Provides methods for managing favorites lists") // Anotación Tag agregada
public class FavoritesListController {

    @Autowired
    private FavoritesListService service;

    // Obtener todas las listas de favoritos
    @Operation(summary = "Get all favorites lists") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Found favorites lists", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FavoritesListService.class)))) // Descripción
                                                                                                                                                                                                                // de
                                                                                                                                                                                                                // la
                                                                                                                                                                                                                // respuesta
    @GetMapping
    public List<FavoritesList> getAll() {
        return service.getAll();
    }

    // Obtener una lista de favoritos por su ID
    @Operation(summary = "Get a favorites list by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Found the favorites list", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FavoritesListService.class))) // Descripción
                                                                                                                                                                                             // de
                                                                                                                                                                                             // la
                                                                                                                                                                                             // respuesta
    @ApiResponse(responseCode = "404", description = "Favorites list not found") // Descripción del error
    @GetMapping("{id}")
    public ResponseEntity<FavoritesList> getById(@PathVariable Integer id) {
        FavoritesList favoritesList = service.getById(id);
        return new ResponseEntity<FavoritesList>(favoritesList, HttpStatus.OK);
    }

    // Registrar una nueva lista de favoritos
    @Operation(summary = "Register a new favorites list") // Resumen de la operación
    @ApiResponse(responseCode = "201", description = "Favorites list registered successfully") // Descripción de la
                                                                                               // respuesta
    @PostMapping
    public ResponseEntity<String> register(@RequestBody FavoritesList favoritesList) {
        service.save(favoritesList);
        return new ResponseEntity<>("Favorites list registered successfully", HttpStatus.CREATED);
    }

    // Actualizar una lista de favoritos por su ID
    @Operation(summary = "Update a favorites list by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Record updated successfully") // Descripción de la respuesta
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") // Descripción del error
    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody FavoritesList favoritesList, @PathVariable Integer id) {
        try {
            FavoritesList auxFavoritesList = service.getById(id);
            favoritesList.setId(auxFavoritesList.getId());
            service.save(favoritesList);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una lista de favoritos por su ID
    @Operation(summary = "Delete a favorites list by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Favorites list deleted successfully") // Descripción de la
                                                                                            // respuesta
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") // Descripción del error
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<>("Favorites list deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }
}
