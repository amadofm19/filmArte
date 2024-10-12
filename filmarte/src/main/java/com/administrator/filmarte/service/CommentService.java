package com.administrator.filmarte.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Comment;
import com.administrator.filmarte.repository.CommentRepository;
import jakarta.transaction.Transactional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Service
@Transactional
@Schema(description = "Service class for managing comments.")
public class CommentService {

    @Autowired
    private CommentRepository repo;

    @Operation(summary = "Retrieve all comments", description = "Returns a list of all comments in the system.")
    @ApiResponse(responseCode = "200", description = "Found comments", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)))
    public List<Comment> getAll(){
        return repo.findAll();
    }

    //Pagination
    public List<Comment> getAll(int page, int pageSize){
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Comment> comments = repo.findAll(pageReq);
        return comments.getContent();
    }

    @Operation(summary = "Save a comment", description = "Saves a new or updated comment to the repository.")
    @ApiResponse(responseCode = "201", description = "Comment saved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)))
    public void save(Comment comment){
        repo.save(comment);
    }

    @Operation(summary = "Retrieve a comment by ID", description = "Returns the comment with the specified ID.")
    @ApiResponse(responseCode = "200", description = "Comment found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class)))
    public Comment getByIdComment(Integer idComment){
        return repo.findById(idComment).get();
    }

    @Operation(summary = "Delete a comment by ID", description = "Deletes the comment with the specified ID.")
    @ApiResponse(responseCode = "204", description = "Comment deleted successfully")
    public void delete (Integer idComment){
        repo.deleteById(idComment);
    }
}
