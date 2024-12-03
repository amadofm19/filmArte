package com.administrator.filmarte.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.administrator.filmarte.model.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Integer> {
    List<Reward> findByNameRewardContainingIgnoreCase(String nameReward);
    List<Reward> findByNominationContainingIgnoreCase(String nomination);

    @Query(value = "SELECT * FROM reward WHERE idReward = :idReward", nativeQuery = true)
    Reward getRewardById(@Param("idReward") int idReward);

    @Query(value = "SELECT r FROM Reward r WHERE r.idReward = :idReward")
    Reward getRewardByIdJPQL(@Param("idReward") int idReward);

    @Query(value = "SELECT * FROM reward WHERE name_reward = :nameReward", nativeQuery = true)
    List<Reward> getRewardsByName(@Param("nameReward") String nameReward);

    @Query(value = "SELECT r FROM Reward r WHERE r.nameReward = :nameReward")
    List<Reward> getRewardsByNameJPQL(@Param("nameReward") String nameReward);

    @Query(value = "SELECT * FROM reward WHERE delivery_date = :deliveryDate", nativeQuery = true)
    List<Reward> getRewardsByDeliveryDate(@Param("deliveryDate") Date deliveryDate);

    @Query(value = "SELECT r FROM Reward r WHERE r.deliveryDate = :deliveryDate")
    List<Reward> getRewardsByDeliveryDateJPQL(@Param("deliveryDate") Date deliveryDate);

    @Query(value = "SELECT * FROM reward WHERE nomination = :nomination", nativeQuery = true)
    List<Reward> getRewardsByNomination(@Param("nomination") String nomination);

    @Query(value = "SELECT r FROM Reward r WHERE r.nomination = :nomination")
    List<Reward> getRewardsByNominationJPQL(@Param("nomination") String nomination);

    @Query(value = "SELECT * FROM reward WHERE name_reward LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Reward> getRewardsByNameStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM reward WHERE nomination LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Reward> getRewardsByNominationStartingWith(@Param("letter") String letter);
}