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

import com.administrator.filmarte.model.Reward;
import com.administrator.filmarte.service.RewardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("rewards")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Reward", description = "Provides methods for managing rewards")
public class RewardController {

    @Autowired
    private RewardService service;

    @Operation(summary = "Get all rewards or rewards with pagination")
    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        if (page >= 0 && pageSize > 0) {
            List<Reward> rewards = service.getAll(page, pageSize);
            return new ResponseEntity<>(rewards, HttpStatus.OK);
        } else {
            List<Reward> rewards = service.getAll();
            return new ResponseEntity<>(rewards, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get a reward by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reward found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Reward not found", content = @Content)})
    @GetMapping("{idReward}")
    public ResponseEntity<Reward> getByIdReward(@PathVariable Integer idReward) {
        try {
            Reward reward = service.getByIdReward(idReward);
            return new ResponseEntity<>(reward, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Register a new reward")
    @ApiResponse(responseCode = "201", description = "Reward registered successfully", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))})
    @PostMapping
    public ResponseEntity<String> registrar(@Valid @RequestBody Reward reward) {
        service.save(reward);
        return new ResponseEntity<>("Saved record", HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing reward")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated record", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))}),
        @ApiResponse(responseCode = "404", description = "Reward not found", content = @Content)})
    @PutMapping("{idReward}")
    public ResponseEntity<String> update(@Valid @RequestBody Reward reward, @PathVariable Integer idReward) {
        try {
            Reward auxReward = service.getByIdReward(idReward);
            reward.setIdReward(auxReward.getIdReward());
            service.save(reward);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a reward by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reward deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Reward not found", content = @Content)})
    @DeleteMapping("{idReward}")
    public ResponseEntity<String> delete(@PathVariable Integer idReward) {
        try {
            service.delete(idReward);
            return new ResponseEntity<>("Deleted record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Record not found with the provided ID", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Search rewards by name")
    @GetMapping("search/name")
    public ResponseEntity<?> getByNameReward(@RequestParam("nameReward") String nameReward) {
        List<Reward> rewards = service.findByNameReward(nameReward);
        if (rewards.isEmpty()) {
            return new ResponseEntity<>("No rewards found with the name: " + nameReward, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

    @Operation(summary = "Search rewards by nomination")
    @GetMapping("search/nomination")
    public ResponseEntity<?> getByNomination(@RequestParam("nomination") String nomination) {
        List<Reward> rewards = service.findByNomination(nomination);
        if (rewards.isEmpty()) {
            return new ResponseEntity<>("No rewards found with the nomination: " + nomination, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

}
