package com.administrator.filmarte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay, Integer> {
   
}
