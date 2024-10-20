/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administrator.filmarte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.Category;
import com.administrator.filmarte.repository.CategoryRepository;

import jakarta.transaction.Transactional;

/**
 *
 * @author ARACELI
 */
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
}
