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

import com.administrator.filmarte.controller.UserController;
import com.administrator.filmarte.model.User;
import com.administrator.filmarte.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setIdUser(1);
        user.setName("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setMembership("Premium");
        user.setUsername("johndoe");
        user.setPassword("password123");
    }

    @Test
    void testGetAll() {
        List<User> users = Arrays.asList(user);
        when(service.getAll()).thenReturn(users);

        List<User> result = controller.getAll();
        assert (result.size() == 1);
        assert (result.get(0).getName().equals("John"));
    }

    @Test
    void testGetById() {
        when(service.getById(1)).thenReturn(user);

        ResponseEntity<User> response = controller.getById(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().getName().equals("John"));
    }

    @Test
    void testRegister() {
        ResponseEntity<String> response = (ResponseEntity<String>) controller.registrar(user);
        verify(service).save(any(User.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("Saved record"));
    }

    @Test
    void testUpdate() {
        when(service.getById(1)).thenReturn(user);

        ResponseEntity<String> response = controller.update(user, 1);
        verify(service).save(any(User.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Updated record"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getById(1)).thenThrow(new NoSuchElementException());

        ResponseEntity<String> response = controller.update(user, 1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }

    @Test
    void testDelete() {
        ResponseEntity<String> response = controller.delete(1);
        verify(service).delete(anyInt());
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Deleted record"));
    }

    @Test
    void testDeleteNotFound() {
        doThrow(new NoSuchElementException()).when(service).delete(1);

        ResponseEntity<String> response = controller.delete(1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }
}
