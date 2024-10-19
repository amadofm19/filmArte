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

import com.administrator.filmarte.model.Movie;
import com.administrator.filmarte.service.MovieService;

import io.swagger.v3.oas.annotations.Operation; // Importar la anotación Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Importar la anotación ApiResponse
import io.swagger.v3.oas.annotations.media.Content; // Importar la anotación Content
import io.swagger.v3.oas.annotations.media.Schema; // Importar la anotación Schema
import io.swagger.v3.oas.annotations.tags.Tag; // Importar la anotación Tag
import io.swagger.v3.oas.annotations.media.ArraySchema; // Importar la anotación ArraySchema

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Movies", description = "Provides methods for managing movies") // Anotación Tag agregada
public class MovieController {

    @Autowired
    private MovieService service;

    // Obtener todas las películas
    @Operation(summary = "Get all movies") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Found movies", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Movie.class)))) // Descripción
                                                                                                                                                                                               // de
                                                                                                                                                                                               // la
                                                                                                                                                                                               // respuesta
    @GetMapping
    public List<Movie> getAll() {
        return service.getAll();
    }

    // Obtener una película por su ID
    @Operation(summary = "Get a movie by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Found the movie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))) // Descripción
                                                                                                                                                                            // de
                                                                                                                                                                            // la
                                                                                                                                                                            // respuesta
    @ApiResponse(responseCode = "404", description = "Movie not found") // Descripción del error
    @GetMapping("{id}")
    public ResponseEntity<Movie> getById(@PathVariable Integer id) {
        Movie movie = service.getById(id);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    // Registrar una nueva película
    @Operation(summary = "Register a new movie") // Resumen de la operación
    @ApiResponse(responseCode = "201", description = "Movie registered successfully") // Descripción de la respuesta
    @PostMapping
    public ResponseEntity<String> register(@RequestBody Movie movie) {
        service.save(movie);
        return new ResponseEntity<>("Movie registered successfully", HttpStatus.CREATED);
    }

    // Actualizar una película por su ID
    @Operation(summary = "Update a movie by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Record updated successfully") // Descripción de la respuesta
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") // Descripción del error
    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Movie movie, @PathVariable Integer id) {
        try {
            Movie auxMovie = service.getById(id);
            movie.setIdMovie(auxMovie.getIdMovie());
            service.save(movie);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una película por su ID
    @Operation(summary = "Delete a movie by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Movie deleted successfully") // Descripción de la respuesta
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") // Descripción del error
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }
}
