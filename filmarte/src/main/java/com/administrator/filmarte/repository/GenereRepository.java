package com.administrator.filmarte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Genre;

@Repository
public interface GenereRepository extends JpaRepository<Genre, Integer> {    
}
