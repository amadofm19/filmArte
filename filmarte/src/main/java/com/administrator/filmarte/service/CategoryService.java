package com.administrator.filmarte.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.administrator.filmarte.model.Category;
import com.administrator.filmarte.repository.CategoryRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public List<Category> getAll() {
        return repo.findAll();
    }

    public void save(Category category) {
        repo.save(category);
    }

    public Category getById(Integer idCategory) {
        return repo.findById(idCategory).get();
    }

    public void delete(Integer idCategory) {
        repo.deleteById(idCategory);
    }

    public List<Category> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Category> categoriesPage = repo.findAll(pageReq);
        return categoriesPage.getContent();
    }

    public List<Category> findByCategoryType(String categoryType) {
        return repo.findByCategoryType(categoryType);
    }

    public List<Category> findByDescription(String description) {
        return repo.findByDescriptionContaining(description);
    }
}
