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
import com.administrator.filmarte.controller.RewardController;
import com.administrator.filmarte.model.Reward;
import com.administrator.filmarte.service.RewardService;

@ExtendWith(MockitoExtension.class)
public class RewardControllerTest {

    @Mock
    private RewardService service;

    @InjectMocks
    private RewardController controller;

    private Reward reward;

    @BeforeEach
    void setUp() {
        reward = new Reward();
        reward.setIdReward(1);
        reward.setNameReward("Best Film");
        reward.setDeliveryDate(Date.valueOf("2023-01-01"));
        reward.setNomination("Best Director");
    }

    @Test
    void testGetByIdReward() {
        when(service.getByIdReward(1)).thenReturn(reward);

        ResponseEntity<Reward> response = controller.getByIdReward(1);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().getNameReward().equals("Best Film"));
    }

    @Test
    void testRegister() {
        ResponseEntity<String> response = controller.registrar(reward);
        verify(service).save(any(Reward.class));
        assert (response.getStatusCode() == HttpStatus.CREATED);
        assert (response.getBody().equals("Saved record"));
    }

    @Test
    void testUpdate() {
        when(service.getByIdReward(1)).thenReturn(reward);

        ResponseEntity<String> response = controller.update(reward, 1);
        verify(service).save(any(Reward.class));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody().equals("Updated record"));
    }

    @Test
    void testUpdateNotFound() {
        when(service.getByIdReward(1)).thenThrow(new NoSuchElementException("Record not found with the provided ID"));

        ResponseEntity<String> response = controller.update(reward, 1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }

    @Test
    void testDelete() {
        controller.delete(1);
        verify(service).delete(anyInt());
    }

    @Test
    void testDeleteNotFound() {
        doThrow(new NoSuchElementException("Record not found with the provided ID")).when(service).delete(1);

        ResponseEntity<String> response = controller.delete(1);
        assert (response.getStatusCode() == HttpStatus.NOT_FOUND);
        assert (response.getBody().equals("Record not found with the provided ID"));
    }
}
