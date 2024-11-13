package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Genre;
import com.administrator.filmarte.repository.GenereRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GenereService {

    @Autowired
    private GenereRepository repo;

    public List<Genre> getAll() {
        return repo.findAll();
    }

    public void save(Genre genero) {
        repo.save(genero);
    }

    public Genre getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Genere not found"));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Genre> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Genre> genrePage = repo.findAll(pageReq);
        return genrePage.getContent();
    }

    
}
