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

import com.administrator.filmarte.model.Director;
import com.administrator.filmarte.service.DirectorService;

import io.swagger.v3.oas.annotations.Operation; // Importar la anotación Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Importar la anotación ApiResponse
import io.swagger.v3.oas.annotations.media.Content; // Importar la anotación Content
import io.swagger.v3.oas.annotations.media.Schema; // Importar la anotación Schema
import io.swagger.v3.oas.annotations.tags.Tag; // Importar la anotación Tag
import io.swagger.v3.oas.annotations.media.ArraySchema; // Importar la anotación ArraySchema

@RestController
@RequestMapping("/directors")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Directors", description = "Provides methods for managing directors") // Anotación Tag agregada
public class DirectorController {

    @Autowired
    private DirectorService service;

    // Obtener todos los directores
    @Operation(summary = "Get all directors") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Found directors", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Director.class)))) // Descripción

    @GetMapping
    public List<Director> getAll() {
        return service.getAll();
    }

    // Obtener un director por su ID
    @Operation(summary = "Get a director by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Found the director", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Director.class))) // Descripción

    @ApiResponse(responseCode = "404", description = "Director not found") // Descripción del error
    @GetMapping("{id}")
    public ResponseEntity<Director> getById(@PathVariable Integer id) {
        Director director = service.getById(id);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    // Registrar un nuevo director
    @Operation(summary = "Register a new director") // Resumen de la operación
    @ApiResponse(responseCode = "201", description = "Director registered successfully") // Descripción de la respuesta
    @PostMapping
    public ResponseEntity<String> register(@RequestBody Director director) {
        service.save(director);
        return new ResponseEntity<>("Director registered successfully", HttpStatus.CREATED);
    }

    // Actualizar un director por su ID
    @Operation(summary = "Update a director by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Record updated successfully") // Descripción de la respuesta
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") // Descripción del error
    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Director director, @PathVariable Integer id) {
        try {
            Director auxDirector = service.getById(id);
            director.setId(auxDirector.getId());
            service.save(director);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un director por su ID
    @Operation(summary = "Delete a director by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Director deleted successfully") // Descripción de la respuesta
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") // Descripción del error
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<>("Director deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }
}
