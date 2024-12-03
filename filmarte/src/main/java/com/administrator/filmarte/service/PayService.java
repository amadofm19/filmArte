package com.administrator.filmarte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.administrator.filmarte.model.Pay;
import com.administrator.filmarte.repository.PayRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.transaction.Transactional;

@Service
@Transactional
@Schema(description = "Service class for managing payments.")
public class PayService {

    @Autowired
    private PayRepository repo;

    public List<Pay> getAll() {
        return repo.findAll();
    }

    public List<Pay> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Pay> pays = repo.findAll(pageReq);
        return pays.getContent();
    }

    public void save(Pay pay) {
        repo.save(pay);
    }

    public Pay getByIdPay(Integer idPay) {
        return repo.findById(idPay).get();
    }

    public void delete(Integer idPay) {
        repo.deleteById(idPay);
    }

    public List<Pay> findByPaymentMethod(String paymentMethod) {
        return repo.findByPaymentMethod(paymentMethod);
    }
    
    public List<Pay> findByCurrency(String currency) {
        return repo.findByCurrency(currency);
    }
 
}