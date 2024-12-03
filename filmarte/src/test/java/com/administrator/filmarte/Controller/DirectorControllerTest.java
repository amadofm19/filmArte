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
import com.administrator.filmarte.controller.DirectorController;
import com.administrator.filmarte.model.Director;
import com.administrator.filmarte.service.DirectorService;

@ExtendWith(MockitoExtension.class)
public class DirectorControllerTest {

    @Mock
    private DirectorService service;

    @InjectMocks
    private DirectorController controller;

    private Director director;

    @BeforeEach
    void setUp() {
        director = new Director();
        director.setIdDirector(1);
        director.setFirstName("Christopher");
        director.setLastNameFather("Nolan");
        director.setLastNameMother("Smith");
    }

    @Test
    void testGetById() {
        when(service.getById(1)).thenReturn(director);

        ResponseEntity<Director> response = controller.getById(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().getFirstName().equals("Christopher"));
    }

    @Test
    void testRegister() {
        ResponseEntity<String> response = controller.register(director);
        verify(service).save(any(Director.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("Director registered successfully"));
    }

    @Test
    void testUpdate() {
        when(service.getById(1)).thenReturn(director);

        ResponseEntity<String> response = controller.update(director, 1);
        verify(service).save(any(Director.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Record updated successfully"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getById(1)).thenThrow(new NoSuchElementException());

        ResponseEntity<String> response = controller.update(director, 1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }

    @Test
    void testDelete() {
        ResponseEntity<String> response = controller.delete(1);
        verify(service).delete(anyInt());
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Director deleted successfully"));
    }

    @Test
    void testDeleteNotFound() {
        doThrow(new NoSuchElementException()).when(service).delete(1);

        ResponseEntity<String> response = controller.delete(1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }
}
