package com.administrator.filmarte.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.administrator.filmarte.service.CommentService;
import java.util.List;
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
import com.administrator.filmarte.model.Comment;

@Validated
@RestController
@RequestMapping("comments")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
    RequestMethod.PUT})
@Tag(name = "Comment", description = "Provides methods for managing comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @Operation(summary = "Get all comments or comments with pagination")
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize) {
        if (page >= 0 && pageSize > 0) {
            List<Comment> comments = service.getAll(page, pageSize);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            List<Comment> comments = service.getAll();
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get a comment by his or her ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comment found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Actor not found", content = @Content)})
    @GetMapping("{idComment}")
    public ResponseEntity<?> getById(@PathVariable Integer idComment) {
        Comment comment = service.getById(idComment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @Operation(summary = "Register a comment with sentiment analysis")
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Comment comment) {
        String sentiment = service.analyzeSentiment(comment.getContent());
        comment.setAnalysis(sentiment);
        service.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @Operation(summary = "Update comments")
    @PutMapping("{idComment}")
    public ResponseEntity<?> update(@Valid @RequestBody Comment comment, @PathVariable Integer idComment) {
        Comment auxComment = service.getById(idComment);
        comment.setIdComment(auxComment.getIdComment());
        service.save(comment);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }

    @Operation(summary = "Delete Comment")
    @DeleteMapping("{idComment}")
    public ResponseEntity<?> delete(@PathVariable Integer idComment) {
        service.delete(idComment);
        return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
    }

    @Operation(summary = "Search comments by author")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found comments by author", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comment.class)))}),
        @ApiResponse(responseCode = "404", description = "No comments found with the provided author", content = @Content)})
    @GetMapping("/search/author")
    public ResponseEntity<?> searchByAuthor(@RequestParam String author) {
        List<Comment> comments = service.findByAuthor(author);
        if (comments.isEmpty()) {
            return new ResponseEntity<>("No comments found with the provided author", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
