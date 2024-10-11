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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.administrator.filmarte.service.CommentService;
import com.administrator.filmarte.model.Comment;

@RestController
@RequestMapping("comments")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Comment", description = "Provides methods for managing comments")

public class CommentController {
    @Autowired
    private CommentService service;

    @Operation(summary = "Get all comments")
    @ApiResponse(responseCode = "200", description = "Found comments", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comment.class)))})

    @GetMapping
    public List<Comment> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Get a comment by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)})
    @GetMapping("{idComment}")
    public ResponseEntity<?> getByIdComment(@PathVariable Integer idComment){
        Comment comment = service.getByIdComment(idComment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @Operation(summary = "Register a new comment")
    @ApiResponse(responseCode = "201", description = "Comment registered successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))})
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Comment comment) {
        service.save(comment);
        return new ResponseEntity<String>("Saved record", HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated record", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))}),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)})
    @PutMapping("{idComment}")
    public ResponseEntity<?> update(@RequestBody Comment comment, @PathVariable Integer idComment){
        try{
            Comment auxComment = service.getByIdComment(idComment);
            comment.setIdComment(auxComment.getIdComment());
            service.save(comment);
            return new ResponseEntity<>("Updated record", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>("The record with the id administrator provided is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a comment by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)})
    @DeleteMapping("{idComment}")
    public void delete(@PathVariable Integer idComment) {
        service.delete(idComment); 
    }
}
