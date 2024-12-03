package com.administrator.filmarte.Controller;

import java.sql.Date;

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
import com.administrator.filmarte.controller.PayController;
import com.administrator.filmarte.model.Pay;
import com.administrator.filmarte.service.PayService;

@ExtendWith(MockitoExtension.class)
public class PayControllerTest {

    @Mock
    private PayService service;

    @InjectMocks
    private PayController controller;

    private Pay pay;

    @BeforeEach
    void setUp() {
        pay = new Pay();
        pay.setIdPay(1);
        pay.setAmount(99.99f);
        pay.setCurrency("USD");
        pay.setPaymentDay(Date.valueOf("2023-01-01"));
        pay.setPaymentMethod("Credit Card");
    }

    @Test
    void testGetByIdPay() {
        when(service.getByIdPay(1)).thenReturn(pay);

        ResponseEntity<Pay> response = controller.getByidPay(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().getAmount() == 99.99f);
    }

    @Test
    void testRegister() {
        ResponseEntity<String> response = controller.registrar(pay);
        verify(service).save(any(Pay.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("Saved record"));
    }

    @Test
    void testUpdate() {
        when(service.getByIdPay(1)).thenReturn(pay);

        ResponseEntity<String> response = controller.update(pay, 1);
        verify(service).save(any(Pay.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Updated record"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getByIdPay(1)).thenThrow(new NoSuchElementException());

        ResponseEntity<String> response = controller.update(pay, 1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody()
                .equals("The record with the id administrator provided is not found in the database"));
    }

    @Test
    void testDelete() {
        ResponseEntity<String> response = controller.delete(1);
        verify(service).delete(anyInt());
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Pay deleted successfully"));
    }

    @Test
    void testDeleteNotFound() {
        doThrow(new NoSuchElementException()).when(service).delete(1);

        ResponseEntity<String> response = controller.delete(1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody()
                .equals("The record with the id administrator provided is not found in the database"));
    }
}
