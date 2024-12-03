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
import com.administrator.filmarte.controller.SubscriptionController;
import com.administrator.filmarte.model.Subscription;
import com.administrator.filmarte.service.SubscriptionService;

@ExtendWith(MockitoExtension.class)
public class SubscriptionControllerTest {

    @Mock
    private SubscriptionService service;

    @InjectMocks
    private SubscriptionController controller;

    private Subscription subscription;

    @BeforeEach
    void setUp() {
        subscription = new Subscription();
        subscription.setIdSubscription(1);
        subscription.setMembershipType("Premium");
        subscription.setCost(29.99);
        subscription.setDuration(12);
        subscription.setPaymentMethod("Credit Card");
    }

    @Test
    void testGetById() {
        when(service.getById(1)).thenReturn(subscription);

        ResponseEntity<Subscription> response = controller.getById(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().getMembershipType().equals("Premium"));
    }

    @Test
    void testRegister() {
        ResponseEntity<String> response = controller.registrar(subscription);
        verify(service).save(any(Subscription.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("Saved record"));
    }

    @Test
    void testUpdate() {
        when(service.getById(1)).thenReturn(subscription);

        ResponseEntity<String> response = controller.update(subscription, 1);
        verify(service).save(any(Subscription.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Updated record"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getById(1)).thenThrow(new NoSuchElementException());

        ResponseEntity<String> response = controller.update(subscription, 1);
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
