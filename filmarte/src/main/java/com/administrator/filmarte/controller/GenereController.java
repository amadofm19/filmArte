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

import com.administrator.filmarte.model.Genre;
import com.administrator.filmarte.service.GenereService;

import io.swagger.v3.oas.annotations.Operation; // Importar la anotación Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Importar la anotación ApiResponse
import io.swagger.v3.oas.annotations.media.Content; // Importar la anotación Content
import io.swagger.v3.oas.annotations.media.Schema; // Importar la anotación Schema
import io.swagger.v3.oas.annotations.tags.Tag; // Importar la anotación Tag
import io.swagger.v3.oas.annotations.media.ArraySchema; // Importar la anotación ArraySchema

@RestController
@RequestMapping("/generes")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Genres", description = "Provides methods for managing movie genres") // Anotación Tag agregada
public class GenereController {

    @Autowired
    private GenereService service;

    // Obtener todos los géneros
    @Operation(summary = "Get all genres") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Found genres", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))) // Descripción
                                                                                                                                                                                               // de
                                                                                                                                                                                               // la
                                                                                                                                                                                               // respuesta
    @GetMapping
    public List<Genre> getAll() {
        return service.getAll();
    }

    // Obtener un género por su ID
    @Operation(summary = "Get a genre by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Found the genre", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Genre.class))) // Descripción
                                                                                                                                                                            // de
                                                                                                                                                                            // la
                                                                                                                                                                            // respuesta
    @ApiResponse(responseCode = "404", description = "Genre not found") // Descripción del error
    @GetMapping("{id}")
    public ResponseEntity<Genre> getById(@PathVariable Integer id) {
        Genre genere = service.getById(id);
        return new ResponseEntity<Genre>(genere, HttpStatus.OK);
    }

    // Registrar un nuevo género
    @Operation(summary = "Register a new genre") // Resumen de la operación
    @ApiResponse(responseCode = "201", description = "Genre registered successfully") // Descripción de la respuesta
    @PostMapping
    public ResponseEntity<String> register(@RequestBody Genre genere) {
        service.save(genere);
        return new ResponseEntity<>("Genero registered successfully", HttpStatus.CREATED);
    }

    // Actualizar un género por su ID
    @Operation(summary = "Update a genre by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Record updated successfully") // Descripción de la respuesta
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") // Descripción del error
    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Genre genere, @PathVariable Integer id) {
        try {
            Genre auxGenere = service.getById(id);
            genere.setId(auxGenere.getId());
            service.save(genere);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un género por su ID
    @Operation(summary = "Delete a genre by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Genre deleted successfully") // Descripción de la respuesta
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") // Descripción del error
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<>("Genero deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }
}
