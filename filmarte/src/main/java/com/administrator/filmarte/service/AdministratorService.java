package com.administrator.filmarte.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.administrator.filmarte.model.Administrator;
import com.administrator.filmarte.repository.AdministratorRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.transaction.Transactional;

@Service
@Transactional
@Schema(description = "Service class for managing administrators.")
public class AdministratorService {

    @Autowired
    private AdministratorRepository repo;

    public List<Administrator> getAll() {
        return repo.findAll();
    }

    public List<Administrator> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Administrator> administrators = repo.findAll(pageReq);
        return administrators.getContent();
    }

    public void save(Administrator administrator) {
        repo.save(administrator);
    }

    public Administrator getByIAdministrator(Integer idAdministrator) {
        return repo.findById(idAdministrator).get();
    }

    public void delete(Integer idAdministrator) {
        repo.deleteById(idAdministrator);
    }

    public List<Administrator> findByName(String name) {
        return repo.findByName(name);
    }

    public List<Administrator> findByLastname(String lastname) {
        return repo.findByLastname(lastname);
    }

    public List<Administrator> findByPassword(String password) {
        return repo.findByPassword(password);
    }
}