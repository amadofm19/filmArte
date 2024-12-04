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

import com.administrator.filmarte.model.Director;
import com.administrator.filmarte.service.DirectorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/directors")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
    RequestMethod.PUT})
@Tag(name = "Directors", description = "Provides methods for managing directors")
public class DirectorController {

    @Autowired
    private DirectorService service;

    @Operation(summary = "Get all directors or directors with pagination")
    @GetMapping
    public ResponseEntity<List<Director>> getAllDirectors(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
    
        if (page >= 0 && pageSize > 0) {
            List<Director> directors = service.getAll(page, pageSize);
            return new ResponseEntity<>(directors, HttpStatus.OK);
        } else {
            List<Director> directors = service.getAll();
            return new ResponseEntity<>(directors, HttpStatus.OK);
        }
    }
    

    @Operation(summary = "Get a director by ID")
    @ApiResponse(responseCode = "200", description = "Found the director", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Director.class)))
    @ApiResponse(responseCode = "404", description = "Director not found")
    @GetMapping("{idDirector}")
    public ResponseEntity<Director> getById(@PathVariable Integer idDirector) {
        Director director = service.getById(idDirector);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    @Operation(summary = "Register a new director")
    @ApiResponse(responseCode = "201", description = "Director registered successfully")
    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody Director director) {
        service.save(director);
        return new ResponseEntity<>("Director registered successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Update a director by ID")
    @ApiResponse(responseCode = "200", description = "Record updated successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @PutMapping("{idDirector}")
    public ResponseEntity<String> update(@Valid @RequestBody Director director, @PathVariable Integer idDirector) {
        try {
            Director auxDirector = service.getById(idDirector);
            director.setIdDirector(auxDirector.getIdDirector());
            service.save(director);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a director by ID")
    @ApiResponse(responseCode = "200", description = "Director deleted successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @DeleteMapping("{idDirector}")
    public ResponseEntity<String> delete(@PathVariable Integer idDirector) {
        try {
            service.delete(idDirector);
            return new ResponseEntity<>("Director deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get directors by first name")
    @GetMapping("/search/firstName/{firstName}")
    public ResponseEntity<List<Director>> getByFirstName(@PathVariable String firstName) {
        List<Director> directors = service.findByFirstName(firstName);
        if (directors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @Operation(summary = "Get directors by father's last name")
    @GetMapping("/search/lastNameFather/{lastNameFather}")
    public ResponseEntity<List<Director>> getByLastNameFather(@PathVariable String lastNameFather) {
        List<Director> directors = service.findByLastNameFather(lastNameFather);
        if (directors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @Operation(summary = "Get directors by mother's last name")
    @GetMapping("/search/lastNameMother/{lastNameMother}")
    public ResponseEntity<List<Director>> getByLastNameMother(@PathVariable String lastNameMother) {
        List<Director> directors = service.findByLastNameMother(lastNameMother);
        if (directors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }
}
