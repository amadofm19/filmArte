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

import com.administrator.filmarte.model.History;
import com.administrator.filmarte.service.HistoryService;

import io.swagger.v3.oas.annotations.Operation; // Importar la anotación Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Importar la anotación ApiResponse
import io.swagger.v3.oas.annotations.media.Content; // Importar la anotación Content
import io.swagger.v3.oas.annotations.media.Schema; // Importar la anotación Schema
import io.swagger.v3.oas.annotations.tags.Tag; // Importar la anotación Tag
import io.swagger.v3.oas.annotations.media.ArraySchema; // Importar la anotación ArraySchema

@RestController
@RequestMapping("/histories")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Histories", description = "Provides methods for managing histories") // Anotación Tag agregada
public class HistoryController {

    @Autowired
    private HistoryService service;

    // Obtener todas las historias
    @Operation(summary = "Get all histories") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Found histories", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = History.class)))) // Descripción
                                                                                                                                                                                                    // de
                                                                                                                                                                                                    // la
                                                                                                                                                                                                    // respuesta
    @GetMapping
    public List<History> getAll() {
        return service.getAll();
    }

    // Obtener una historia por su ID
    @Operation(summary = "Get a history by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Found the history", content = @Content(mediaType = "application/json", schema = @Schema(implementation = History.class))) // Descripción
                                                                                                                                                                                // de
                                                                                                                                                                                // la
                                                                                                                                                                                // respuesta
    @ApiResponse(responseCode = "404", description = "History not found") // Descripción del error
    @GetMapping("{id}")
    public ResponseEntity<History> getById(@PathVariable Integer id) {
        History history = service.getById(id);
        return new ResponseEntity<History>(history, HttpStatus.OK);
    }

    // Registrar una nueva historia
    @Operation(summary = "Register a new history") // Resumen de la operación
    @ApiResponse(responseCode = "201", description = "History registered successfully") // Descripción de la respuesta
    @PostMapping
    public ResponseEntity<String> register(@RequestBody History history) {
        service.save(history);
        return new ResponseEntity<>("History registered successfully", HttpStatus.CREATED);
    }

    // Actualizar una historia por su ID
    @Operation(summary = "Update a history by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "Record updated successfully") // Descripción de la respuesta
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") // Descripción del error
    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody History history, @PathVariable Integer id) {
        try {
            History auxHistory = service.getById(id);
            history.setId(auxHistory.getId());
            service.save(history);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una historia por su ID
    @Operation(summary = "Delete a history by ID") // Resumen de la operación
    @ApiResponse(responseCode = "200", description = "History deleted successfully") // Descripción de la respuesta
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID") // Descripción del error
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<>("History deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

}
