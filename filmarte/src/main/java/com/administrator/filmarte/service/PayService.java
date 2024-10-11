package com.administrator.filmarte.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Pay;
import com.administrator.filmarte.repository.PayRepository;
import jakarta.transaction.Transactional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Service
@Transactional
@Schema(description = "Service class for managing payments.")
public class PayService {

    @Autowired
    private PayRepository repo;

    @Operation(summary = "Retrieve all payments", description = "Returns a list of all payments in the system.")
    @ApiResponse(responseCode = "200", description = "Found payments", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pay.class)))
    public List<Pay> getAll(){
        return repo.findAll();
    }

    @Operation(summary = "Save a payment", description = "Saves a new or updated payment to the repository.")
    @ApiResponse(responseCode = "201", description = "Payment saved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pay.class)))
    public void save(Pay pay){
        repo.save(pay);
    }

    @Operation(summary = "Retrieve a payment by ID", description = "Returns the payment with the specified ID.")
    @ApiResponse(responseCode = "200", description = "Payment found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pay.class)))
    public Pay getByIdPay(Integer idPay){
        return repo.findById(idPay).get();
    }

    @Operation(summary = "Delete a payment by ID", description = "Deletes the payment with the specified ID.")
    @ApiResponse(responseCode = "204", description = "Payment deleted successfully")
    public void delete (Integer idPay){
        repo.deleteById(idPay);
    }
}
