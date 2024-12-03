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
import com.administrator.filmarte.controller.FavoritesListController;
import com.administrator.filmarte.model.FavoritesList;
import com.administrator.filmarte.service.FavoritesListService;

@ExtendWith(MockitoExtension.class)
public class FavoritesListControllerTest {

    @Mock
    private FavoritesListService service;

    @InjectMocks
    private FavoritesListController controller;

    private FavoritesList favoritesList;

    @BeforeEach
    void setUp() {
        favoritesList = new FavoritesList();
        favoritesList.setIdFavoritesList(1);
        favoritesList.setMovieTitle("Inception");
        favoritesList.setDescription("A mind-bending thriller.");
        favoritesList.setGenere("Sci-Fi");
        favoritesList.setViewingStatus("Watched");
    }

    @Test
    void testGetById() {
        when(service.getById(1)).thenReturn(favoritesList);

        ResponseEntity<FavoritesList> response = controller.getById(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().getMovieTitle().equals("Inception"));
    }

    @Test
    void testRegister() {
        ResponseEntity<String> response = controller.register(favoritesList);
        verify(service).save(any(FavoritesList.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("Favorites list registered successfully"));
    }

    @Test
    void testUpdate() {
        when(service.getById(1)).thenReturn(favoritesList);

        ResponseEntity<String> response = controller.update(favoritesList, 1);
        verify(service).save(any(FavoritesList.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Record updated successfully"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getById(1)).thenThrow(new NoSuchElementException());

        ResponseEntity<String> response = controller.update(favoritesList, 1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }

    @Test
    void testDelete() {
        ResponseEntity<String> response = controller.delete(1);
        verify(service).delete(anyInt());
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Favorites list deleted successfully"));
    }

    @Test
    void testDeleteNotFound() {
        doThrow(new NoSuchElementException()).when(service).delete(1);

        ResponseEntity<String> response = controller.delete(1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }
}
