package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Movie;
import com.administrator.filmarte.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovieService {
    
    @Autowired
    private MovieRepository repo;

    // Obtener todas las películas
    public List<Movie> getAll() {
        return repo.findAll();
    }

    // Guardar o actualizar una película
    public void save(Movie movie) {
        repo.save(movie);
    }

    // Obtener una película por su ID
    public Movie getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Movie not found"));
    }

    // Eliminar una película por su ID
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
