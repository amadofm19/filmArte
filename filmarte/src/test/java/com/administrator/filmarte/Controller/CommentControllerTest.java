package com.administrator.filmarte.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    }

    @Test
    void testGetByIdComment() {
        when(service.getById(1)).thenReturn(comment);
        ResponseEntity<?> response = controller.getById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jane Doe", ((Comment) response.getBody()).getAuthor());
    }

    @Test
    void testRegister() {
        when(service.analyzeSentiment(comment.getContent())).thenReturn("Positive");
        ResponseEntity<?> response = controller.registrar(comment);
        verify(service).save(any(Comment.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(((Comment) response.getBody()).getAnalysis().equals("Positive"));
    }

    @Test
    void testUpdate() {
        when(service.getById(1)).thenReturn(comment);
        ResponseEntity<?> response = controller.update(comment, 1);
        verify(service).save(any(Comment.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated record", response.getBody());
    }

    @Test
    void testDelete() {
        ResponseEntity<?> response = controller.delete(1);
        verify(service).delete(anyInt());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted record", response.getBody());
    }
}
