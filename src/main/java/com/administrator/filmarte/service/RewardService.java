package com.administrator.filmarte.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.administrator.filmarte.model.Reward;
import com.administrator.filmarte.repository.RewardRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.transaction.Transactional;

@Service
@Transactional
@Schema(description = "Service class for managing rewards.")
public class RewardService {

    @Autowired
    private RewardRepository repo;

    public List<Reward> getAll() {
        return repo.findAll();
    }

    public void save(Reward reward) {
        repo.save(reward);
    }

    public Reward getByIdReward(Integer idReward) {
        return repo.findById(idReward).get();
    }

    public void delete(Integer idReward) {
        repo.deleteById(idReward);
    }

    public List<Reward> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Reward> generePage = repo.findAll(pageReq);
        return generePage.getContent();
    }

    public List<Reward> findByNameReward(String nameReward) {
        return repo.findByNameRewardContainingIgnoreCase(nameReward);
    }

    public List<Reward> findByNomination(String nomination) {
        return repo.findByNominationContainingIgnoreCase(nomination);
    }
}