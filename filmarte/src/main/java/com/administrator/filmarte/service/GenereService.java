package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Genre;
import com.administrator.filmarte.repository.GenereRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GenereService {
    
    @Autowired
    private GenereRepository repo;

    // Obtener todos los géneros
    public List<Genre> getAll() {
        return repo.findAll();
    }

    // Guardar o actualizar un género
    public void save(Genre genero) {
        repo.save(genero);
    }

    // Obtener un género por su ID
    public Genre getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Genere not found"));
    }

    // Eliminar un género por su ID
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
