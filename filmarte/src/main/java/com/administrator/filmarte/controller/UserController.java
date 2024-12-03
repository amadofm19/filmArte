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

import com.administrator.filmarte.model.User;
import com.administrator.filmarte.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "User", description = "Provides methods for managing users")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(summary = "Get all users or with pagination")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        if (page >= 0 && pageSize > 0) {
            List<User> users = service.getAll(page, pageSize);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            List<User> users = service.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get a user by his or her ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid control number supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @GetMapping("{idUser}")
    public ResponseEntity<User> getById(@PathVariable Integer idUser) {
        try {
            User user = service.getById(idUser);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Register a user")
    @ApiResponse(responseCode = "201", description = "User registered successfully", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> registrar(@Valid @RequestBody User user) {
        service.save(user);
        return new ResponseEntity<>("Saved record", HttpStatus.CREATED);
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @PutMapping("{idUser}")
    public ResponseEntity<String> update(@Valid @RequestBody User user, @PathVariable Integer idUser) {
        try {
            User auxUser = service.getById(idUser);
            user.setIdUser(auxUser.getIdUser());
            service.save(user);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a user by his or her ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Record not found with the provided ID", content = @Content)})
    @DeleteMapping("{idUser}")
    public ResponseEntity<String> delete(@PathVariable Integer idUser) {
        try {
            service.delete(idUser);
            return new ResponseEntity<>("Deleted record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Search users by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found users", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))}),
        @ApiResponse(responseCode = "404", description = "No users found", content = @Content)})
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchByName(@RequestParam String name) {
        List<User> users = service.findByName(name);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Search users by membership type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found users", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))}),
        @ApiResponse(responseCode = "404", description = "No users found", content = @Content)})
    @GetMapping("/search/membership")
    public ResponseEntity<?> searchByMembership(@RequestParam String membership) {
        List<User> users = service.findByMembership(membership);
        if (users.isEmpty()) {
            return new ResponseEntity<>("No users found with the provided membership type", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
