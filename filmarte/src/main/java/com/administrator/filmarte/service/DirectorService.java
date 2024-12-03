package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.administrator.filmarte.model.Director;
import com.administrator.filmarte.repository.DirectorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DirectorService {
    
    @Autowired
    private DirectorRepository repo;

    public List<Director> getAll() {
        return repo.findAll();
    }

    public void save(Director director) {
        repo.save(director);
    }

    public Director getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Director not found"));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Director> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Director> directorPage = repo.findAll(pageReq);
        return directorPage.getContent();
    }

    public List<Director> findByFirstName(String firstName) {
        return repo.findByFirstName(firstName);
    }

    public List<Director> findByLastNameFather(String lastNameFather) {
        return repo.findByLastNameFather(lastNameFather);
    }

    public List<Director> findByLastNameMother(String lastNameMother) {
        return repo.findByLastNameMother(lastNameMother);
    }
}