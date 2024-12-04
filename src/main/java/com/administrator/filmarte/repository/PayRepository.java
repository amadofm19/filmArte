package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay, Integer> {
    List<Pay> findByPaymentMethod(String paymentMethod);
    List<Pay> findByCurrency(String currency);
    
    @Query(value = "SELECT * FROM pay WHERE idPay = :idPay", nativeQuery = true)
    Pay getPayById(@Param("idPay") int idPay);

    @Query(value = "SELECT p FROM Pay p WHERE p.idPay = :idPay")
    Pay getPayByIdJPQL(@Param("idPay") int idPay);

    @Query(value = "SELECT * FROM pay WHERE amount = :amount", nativeQuery = true)
    List<Pay> getPaysByAmount(@Param("amount") float amount);

    @Query(value = "SELECT p FROM Pay p WHERE p.amount = :amount")
    List<Pay> getPaysByAmountJPQL(@Param("amount") float amount);

    @Query(value = "SELECT * FROM pay WHERE currency = :currency", nativeQuery = true)
    List<Pay> getPaysByCurrency(@Param("currency") String currency);

    @Query(value = "SELECT p FROM Pay p WHERE p.currency = :currency")
    List<Pay> getPaysByCurrencyJPQL(@Param("currency") String currency);

    @Query(value = "SELECT * FROM pay WHERE payment_method = :paymentMethod", nativeQuery = true)
    List<Pay> getPaysByPaymentMethod(@Param("paymentMethod") String paymentMethod);

    @Query(value = "SELECT p FROM Pay p WHERE p.paymentMethod = :paymentMethod")
    List<Pay> getPaysByPaymentMethodJPQL(@Param("paymentMethod") String paymentMethod);

    @Query(value = "SELECT * FROM pay WHERE payment_method LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Pay> getPaysByPaymentMethodStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM pay WHERE currency LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Pay> getPaysByCurrencyStartingWith(@Param("letter") String letter);
}
