/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.administrator.filmarte.model.Actor;
import com.administrator.filmarte.service.ActorService;
import java.util.List;
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

@Validated
@RestController
@RequestMapping("actors")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
    RequestMethod.PUT})
@Tag(name = "Actor", description = "Provides methods for managing actors")
public class ActorController {

    @Autowired
    private ActorService service;

    @Operation(summary = "Get all actors or actors with pagination")
    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        if (page >= 0 && pageSize > 0) {
            List<Actor> actors = service.getAll(page, pageSize);
            return new ResponseEntity<>(actors, HttpStatus.OK);
        } else {
            List<Actor> actors = service.getAll();
            return new ResponseEntity<>(actors, HttpStatus.OK);
        }
    }
    
    @Operation(summary = "Get an actor by his or her ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Actor found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Actor.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Actor not found", content = @Content)})
    @GetMapping("{idActor}")
    public ResponseEntity<?> getById(@PathVariable Integer idActor) {
        Actor actor = service.getById(idActor);
        return new ResponseEntity<Actor>(actor, HttpStatus.OK);
    }

    @Operation(summary = "Register an actor")
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Actor actor) {
        service.save(actor);
        return new ResponseEntity<>("Saved record", HttpStatus.CREATED);
    }

    @Operation(summary = "Update actors")
    @PutMapping("{idActor}")
    public ResponseEntity<?> update(@Valid @RequestBody Actor actor, @PathVariable Integer idActor) {
        Actor auxActor = service.getById(idActor);
        actor.setIdActor(auxActor.getIdActor());
        service.save(actor);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }

    @Operation(summary = "Delete actor")
    @DeleteMapping("{idActor}")
    public ResponseEntity<?> delete(@PathVariable Integer idActor) {
        service.delete(idActor);
        return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
    }

    @Operation(summary = "Search actors by first name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found actors", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Actor.class)))}),
        @ApiResponse(responseCode = "404", description = "No actors found", content = @Content)})
    @GetMapping("/search/firstName")
    public ResponseEntity<?> searchByFirstName(@RequestParam String firstName) {
        List<Actor> actors = service.findByFirstName(firstName);
        if (actors.isEmpty()) {
            return new ResponseEntity<>("No actors found with the provided first name", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @Operation(summary = "Search actors by last name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found actors", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Actor.class)))}),
        @ApiResponse(responseCode = "404", description = "No actors found", content = @Content)})
    @GetMapping("/search/lastName")
    public ResponseEntity<?> searchByLastName(@RequestParam String lastName) {
        List<Actor> actors = service.findByLastName(lastName);
        if (actors.isEmpty()) {
            return new ResponseEntity<>("No actors found with the provided last name", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @Operation(summary = "Search actors by description")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found actors", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Actor.class)))}),
        @ApiResponse(responseCode = "404", description = "No actors found", content = @Content)})
    @GetMapping("/search/description")
    public ResponseEntity<?> searchByDescription(@RequestParam String description) {
        List<Actor> actors = service.findByDescription(description);
        if (actors.isEmpty()) {
            return new ResponseEntity<>("No actors found with the provided description", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }
}
