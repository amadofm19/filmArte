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
import com.administrator.filmarte.model.Genere;
import com.administrator.filmarte.service.GenereService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/generes")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Generes", description = "Provides methods for managing movie generes")
public class GenereController {

    @Autowired
    private GenereService service;

    @Operation(summary = "Get all genres or genres with pagination")
    @GetMapping
    public ResponseEntity<List<Genere>> getAllGenres(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        if (page >= 0 && pageSize > 0) {
            List<Genere> genres = service.getAll(page, pageSize);
            return new ResponseEntity<>(genres, HttpStatus.OK);
        } else {
            List<Genere> genres = service.getAll();
            return new ResponseEntity<>(genres, HttpStatus.OK);
        }
    }
    

    @Operation(summary = "Get a genere by ID")
    @ApiResponse(responseCode = "200", description = "Found the genere", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Genere.class)))
    @ApiResponse(responseCode = "404", description = "Genere not found")
    @GetMapping("{idGenere}")
    public ResponseEntity<Genere> getById(@PathVariable Integer idGenere) {
        Genere genere = service.getById(idGenere);
        return new ResponseEntity<>(genere, HttpStatus.OK);
    }

    @Operation(summary = "Register a new genere")
    @ApiResponse(responseCode = "201", description = "Genere registered successfully")
    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody Genere genere) {
        service.save(genere);
        return new ResponseEntity<>("Genere registered successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Update a genere by ID")
    @ApiResponse(responseCode = "200", description = "Record updated successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @PutMapping("{idGenere}")
    public ResponseEntity<String> update(@Valid @RequestBody Genere genere, @PathVariable Integer idGenere) {
        try {
            Genere auxGenere = service.getById(idGenere);
            genere.setIdGenere(auxGenere.getIdGenere());
            service.save(genere);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a genere by ID")
    @ApiResponse(responseCode = "200", description = "Genere deleted successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @DeleteMapping("{idGenere}")
    public ResponseEntity<String> delete(@PathVariable Integer idGenere) {
        try {
            service.delete(idGenere);
            return new ResponseEntity<>("Genere deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }
}
