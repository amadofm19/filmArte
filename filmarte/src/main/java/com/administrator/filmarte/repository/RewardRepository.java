package com.administrator.filmarte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.administrator.filmarte.model.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Integer> {
   
}