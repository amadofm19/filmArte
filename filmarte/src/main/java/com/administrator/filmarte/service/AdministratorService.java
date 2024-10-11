package com.administrator.filmarte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Administrator;
import com.administrator.filmarte.repository.AdministratorRepository;
import jakarta.transaction.Transactional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Service
@Transactional
@Schema(description = "Service class for managing administrators.")
public class AdministratorService {

    @Autowired
    private AdministratorRepository repo;

    @Operation(summary = "Retrieve all administrators", description = "Returns a list of all administrators in the system.")
    @ApiResponse(responseCode = "200", description = "Found Administrators", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)))
    public List<Administrator> getAll(){
        return repo.findAll();
    }

    @Operation(summary = "Save an administrator", description = "Saves a new or updated administrator to the repository.")
    @ApiResponse(responseCode = "201", description = "Administrator saved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)))
    public void save(Administrator administrator){
        repo.save(administrator);
    }

    @Operation(summary = "Retrieve an administrator by ID", description = "Returns the administrator with the specified ID.")
    @ApiResponse(responseCode = "200", description = "Administrator found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)))
    public Administrator getByIAdministrator(Integer idAdministrator) {
        return repo.findById(idAdministrator).get();
    }

    @Operation(summary = "Delete an administrator by ID", description = "Deletes the administrator with the specified ID.")
    @ApiResponse(responseCode = "204", description = "Administrator deleted successfully")
    public void delete(Integer idAdministrator) {
        repo.deleteById(idAdministrator);
    }
}
