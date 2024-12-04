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

import com.administrator.filmarte.model.History;
import com.administrator.filmarte.service.HistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/histories")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Histories", description = "Provides methods for managing histories")
public class HistoryController {

    @Autowired
    private HistoryService service;

    @Operation(summary = "Get all histories or histories with pagination")
    @GetMapping
    public ResponseEntity<List<History>> getAllHistories(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        if (page >= 0 && pageSize > 0) {
            List<History> histories = service.getAll(page, pageSize);
            return new ResponseEntity<>(histories, HttpStatus.OK);
        } else {
            List<History> histories = service.getAll();
            return new ResponseEntity<>(histories, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get a history by ID")
    @ApiResponse(responseCode = "200", description = "Found the history", content = @Content(mediaType = "application/json", schema = @Schema(implementation = History.class)))
    @ApiResponse(responseCode = "404", description = "History not found")
    @GetMapping("{idHistory}")
    public ResponseEntity<History> getById(@PathVariable Integer idHistory) {
        History history = service.getById(idHistory);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @Operation(summary = "Register a new history")
    @ApiResponse(responseCode = "201", description = "History registered successfully")
    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody History history) {
        service.save(history);
        return new ResponseEntity<>("History registered successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Update a history by ID")
    @ApiResponse(responseCode = "200", description = "Record updated successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @PutMapping("{idHistory}")
    public ResponseEntity<String> update(@Valid @RequestBody History history, @PathVariable Integer idHistory) {
        try {
            History auxHistory = service.getById(idHistory);
            history.setIdHistory(auxHistory.getIdHistory());
            service.save(history);
            return new ResponseEntity<>("Record updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a history by ID")
    @ApiResponse(responseCode = "200", description = "History deleted successfully")
    @ApiResponse(responseCode = "404", description = "Record not found with the provided ID")
    @DeleteMapping("{idHistory}")
    public ResponseEntity<String> delete(@PathVariable Integer idHistory) {
        try {
            service.delete(idHistory);
            return new ResponseEntity<>("History deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Search histories by viewing date")
    @GetMapping("/search/date")
    public ResponseEntity<?> searchByViewingDate(@RequestParam String date) {
        List<History> histories = service.findByViewingDate(date);
        if (histories.isEmpty()) {
            return new ResponseEntity<>("No histories found for the specified viewing date", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @Operation(summary = "Search histories by duration")
    @GetMapping("/search/duration")
    public ResponseEntity<?> searchByDuration(@RequestParam int duration) {
        List<History> histories = service.findByDuration(duration);
        if (histories.isEmpty()) {
            return new ResponseEntity<>("No histories found for the specified duration", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @Operation(summary = "Search histories by genere")
    @GetMapping("/search/genere")
    public ResponseEntity<?> searchByGenere(@RequestParam String genere) {
        List<History> histories = service.findByGenere(genere);
        if (histories.isEmpty()) {
            return new ResponseEntity<>("No histories found for the specified genere", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

}
