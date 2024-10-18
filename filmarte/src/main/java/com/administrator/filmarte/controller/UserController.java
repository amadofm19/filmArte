package com.administrator.filmarte.controller;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.administrator.filmarte.model.User;
import com.administrator.filmarte.service.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@Tag(name = "User", description = "Provides methods for managing users")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "Found users", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))) })

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get all users with pagination")
    @GetMapping(value = "/pagination", params = { "page", "size" })
    public List<User> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        List<User> users = service.getAll(page, pageSize);
        return users;
    }

    @Operation(summary = "Get a user by his or her ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid control number supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })

    @GetMapping("{idUser}")
    public ResponseEntity<?> getById(@PathVariable Integer idUser) {
        User user = service.getById(idUser);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Operation(summary = "Register a user")
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody User user) {
        service.save(user);
        return new ResponseEntity<String>("Saved record", HttpStatus.OK);
    }

    @Operation(summary = "Update users")
    @PutMapping("{idUser}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer idUser) {
        User auxUser = service.getById(idUser);
        user.setIdUser(auxUser.getIdUser());
        service.save(user);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("{idUser}")
    public ResponseEntity<?> delete(@RequestBody User user, @PathVariable Integer idUser) {
        service.delete(idUser);
        return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
    }
}
