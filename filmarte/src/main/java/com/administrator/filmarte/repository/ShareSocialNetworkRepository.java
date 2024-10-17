package com.administrator.filmarte.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.ShareSocialNetwork;

@Repository
public interface ShareSocialNetworkRepository extends MongoRepository<ShareSocialNetwork, String> {
    
}
