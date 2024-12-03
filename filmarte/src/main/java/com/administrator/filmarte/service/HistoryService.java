package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.administrator.filmarte.model.History;
import com.administrator.filmarte.repository.HistoryRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistoryService {

    @Autowired
    private HistoryRepository repo;

    public List<History> getAll() {
        return repo.findAll();
    }

    public void save(History history) {
        repo.save(history);
    }

    public History getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("History record not found"));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<History> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<History> historysPage = repo.findAll(pageReq);
        return historysPage.getContent();
    }

    public List<History> findByViewingDate(String viewingDate) {
        return repo.findByViewingDate(viewingDate);
    }

    public List<History> findByDuration(int duration) {
        return repo.findByDuration(duration);
    }

    public List<History> findByGenere(String genere) {
        return repo.findByGenereContainingIgnoreCase(genere);
    }
}