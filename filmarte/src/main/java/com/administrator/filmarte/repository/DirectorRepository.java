package com.administrator.filmarte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {    
}

