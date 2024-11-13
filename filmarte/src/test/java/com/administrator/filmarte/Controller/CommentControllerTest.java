package com.administrator.filmarte.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.administrator.filmarte.controller.CommentController;
import com.administrator.filmarte.model.Comment;
import com.administrator.filmarte.service.CommentService;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Mock
    private CommentService service;

    @InjectMocks
    private CommentController controller;

    private Comment comment;

    @BeforeEach
    void setUp() {
        comment = new Comment();
        comment.setIdComment(1);
        comment.setAuthor("Jane Doe");
        comment.setContent("This is an excellent movie!");
        comment.setType("Review");
    }

    @Test
    void testGetAll() {
        List<Comment> comments = Arrays.asList(comment);
        when(service.getAll()).thenReturn(comments);

        List<Comment> result = controller.getAll();
        assert (result.size() == 1);
        assert (result.get(0).getAuthor().equals("Jane Doe"));
    }

    @Test
    void testGetByIdComment() {
        when(service.getByIdComment(1)).thenReturn(comment);

        ResponseEntity<?> response = controller.getByIdComment(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (((Comment) response.getBody()).getAuthor().equals("Jane Doe"));
    }

    @Test
    void testRegister() {
        ResponseEntity<?> response = controller.registrar(comment);
        verify(service).save(any(Comment.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("Saved record"));
    }

    @Test
    void testUpdate() {
        when(service.getByIdComment(1)).thenReturn(comment);

        ResponseEntity<?> response = controller.update(comment, 1);
        verify(service).save(any(Comment.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Updated record"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getByIdComment(1)).thenThrow(new NoSuchElementException());

        ResponseEntity<?> response = controller.update(comment, 1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody()
                .equals("The record with the id administrator provided is not found in the database"));
    }

    @Test
    void testDelete() {
        controller.delete(1);
        verify(service).delete(anyInt());
    }
}
