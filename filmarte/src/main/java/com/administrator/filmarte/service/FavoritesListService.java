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

    public FavoritesList getByIdFavoritesList(Integer idFavoritesList) {
        return repo.findById(idFavoritesList).orElseThrow(() -> new NoSuchElementException("FavoritesList not found"));
    }

    public void delete(Integer idFavoritesList) {
        repo.deleteById(idFavoritesList);
    }

    public void setIdFavoritesList(int idFavoritesList) {
        throw new UnsupportedOperationException("Unimplemented method 'setIdFavoritesList'");
    }

    public FavoritesList getById(Integer idFavoritesList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
}
