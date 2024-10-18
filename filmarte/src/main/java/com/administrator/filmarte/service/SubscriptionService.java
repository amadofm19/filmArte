/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Subscription;
import com.administrator.filmarte.repository.SubscriptionRepository;

import jakarta.transaction.Transactional;

/**
 *
 * @author ARACELI
 */
@Service
@Transactional
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository repo;

    public List<Subscription> getAll() {
        return repo.findAll();
    }

    public void save(Subscription subscription) {
        repo.save(subscription);
    }

    public Subscription getById(Integer idSubscription) {
        return repo.findById(idSubscription).get();
    }

    public void delete(Integer idSubscription) {
        repo.deleteById(idSubscription);
    }

    public List<Subscription> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Subscription> subscriptionsPage = repo.findAll(pageReq);
        return subscriptionsPage.getContent();
    }
}
