package com.administrator.filmarte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {    
}
