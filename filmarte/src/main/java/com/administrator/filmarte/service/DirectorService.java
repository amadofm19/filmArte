package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Director;
import com.administrator.filmarte.repository.DirectorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DirectorService {
    
    @Autowired
    private DirectorRepository repo;

    // Obtener todos los directores
    public List<Director> getAll() {
        return repo.findAll();
    }

    // Guardar o actualizar un director
    public void save(Director director) {
        repo.save(director);
    }

    // Obtener un director por su ID
    public Director getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Director not found"));
    }

    // Eliminar un director por su ID
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
