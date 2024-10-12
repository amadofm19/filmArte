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
import com.administrator.filmarte.model.Actor;
import com.administrator.filmarte.service.ActorService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ARACELI
 */
@RestController
@RequestMapping("actors")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Actor", description = "Provides methods for managing actors")
public class ActorController {

    @Autowired
    private ActorService service;

    @Operation(summary = "Get all actors")
    @ApiResponse(responseCode = "200", description = "Found actors", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Actor.class))) })

    @GetMapping
    public List<Actor> getAll() {
        return service.getAll();
    }

    // PAGINATION
 @Operation(summary = "Get actors with pagination")
    @GetMapping(value = "pagination", params = { "page", "size" })
    public List<Actor> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        return service.getAll(page, pageSize);  
    }
    
    @Operation(summary = "Get an actor by his or her ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actor found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Actor.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Actor not found", content = @Content) })

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Actor actor = service.getById(id);
        return new ResponseEntity<Actor>(actor, HttpStatus.OK);
    }

    @Operation(summary = "Register an actor")
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Actor actor) {
        service.save(actor);
        return new ResponseEntity<String>("Saved record", HttpStatus.OK);
    }

    @Operation(summary = "Update actors")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody Actor actor, @PathVariable Integer id) {
        Actor auxActor = service.getById(id);
        actor.setId(auxActor.getId());
        service.save(actor);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }

    @Operation(summary = "Delete actor")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@RequestBody Actor actor, @PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
    }

}
