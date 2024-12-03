package com.administrator.filmarte.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.administrator.filmarte.model.Actor;
import com.administrator.filmarte.repository.ActorRepository;
import jakarta.transaction.Transactional; 

@Service
@Transactional
public class ActorService {
    @Autowired
    private ActorRepository repo;

    public List<Actor> getAll() {
        return repo.findAll();
    }

    public void save(Actor actor) {
        repo.save(actor);
    }

    public Actor getById(Integer idActor) {
        return repo.findById(idActor).orElse(null);
    }

    public void delete(Integer idActor) {
        repo.deleteById(idActor);
    }

    public List<Actor> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize); 
        Page<Actor> actorsPage = repo.findAll(pageReq); 
        return actorsPage.getContent(); 
    }

    public List<Actor> findByFirstName(String firstName) {
        return repo.findByFirstNameContainingIgnoreCase(firstName);
    }
    
    public List<Actor> findByLastName(String lastName) {
        return repo.findByLastNameContainingIgnoreCase(lastName);
    }
    
    public List<Actor> findByDescription(String description) {
        return repo.findByDescriptionContainingIgnoreCase(description);
    }
}
