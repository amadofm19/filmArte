package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.administrator.filmarte.model.Movie;
import com.administrator.filmarte.repository.MovieRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository repo;

    public List<Movie> getAll() {
        return repo.findAll();
    }

    public void save(Movie movie) {
        repo.save(movie);
    }

    public Movie getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Movie not found"));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Movie> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Movie> moviesPage = repo.findAll(pageReq);
        return moviesPage.getContent();
    }

    public List<Movie> findByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> findByYear(int year) {
        return repo.findByYear(year);
    }

    public List<Movie> findByTitleAndYear(String title, int year) {
        return repo.findByTitleContainingIgnoreCaseAndYear(title, year);
    }
}