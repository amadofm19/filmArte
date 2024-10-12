package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.History;
import com.administrator.filmarte.repository.HistoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistoryService {

    @Autowired
    private HistoryRepository repo;

    // Obtener todo el historial
    public List<History> getAll() {
        return repo.findAll();
    }

    // Guardar o actualizar una entrada en el historial
    public void save(History history) {
        repo.save(history);
    }

    // Obtener una entrada de historial por su ID
    public History getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("History record not found"));
    }

    // Eliminar una entrada de historial por su ID
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
