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

import com.administrator.filmarte.model.Administrator;
import com.administrator.filmarte.service.AdministratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("administrators")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Administrator", description = "Provides methods for managing administrators")
public class AdministratorController {
    
    @Autowired
    private AdministratorService service;

    @Operation(summary = "Get all administrators")
    @ApiResponse(responseCode = "200", description = "Found Administrators", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Administrator.class)))})

    @GetMapping
    public List<Administrator> getAll () {
        return service.getAll();
    }

    @Operation(summary = "Get an administrator by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrator found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Administrator not found", content = @Content)})
    @GetMapping("{idAdministrator}")
    public ResponseEntity<Administrator> getByIdAdministrator(@PathVariable Integer idAdministrator){
        Administrator administrator = service.getByIAdministrator(idAdministrator);
        return new ResponseEntity<>(administrator, HttpStatus.OK);
    }

    @Operation(summary = "Register a new administrator")
    @ApiResponse(responseCode = "201", description = "Administrator registered successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class))})
    @PostMapping
    public void registrar(@RequestBody Administrator administrator){
        service.save(administrator);
    }

    @Operation(summary = "Update an existing administrator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated record", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class))}),
            @ApiResponse(responseCode = "404", description = "Administrator not found", content = @Content)})
    @PutMapping("{idAdministrator}")
    public ResponseEntity<?> update(@RequestBody Administrator administrator, @PathVariable Integer idAdministrator){
        try{
            Administrator auxAdministrator = service.getByIAdministrator(idAdministrator);
            administrator.setIdAdministrator(auxAdministrator.getIdAdministrator());
            service.save(administrator);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>("The record with the id administrator provided is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete an administrator by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Administrator deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Administrator not found", content = @Content)})
    @DeleteMapping("{idAdministrator}")
    public void delete(@PathVariable Integer idAdministrator) {
        service.delete(idAdministrator); 
    }
}
