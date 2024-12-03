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

import com.administrator.filmarte.model.Administrator;
import com.administrator.filmarte.service.AdministratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("administrators")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
    RequestMethod.PUT})
@Tag(name = "Administrator", description = "Provides methods for managing administrators")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @Operation(summary = "Get all administrators or administrators with pagination")
    @GetMapping
    public ResponseEntity<List<Administrator>> getAllAdministrators(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {

        if (page >= 0 && pageSize > 0) {
            List<Administrator> administrators = service.getAll(page, pageSize);
            return new ResponseEntity<>(administrators, HttpStatus.OK);
        } else {
            List<Administrator> administrators = service.getAll();
            return new ResponseEntity<>(administrators, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get an administrator by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrator found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Administrator not found", content = @Content)})
    @GetMapping("{idAdministrator}")
    public ResponseEntity<Administrator> getByIdAdministrator(@Valid @PathVariable Integer idAdministrator) {
        Administrator administrator = service.getByIAdministrator(idAdministrator);
        return new ResponseEntity<>(administrator, HttpStatus.OK);
    }

    @Operation(summary = "Register a new administrator")
    @ApiResponse(responseCode = "201", description = "Administrator registered successfully", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class))})
    @PostMapping
    public void registrar(@Valid @RequestBody Administrator administrator) {
        service.save(administrator);
    }

    @Operation(summary = "Update an existing administrator")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class))}),
        @ApiResponse(responseCode = "404", description = "Administrator not found", content = @Content)})
    @PutMapping("{idAdministrator}")
    public ResponseEntity<?> update(@RequestBody Administrator administrator,
            @PathVariable Integer idAdministrator) {
        try {
            Administrator auxAdministrator = service.getByIAdministrator(idAdministrator);
            administrator.setIdAdministrator(auxAdministrator.getIdAdministrator());
            service.save(administrator);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    "The record with the id administrator provided is not found in the database",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete an administrator")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Administrator deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Administrator not found", content = @Content)})
    @DeleteMapping("{idAdministrator}")
    public ResponseEntity<Void> delete(@PathVariable Integer idAdministrator) {
        service.delete(idAdministrator);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get administrators by name")
    @GetMapping("/search/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        List<Administrator> administrators = service.findByName(name);
        if (administrators.isEmpty()) {
            return new ResponseEntity<>("No administrators found with name: " + name, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(administrators, HttpStatus.OK);
    }

    @Operation(summary = "Get administrators by lastname")
    @GetMapping("/search/lastname/{lastname}")
    public ResponseEntity<?> getByLastname(@PathVariable String lastname) {
        List<Administrator> administrators = service.findByLastname(lastname);
        if (administrators.isEmpty()) {
            return new ResponseEntity<>("No administrators found with lastname: " + lastname,
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(administrators, HttpStatus.OK);
    }

    @Operation(summary = "Get administrators by password")
    @GetMapping("/search/password/{password}")
    public ResponseEntity<?> getByPassword(@PathVariable String password) {
        List<Administrator> administrators = service.findByPassword(password);
        if (administrators.isEmpty()) {
            return new ResponseEntity<>("No administrators found with password: " + password,
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(administrators, HttpStatus.OK);
    }

}
