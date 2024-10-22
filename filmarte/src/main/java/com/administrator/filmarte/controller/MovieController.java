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

import io.swagger.v3.oas.annotations.Operation; 
import io.swagger.v3.oas.annotations.responses.ApiResponse; 
import io.swagger.v3.oas.annotations.media.Content; 
import io.swagger.v3.oas.annotations.media.Schema; 
import io.swagger.v3.oas.annotations.tags.Tag; 
import io.swagger.v3.oas.annotations.media.ArraySchema; 

@RestController
@RequestMapping("movies")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@Tag(name = "Movies", description = "Provides methods for managing movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @Operation(summary = "Get all movies")
    @ApiResponse(responseCode = "200", description = "Found movies", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Movie.class))))
    @GetMapping
    public List<Movie> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get a movie by ID")
    @ApiResponse(responseCode = "200", description = "Found the movie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class)))
    @ApiResponse(responseCode = "404", description = "Movie not found")
    @GetMapping("{idMovie}")
    public ResponseEntity<Movie> getById(@PathVariable Integer idMovie) {
        Movie movie = service.getById(idMovie);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @Operation(summary = "Register a new movie")
    @ApiResponse(responseCode = "201", description = "Movie registered successfully")
    @PostMapping
    public ResponseEntity<String> register(@RequestBody Movie movie) {
        service.save(movie);
        return new ResponseEntity<>("Movie registered successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Update a movie by ID")
    @ApiResponse(responseCode = "200", description = "Record updated successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @PutMapping("{idMovie}")
    public ResponseEntity<String> update(@RequestBody Movie movie, @PathVariable Integer idMovie) {
        try {
            Movie auxMovie = service.getById(idMovie);
            movie.setIdMovie(auxMovie.getIdMovie());
            service.save(movie);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a movie by ID")
    @ApiResponse(responseCode = "200", description = "Movie deleted successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @DeleteMapping("{idMovie}")
    public ResponseEntity<String> delete(@PathVariable Integer idMovie) {
        try {
            service.delete(idMovie);
            return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }
}
