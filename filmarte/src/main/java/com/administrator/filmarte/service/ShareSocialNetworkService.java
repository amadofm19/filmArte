package com.administrator.filmarte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<ShareSocialNetwork> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<ShareSocialNetwork> socialNetworksPage = repo.findAll(pageReq);
        return socialNetworksPage.getContent();
    }

    public List<ShareSocialNetwork> searchByNameNetwork(String nameNetwork) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nameNetwork").regex("^" + nameNetwork));
        return mongoTemplate.find(query, ShareSocialNetwork.class);
    }

    public List<ShareSocialNetwork> searchByNameNetwork(String nameNetwork, int page, int pageSize) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nameNetwork").regex("^" + nameNetwork));
        query.with(PageRequest.of(page, pageSize));
        return mongoTemplate.find(query, ShareSocialNetwork.class);
    }

    public void add(ShareSocialNetwork resource) {
        repo.save(resource);
    }

    public void update(ShareSocialNetwork resource, String idNetwork) {
        Optional<ShareSocialNetwork> existingResource = repo.findById(idNetwork);
        if (existingResource.isPresent()) {
            ShareSocialNetwork updatedResource = existingResource.get();
            updatedResource.setNameNetwork(resource.getNameNetwork());
            repo.save(updatedResource);
        }
    }

    public void delete(String idNetwork) {
        Optional<ShareSocialNetwork> existingResource = repo.findById(idNetwork);
        if (existingResource.isPresent()) {
            repo.deleteById(idNetwork);
        }
    }

    public List<ShareSocialNetwork> searchByUrl(String url) {
        return ShareSocialNetworkRepository.findByUrlContainingIgnoreCase(url);
    }
}
