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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.administrator.filmarte.controller.GenereController;
import com.administrator.filmarte.model.Genre;
import com.administrator.filmarte.service.GenereService;

@ExtendWith(MockitoExtension.class)
public class GenereControllerTest {

    @Mock
    private GenereService service;

    @InjectMocks
    private GenereController controller;

    private Genre genre;

    @BeforeEach
    void setUp() {
        genre = new Genre();
        genre.setIdGenre(1);
        genre.setName("Action");
        genre.setDescription("Fast-paced action movies.");
    }

    @Test
    void testGetAll() {
        List<Genre> genres = Arrays.asList(genre);
        when(service.getAll()).thenReturn(genres);

        List<Genre> result = controller.getAll();
        assert (result.size() == 1);
        assert (result.get(0).getName().equals("Action"));
    }

    @Test
    void testGetById() {
        when(service.getById(1)).thenReturn(genre);

        ResponseEntity<Genre> response = controller.getById(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().getName().equals("Action"));
    }

    @Test
    void testRegister() {
        ResponseEntity<String> response = controller.register(genre);
        verify(service).save(any(Genre.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("Genero registered successfully"));
    }

    @Test
    void testUpdate() {
        when(service.getById(1)).thenReturn(genre);

        ResponseEntity<String> response = controller.update(genre, 1);
        verify(service).save(any(Genre.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Record updated successfully"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getById(1)).thenThrow(new NoSuchElementException());

        ResponseEntity<String> response = controller.update(genre, 1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }

    @Test
    void testDelete() {
        ResponseEntity<String> response = controller.delete(1);
        verify(service).delete(anyInt());
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Genero deleted successfully"));
    }

    @Test
    void testDeleteNotFound() {
        doThrow(new NoSuchElementException()).when(service).delete(1);

        ResponseEntity<String> response = controller.delete(1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }
}
