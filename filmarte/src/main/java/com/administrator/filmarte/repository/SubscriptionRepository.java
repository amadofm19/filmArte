/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.administrator.filmarte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Subscription;

/**
 *
 * @author ARACELI
 */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

}