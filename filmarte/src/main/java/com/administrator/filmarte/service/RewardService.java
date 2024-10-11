package com.administrator.filmarte.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Reward;
import com.administrator.filmarte.repository.RewardRepository;

import jakarta.transaction.Transactional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Service
@Transactional
@Schema(description = "Service class for managing rewards.")
public class RewardService {

    @Autowired
    private RewardRepository repo;

    @Operation(summary = "Retrieve all rewards", description = "Returns a list of all rewards in the system.")
    @ApiResponse(responseCode = "200", description = "Found rewards", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class)))
    public List<Reward> getAll(){
        return repo.findAll();
    }

    @Operation(summary = "Save a reward", description = "Saves a new or updated reward to the repository.")
    @ApiResponse(responseCode = "201", description = "Reward saved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class)))
    public void save(Reward reward){
        repo.save(reward);
    }

    @Operation(summary = "Retrieve a reward by ID", description = "Returns the reward with the specified ID.")
    @ApiResponse(responseCode = "200", description = "Reward found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class)))
    public Reward getByIdReward(Integer idReward){
        return repo.findById(idReward).get();
    }

    @Operation(summary = "Delete a reward by ID", description = "Deletes the reward with the specified ID.")
    @ApiResponse(responseCode = "204", description = "Reward deleted successfully")
    public void delete (Integer idReward){
        repo.deleteById(idReward);
    }
}
