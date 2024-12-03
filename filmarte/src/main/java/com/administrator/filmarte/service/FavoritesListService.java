package com.administrator.filmarte.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<FavoritesList> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<FavoritesList> favoriteslistPage = repo.findAll(pageReq);
        return favoriteslistPage.getContent();
    }

    public List<FavoritesList> findByTitle(String title) {
        return repo.findByMovieTitleContainingIgnoreCase(title);
    }

    public List<FavoritesList> findByGenere(String genere) {
        return repo.findByGenereContainingIgnoreCase(genere);
    }

    public List<FavoritesList> findByViewingStatus(String status) {
        return repo.findByViewingStatusContainingIgnoreCase(status);
    }
}