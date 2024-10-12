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
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.administrator.filmarte.service.RewardService;
import com.administrator.filmarte.model.Reward;

@RestController
@RequestMapping("rewards")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag (name = "Reward", description = "Provides methods for managing rewards")
public class RewardController {
    @Autowired
    private RewardService service;

    @Operation(summary = "Get all rewards")
    @ApiResponse(responseCode = "200", description = "Found rewards", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Reward.class)))})

    @GetMapping
    public List<Reward> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get all rewards with pagination")
    @GetMapping(value = "pagination", params = {"page", "pageSize"})
    public List<Reward> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
            List<Reward> rewards = service.getAll(page, pageSize);
            return rewards;
    }

    @Operation(summary = "Get a reward by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reward found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reward not found", content = @Content)})
    @GetMapping("{idReward}")
    public ResponseEntity<?> getByIdReward(@PathVariable Integer idReward){
        Reward reward = service.getByIdReward(idReward);
        return new ResponseEntity<Reward>(reward, HttpStatus.OK);
    }

    @Operation(summary = "Register a new reward")
    @ApiResponse(responseCode = "201", description = "Reward registered successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))})
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Reward reward) {
        service.save(reward);
        return new ResponseEntity<String>("Saved record", HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing reward")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated record", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))}),
            @ApiResponse(responseCode = "404", description = "Reward not found", content = @Content)})
    @PutMapping("{idReward}")
    public ResponseEntity<?> update(@RequestBody Reward reward, @PathVariable Integer idReward){
        try{
            Reward auxReward = service.getByIdReward(idReward);
            reward.setIdReward(auxReward.getIdReward());
            service.save(reward);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>("The record with the id administrator provided is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a reward by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reward deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Reward not found", content = @Content)})
    @DeleteMapping("{idReward}")
    public void delete(@PathVariable Integer idReward) {
        service.delete(idReward); 
    }
}
