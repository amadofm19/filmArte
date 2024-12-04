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

import com.administrator.filmarte.model.Subscription;
import com.administrator.filmarte.service.SubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("subscriptions")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Subscription", description = "Provides methods for managing subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @Operation(summary = "Get all subscriptions or with pagination")
    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        if (page >= 0 && pageSize > 0) {
            List<Subscription> subscriptions = service.getAll(page, pageSize);
            return new ResponseEntity<>(subscriptions, HttpStatus.OK);
        } else {
            List<Subscription> subscriptions = service.getAll();
            return new ResponseEntity<>(subscriptions, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get a subscription by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Subscription found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Subscription.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content)})
    @GetMapping("{idSubscription}")
    public ResponseEntity<Subscription> getById(@PathVariable Integer idSubscription) {
        Subscription subscription = service.getById(idSubscription);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @Operation(summary = "Register a subscription")
    @ApiResponse(responseCode = "201", description = "Subscription registered successfully", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Subscription.class))})
    @PostMapping
    public ResponseEntity<String> registrar(@Valid @RequestBody Subscription subscription) {
        service.save(subscription);
        return new ResponseEntity<>("Saved record", HttpStatus.CREATED);
    }

    @Operation(summary = "Update a subscription")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Subscription.class))}),
        @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content)})
    @PutMapping("{idSubscription}")
    public ResponseEntity<String> update(@Valid @RequestBody Subscription subscription, @PathVariable Integer idSubscription) {
        try {
            Subscription auxSubscription = service.getById(idSubscription);
            subscription.setIdSubscription(auxSubscription.getIdSubscription());
            service.save(subscription);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a subscription by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Subscription deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Record not found with the provided ID", content = @Content)})
    @DeleteMapping("{idSubscription}")
    public ResponseEntity<String> delete(@PathVariable Integer idSubscription) {
        try {
            service.delete(idSubscription);
            return new ResponseEntity<>("Deleted record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get subscriptions by membership type")
    @GetMapping("search/membershipType")
    public ResponseEntity<?> getByMembershipType(@RequestParam("membershipType") String membershipType) {
        List<Subscription> subscriptions = service.findByMembershipType(membershipType);
        if (subscriptions.isEmpty()) {
            return new ResponseEntity<>("No subscriptions found with the membership type: " + membershipType, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @Operation(summary = "Get subscriptions by cost")
    @GetMapping("search/cost")
    public ResponseEntity<?> getByCost(@RequestParam("cost") double cost) {
        List<Subscription> subscriptions = service.findByCost(cost);
        if (subscriptions.isEmpty()) {
            return new ResponseEntity<>("No subscriptions found with the cost: " + cost, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @Operation(summary = "Get subscriptions by duration")
    @GetMapping("search/duration")
    public ResponseEntity<?> getByDuration(@RequestParam("duration") int duration) {
        List<Subscription> subscriptions = service.findByDuration(duration);
        if (subscriptions.isEmpty()) {
            return new ResponseEntity<>("No subscriptions found with the duration: " + duration, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @Operation(summary = "Get subscriptions by payment method")
    @GetMapping("search/paymentMethod")
    public ResponseEntity<?> getByPaymentMethod(@RequestParam("paymentMethod") String paymentMethod) {
        List<Subscription> subscriptions = service.findByPaymentMethod(paymentMethod);
        if (subscriptions.isEmpty()) {
            return new ResponseEntity<>("No subscriptions found with the payment method: " + paymentMethod, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

}
