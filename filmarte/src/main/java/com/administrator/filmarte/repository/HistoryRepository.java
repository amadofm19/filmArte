package com.administrator.filmarte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {    
}
