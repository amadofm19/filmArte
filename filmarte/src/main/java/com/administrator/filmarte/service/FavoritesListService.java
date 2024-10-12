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

    // Obtener todas las listas de favoritos
    public List<FavoritesList> getAll() {
        return repo.findAll();
    }

    // Guardar o actualizar una lista de favoritos
    public void save(FavoritesList favoritesList) {
        repo.save(favoritesList);
    }

    // Obtener una lista de favoritos por su ID
    public FavoritesList getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("FavoritesList not found"));
    }

    // Eliminar una lista de favoritos por su ID
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public void setId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}
