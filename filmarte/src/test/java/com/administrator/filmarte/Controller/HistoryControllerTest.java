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

import com.administrator.filmarte.controller.HistoryController;
import com.administrator.filmarte.model.History;
import com.administrator.filmarte.service.HistoryService;

@ExtendWith(MockitoExtension.class)
public class HistoryControllerTest {

    @Mock
    private HistoryService service;

    @InjectMocks
    private HistoryController controller;

    private History history;

    @BeforeEach
    void setUp() {
        history = new History();
        history.setIdHistory(1);
        history.setViewingDate("2024-10-21");
        history.setDuration(120);
        history.setGenre("Action");
    }

    @Test
    void testGetAll() {
        List<History> histories = Arrays.asList(history);
        when(service.getAll()).thenReturn(histories);

        List<History> result = controller.getAll();
        assert (result.size() == 1);
        assert (result.get(0).getViewingDate().equals("2024-10-21"));
    }

    @Test
    void testGetById() {
        when(service.getById(1)).thenReturn(history);

        ResponseEntity<History> response = controller.getById(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().getViewingDate().equals("2024-10-21"));
    }

    @Test
    void testRegister() {
        ResponseEntity<String> response = controller.register(history);
        verify(service).save(any(History.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("History registered successfully"));
    }

    @Test
    void testUpdate() {
        when(service.getById(1)).thenReturn(history);

        ResponseEntity<String> response = controller.update(history, 1);
        verify(service).save(any(History.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Record updated successfully"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getById(1)).thenThrow(new NoSuchElementException());

        ResponseEntity<String> response = controller.update(history, 1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }

    @Test
    void testDelete() {
        ResponseEntity<String> response = controller.delete(1);
        verify(service).delete(anyInt());
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("History deleted successfully"));
    }

    @Test
    void testDeleteNotFound() {
        doThrow(new NoSuchElementException()).when(service).delete(1);

        ResponseEntity<String> response = controller.delete(1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }
}
