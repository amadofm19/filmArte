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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import com.administrator.filmarte.service.PayService;
import com.administrator.filmarte.model.Pay;

@Validated
@RestController
@RequestMapping("pays")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Pay", description = "Provides methods for managing pays")
public class PayController {

    @Autowired
    private PayService service;

    @Operation(summary = "Get all pays or pays with pagination")
    @GetMapping
    public ResponseEntity<List<Pay>> getAllPays(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {

        if (page >= 0 && pageSize > 0) {
            List<Pay> pays = service.getAll(page, pageSize);
            return new ResponseEntity<>(pays, HttpStatus.OK);
        } else {
            List<Pay> pays = service.getAll();
            return new ResponseEntity<>(pays, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get a pay by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pay found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Pay.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Pay not found", content = @Content)})
    @GetMapping("{idPay}")
    public ResponseEntity<Pay> getByidPay(@PathVariable Integer idPay) {
        Pay pay = service.getByIdPay(idPay);
        return new ResponseEntity<Pay>(pay, HttpStatus.OK);
    }

    @Operation(summary = "Register a new pay")
    @ApiResponse(responseCode = "201", description = "Pay registered successfully", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Pay.class))})
    @PostMapping
    public ResponseEntity<String> registrar(@Valid @RequestBody Pay pay) {
        service.save(pay);
        return new ResponseEntity<>("Saved record", HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing pay")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Pay.class))}),
        @ApiResponse(responseCode = "404", description = "Pay not found", content = @Content)})
    @PutMapping("{idPay}")
    public ResponseEntity<String> update(@Valid @RequestBody Pay pay, @PathVariable Integer idPay) {
        try {
            Pay auxPay = service.getByIdPay(idPay);
            pay.setIdPay(auxPay.getIdPay());
            service.save(pay);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("The record with the id administrator provided is not found in the database",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a pay by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pay deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Pay not found", content = @Content)})
    @DeleteMapping("{idPay}")
    public ResponseEntity<String> delete(@PathVariable Integer idPay) {
        try {
            service.delete(idPay);
            return new ResponseEntity<>("Pay deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("The record with the id administrator provided is not found in the database",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Search payments by method")
    @GetMapping("/search/method")
    public ResponseEntity<?> searchByPaymentMethod(@RequestParam String paymentMethod) {
        List<Pay> pays = service.findByPaymentMethod(paymentMethod);
        if (pays.isEmpty()) {
            return new ResponseEntity<>("No payments found for the specified payment method", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pays, HttpStatus.OK);
    }

    @Operation(summary = "Search payments by currency")
    @GetMapping("/search/currency")
    public ResponseEntity<?> searchByCurrency(@RequestParam String currency) {
        List<Pay> pays = service.findByCurrency(currency);
        if (pays.isEmpty()) {
            return new ResponseEntity<>("No payments found for the specified currency", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pays, HttpStatus.OK);
    }

}
