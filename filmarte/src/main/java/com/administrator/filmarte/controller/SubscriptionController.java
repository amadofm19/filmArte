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
import com.administrator.filmarte.model.Subscription;
import com.administrator.filmarte.service.SubscriptionService;
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

@RestController
@RequestMapping("subscriptions")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Subscription", description = "Provides methods for managing subscriptions")

public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @Operation(summary = "Get all subscriptions")
    @ApiResponse(responseCode = "200", description = "Found subscriptions", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Subscription.class))) })

    @GetMapping
    public List<Subscription> getAll() {
        return service.getAll();
    }

    // PAGINATION
    @Operation(summary = "Get all subscriptions with pagination")
    @GetMapping(value = "/pagination", params = { "page", "size" })
    public List<Subscription> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        List<Subscription> subscriptions = service.getAll(page, pageSize);
        return subscriptions;
    }

    @Operation(summary = "Get a subscription by his or her ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "subscription found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Subscription.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "subscription not found", content = @Content) })

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Subscription subscription = service.getById(id);
        return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
    }

    @Operation(summary = "Register a subscription")
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Subscription subscription) {
        service.save(subscription);
        return new ResponseEntity<String>("Saved record", HttpStatus.OK);
    }

    @Operation(summary = "Update subscriptions")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody Subscription subscription, @PathVariable Integer id) {
        Subscription auxSubscription = service.getById(id);
        subscription.setId(auxSubscription.getId());
        service.save(subscription);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }

    @Operation(summary = "Delete subscription")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@RequestBody Subscription subscription, @PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
    }

}
