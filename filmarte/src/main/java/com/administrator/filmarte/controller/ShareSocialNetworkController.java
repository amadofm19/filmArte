package com.administrator.filmarte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.administrator.filmarte.model.ShareSocialNetwork;
import com.administrator.filmarte.service.ShareSocialNetworkService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("shareSocialNetworks")
public class ShareSocialNetworkController {

   @Autowired
   private ShareSocialNetworkService service;

   @Operation(summary = "Get all shared social networks")
   @ApiResponses(value = {
       @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
       @ApiResponse(responseCode = "500", description = "Server error occurred while retrieving social networks")
   })
   @GetMapping()
   public Iterable<ShareSocialNetwork> getAll() {
       return service.getAll();
   }

    @Operation(summary = "Get ShareSocialNetworks with pagination")
    @GetMapping(value = "/pagination", params = { "page", "size" })
    public List<ShareSocialNetwork> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        return service.getAll(page, pageSize);
    }

   @Operation(summary = "Search social networks by name")
   @ApiResponses(value = {
       @ApiResponse(responseCode = "200", description = "Search completed successfully"),
       @ApiResponse(responseCode = "404", description = "No social networks found with the specified name"),
       @ApiResponse(responseCode = "500", description = "Server error occurred during the search")
   })
   @GetMapping("{nameNetwork}")
   public Iterable<ShareSocialNetwork> searchByNameNetwork(@PathVariable String nameNetwork) {
       return service.searchByNameNetwork(nameNetwork);
   }

   @Operation(summary = "Add a new shared social network")
   @ApiResponses(value = {
       @ApiResponse(responseCode = "200", description = "Record saved successfully"),
       @ApiResponse(responseCode = "400", description = "Invalid request data"),
       @ApiResponse(responseCode = "500", description = "Server error occurred while saving the record")
   })
   @PostMapping()
   public ResponseEntity<?> add(@RequestBody ShareSocialNetwork resource) {
       service.add(resource);
       return new ResponseEntity<>("Saved record", HttpStatus.OK);
   }

   @Operation(summary = "Update an existing shared social network")
   @ApiResponses(value = {
       @ApiResponse(responseCode = "200", description = "Record updated successfully"),
       @ApiResponse(responseCode = "404", description = "Social network not found"),
       @ApiResponse(responseCode = "400", description = "Invalid request data"),
       @ApiResponse(responseCode = "500", description = "Server error occurred while updating the record")
   })
   @PutMapping("{idNetwork}")
   public ResponseEntity<?> update(@RequestBody ShareSocialNetwork resource, @PathVariable String idNetwork) {
       service.update(resource, idNetwork);
       return new ResponseEntity<>("Updated record", HttpStatus.OK);
   }

   @Operation(summary = "Delete a shared social network by ID")
   @ApiResponses(value = {
       @ApiResponse(responseCode = "200", description = "Record deleted successfully"),
       @ApiResponse(responseCode = "404", description = "Social network not found"),
       @ApiResponse(responseCode = "500", description = "Server error occurred while deleting the record")
   })
   @DeleteMapping("{idNetwork}")
   public ResponseEntity<?> delete(@PathVariable String idNetwork) {
       service.delete(idNetwork);
       return new ResponseEntity<>("Deleted record", HttpStatus.OK);
   }

   @Operation(summary = "Search social networks by URL")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Search completed successfully"),
    @ApiResponse(responseCode = "404", description = "No social networks found with the specified URL"),
    @ApiResponse(responseCode = "500", description = "Server error occurred during the search")
    })
    @GetMapping("/search/url/{url}")
    public ResponseEntity<Iterable<ShareSocialNetwork>> searchByUrl(@PathVariable String url) {
    List<ShareSocialNetwork> socialNetworks = service.searchByUrl(url);
    if (socialNetworks.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(socialNetworks);
}
}
