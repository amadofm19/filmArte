package com.administrator.filmarte.Controller;

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
import com.administrator.filmarte.controller.MovieController;
import com.administrator.filmarte.model.Movie;
import com.administrator.filmarte.service.MovieService;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    @Mock
    private MovieService service;

    @InjectMocks
    private MovieController controller;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setIdMovie(1);
        movie.setTitle("Inception");
        movie.setYear(2010);
        movie.setDescription("A thief who steals corporate secrets through the use of dream-sharing technology.");
    }

    @Test
    void testGetById() {
        when(service.getById(1)).thenReturn(movie);

        ResponseEntity<Movie> response = controller.getById(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().getTitle().equals("Inception"));
    }

    @Test
    void testRegister() {
        ResponseEntity<String> response = controller.register(movie);
        verify(service).save(any(Movie.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("Movie registered successfully"));
    }

    @Test
    void testUpdate() {
        when(service.getById(1)).thenReturn(movie);

        ResponseEntity<String> response = controller.update(movie, 1);
        verify(service).save(any(Movie.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Record updated successfully"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getById(1)).thenThrow(new NoSuchElementException());

        ResponseEntity<String> response = controller.update(movie, 1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }

    @Test
    void testDelete() {
        ResponseEntity<String> response = controller.delete(1);
        verify(service).delete(anyInt());
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Movie deleted successfully"));
    }

    @Test
    void testDeleteNotFound() {
        doThrow(new NoSuchElementException()).when(service).delete(1);

        ResponseEntity<String> response = controller.delete(1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }
}
