package com.administrator.filmarte.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.administrator.filmarte.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    List<Subscription> findByMembershipType(String membershipType);
    List<Subscription> findByCost(double cost);
    List<Subscription> findByDuration(int duration);
    List<Subscription> findByPaymentMethod(String paymentMethod);

    @Query(value = "SELECT * FROM subscription WHERE idSubscription = :idSubscription", nativeQuery = true)
    Subscription getSubscriptionById(@Param("idSubscription") int idSubscription);

    @Query(value = "SELECT s FROM Subscription s WHERE s.idSubscription = :idSubscription")
    Subscription getSubscriptionByIdJPQL(@Param("idSubscription") int idSubscription);

    @Query(value = "SELECT * FROM subscription WHERE membership_type = :membershipType", nativeQuery = true)
    List<Subscription> getSubscriptionsByMembershipType(@Param("membershipType") String membershipType);

    @Query(value = "SELECT s FROM Subscription s WHERE s.membershipType = :membershipType")
    List<Subscription> getSubscriptionsByMembershipTypeJPQL(@Param("membershipType") String membershipType);

    @Query(value = "SELECT * FROM subscription WHERE cost = :cost", nativeQuery = true)
    List<Subscription> getSubscriptionsByCost(@Param("cost") double cost);

    @Query(value = "SELECT s FROM Subscription s WHERE s.cost = :cost")
    List<Subscription> getSubscriptionsByCostJPQL(@Param("cost") double cost);

    @Query(value = "SELECT * FROM subscription WHERE duration = :duration", nativeQuery = true)
    List<Subscription> getSubscriptionsByDuration(@Param("duration") int duration);

    @Query(value = "SELECT s FROM Subscription s WHERE s.duration = :duration")
    List<Subscription> getSubscriptionsByDurationJPQL(@Param("duration") int duration);

    @Query(value = "SELECT * FROM subscription WHERE payment_method = :paymentMethod", nativeQuery = true)
    List<Subscription> getSubscriptionsByPaymentMethod(@Param("paymentMethod") String paymentMethod);

    @Query(value = "SELECT s FROM Subscription s WHERE s.paymentMethod = :paymentMethod")
    List<Subscription> getSubscriptionsByPaymentMethodJPQL(@Param("paymentMethod") String paymentMethod);

    @Query(value = "SELECT * FROM subscription WHERE start_date = :startDate", nativeQuery = true)
    List<Subscription> getSubscriptionsByStartDate(@Param("startDate") Date startDate);

    @Query(value = "SELECT s FROM Subscription s WHERE s.startDate = :startDate")
    List<Subscription> getSubscriptionsByStartDateJPQL(@Param("startDate") Date startDate);

    @Query(value = "SELECT * FROM subscription WHERE renewal_date = :renewalDate", nativeQuery = true)
    List<Subscription> getSubscriptionsByRenewalDate(@Param("renewalDate") Date renewalDate);

    @Query(value = "SELECT s FROM Subscription s WHERE s.renewalDate = :renewalDate")
    List<Subscription> getSubscriptionsByRenewalDateJPQL(@Param("renewalDate") Date renewalDate);

    @Query(value = "SELECT * FROM subscription WHERE membership_type LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Subscription> getSubscriptionsByMembershipTypeStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM subscription WHERE payment_method LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Subscription> getSubscriptionsByPaymentMethodStartingWith(@Param("letter") String letter);
}