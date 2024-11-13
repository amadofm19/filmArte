package com.administrator.filmarte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Administrator;
import com.administrator.filmarte.repository.AdministratorRepository;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;


@Service
@Transactional
@Schema(description = "Service class for managing administrators.")
public class AdministratorService {

    @Autowired
    private AdministratorRepository repo;

    //@Operation(summary = "Retrieve all administrators", description = "Returns a list of all administrators in the system.")
    //@ApiResponse(responseCode = "200", description = "Found Administrators", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)))
    public List<Administrator> getAll(){
        return repo.findAll();
    }

    //Pagination
    public List<Administrator> getAll(int page, int pageSize){
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Administrator> administrators = repo.findAll(pageReq);
        return administrators.getContent();
    }

   // @Operation(summary = "Save an administrator", description = "Saves a new or updated administrator to the repository.")
    //@ApiResponse(responseCode = "201", description = "Administrator saved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)))
    public void save(Administrator administrator){
        repo.save(administrator);
    }

    //@Operation(summary = "Retrieve an administrator by ID", description = "Returns the administrator with the specified ID.")
    @ApiResponse(responseCode = "200", description = "Administrator found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)))
    public Administrator getByIAdministrator(Integer idAdministrator) {
        return repo.findById(idAdministrator).get();
    }

   // @Operation(summary = "Delete an administrator by ID", description = "Deletes the administrator with the specified ID.")
   // @ApiResponse(responseCode = "204", description = "Administrator deleted successfully")
    public void delete(Integer idAdministrator) {
        repo.deleteById(idAdministrator);
    }

    //Nuevos metodos
    public List<Administrator> findByName(String name) {
        return repo.findByName(name);
    }
    
    public List<Administrator> findByLastname(String lastname) {
        return repo.findByLastname(lastname);
    }
    
    public List<Administrator> findByPassword(String password) {
        return repo.findByPassword(password);
    }
    

}
