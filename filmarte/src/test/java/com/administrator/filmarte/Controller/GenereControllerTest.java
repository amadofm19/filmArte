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
import com.administrator.filmarte.controller.GenereController;
import com.administrator.filmarte.model.Genere;
import com.administrator.filmarte.service.GenereService;

@ExtendWith(MockitoExtension.class)
public class GenereControllerTest {

    @Mock
    private GenereService service;

    @InjectMocks
    private GenereController controller;

    private Genere genere;

    @BeforeEach
    void setUp() {
        genere = new Genere();
        genere.setIdGenere(1);
        genere.setName("Action");
        genere.setDescription("Fast-paced action movies.");
    }


    @Test
    void testGetById() {
        when(service.getById(1)).thenReturn(genere);

        ResponseEntity<Genere> response = controller.getById(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().getName().equals("Action"));
    }

    @Test
    void testRegister() {
        ResponseEntity<String> response = controller.register(genere);
        verify(service).save(any(Genere.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("Genere registered successfully"));
    }

    @Test
    void testUpdate() {
        when(service.getById(1)).thenReturn(genere);

        ResponseEntity<String> response = controller.update(genere, 1);
        verify(service).save(any(Genere.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Record updated successfully"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getById(1)).thenThrow(new NoSuchElementException());

        ResponseEntity<String> response = controller.update(genere, 1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }

    @Test
    void testDelete() {
        ResponseEntity<String> response = controller.delete(1);
        verify(service).delete(anyInt());
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Genere deleted successfully"));
    }

    @Test
    void testDeleteNotFound() {
        doThrow(new NoSuchElementException()).when(service).delete(1);

        ResponseEntity<String> response = controller.delete(1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }
}
