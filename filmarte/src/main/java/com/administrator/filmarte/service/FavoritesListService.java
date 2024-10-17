package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.administrator.filmarte.model.FavoritesList;
import com.administrator.filmarte.repository.FavoritesListRepository;

@Service
@Transactional
public class FavoritesListService {

    @Autowired
    private FavoritesListRepository repo;

    public List<FavoritesList> getAll() {
        return repo.findAll();
    }

    public void save(FavoritesList favoritesList) {
        repo.save(favoritesList);
    }

    public FavoritesList getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("FavoritesList not found"));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public void setId(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}
