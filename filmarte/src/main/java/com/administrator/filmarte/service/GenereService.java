package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.administrator.filmarte.model.Genere;
import com.administrator.filmarte.repository.GenereRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class GenereService {

    @Autowired
    private GenereRepository repo;

    public List<Genere> getAll() {
        return repo.findAll();
    }

    public void save(Genere genero) {
        repo.save(genero);
    }

    public Genere getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Genere not found"));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Genere> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Genere> generePage = repo.findAll(pageReq);
        return generePage.getContent();
    }
}
