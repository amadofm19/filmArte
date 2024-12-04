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

import com.administrator.filmarte.model.Movie;
import com.administrator.filmarte.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Movies", description = "Provides methods for managing movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @Operation(summary = "Get all movies or movies with pagination")
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        if (page >= 0 && pageSize > 0) {
            List<Movie> movies = service.getAll(page, pageSize);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            List<Movie> movies = service.getAll();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
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
    public ResponseEntity<String> register(@Valid @RequestBody Movie movie) {
        service.save(movie);
        return new ResponseEntity<>("Movie registered successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Update a movie by ID")
    @ApiResponse(responseCode = "200", description = "Record updated successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @PutMapping("{idMovie}")
    public ResponseEntity<String> update(@Valid @RequestBody Movie movie, @PathVariable Integer idMovie) {
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

    @Operation(summary = "Get movies by title")
    @GetMapping("/search/title")
    public ResponseEntity<?> searchByTitle(@RequestParam String title) {
        List<Movie> movies = service.findByTitle(title);
        if (movies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron películas con el título: " + title);
        }
        return ResponseEntity.ok(movies);
    }

    @Operation(summary = "Get movies by year")
    @GetMapping("/search/year")
    public ResponseEntity<?> searchByYear(@RequestParam int year) {
        List<Movie> movies = service.findByYear(year);
        if (movies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No movies found for the year: " + year);
        }
        return ResponseEntity.ok(movies);
    }

    @Operation(summary = "Get movies by title and year")
    @GetMapping("/search/title-year")
    public ResponseEntity<?> searchByTitleAndYear(@RequestParam String title, @RequestParam int year) {
        List<Movie> movies = service.findByTitleAndYear(title, year);
        if (movies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No movies found with the title: " + title + " and the year: " + year);
        }
        return ResponseEntity.ok(movies);
    }

}
