package com.administrator.filmarte.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.administrator.filmarte.model.User;
import com.administrator.filmarte.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> getAll() {
        return repo.findAll();
    }

    public User save(User user) {
        return repo.save(user);
    }

    public User getById(Integer idUser) {
        return repo.findById(idUser).get();
    }

    public void delete(Integer idUser) {
        repo.deleteById(idUser);
    }

    public List<User> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<User> usersPage = repo.findAll(pageReq);
        return usersPage.getContent();
    }

    public List<User> findByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<User> findByMembership(String membership) {
        return repo.findByMembership(membership);
    }
}
