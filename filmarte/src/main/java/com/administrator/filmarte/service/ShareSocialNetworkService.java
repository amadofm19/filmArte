package com.administrator.filmarte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.administrator.filmarte.model.ShareSocialNetwork;
import com.administrator.filmarte.repository.ShareSocialNetworkRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShareSocialNetworkService {
    @Autowired
    private ShareSocialNetworkRepository repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ShareSocialNetwork> getAll() {
        return repo.findAll();
    }

    public List<ShareSocialNetwork> searchByNameNetwork(String nameNetwork) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nameNetwork").regex("^" + nameNetwork));
        return mongoTemplate.find(query, ShareSocialNetwork.class);
    }

    public void add(ShareSocialNetwork resource) {
        repo.save(resource);
    }

    public void update(ShareSocialNetwork resource, String idNetwork) {
        Optional<ShareSocialNetwork> existingResource = repo.findById(idNetwork);
        if (existingResource.isPresent()) {
            // Aquí puedes establecer los valores que deseas actualizar.
            ShareSocialNetwork updatedResource = existingResource.get();
            updatedResource.setNameNetwork(resource.getNameNetwork());
            // Agrega otros campos que deseas actualizar aquí
            repo.save(updatedResource);
        }
    }

    public void delete(String idNetwork) {
        Optional<ShareSocialNetwork> existingResource = repo.findById(idNetwork);
        if (existingResource.isPresent()) {
            repo.deleteById(idNetwork);
        }
    }
}
