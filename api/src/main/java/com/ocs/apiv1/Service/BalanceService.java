
package com.ocs.apiv1.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocs.apiv1.Models.Balance;
import com.ocs.apiv1.Repository.BalanceRepository;

@Service

@Transactional
public class BalanceService {

    @Autowired
    private BalanceRepository repo;

    public List<Balance> listAll() {
        return repo.findAll();
    }

    public void save(Balance balance) {
        repo.save(balance);
    }

    public Balance get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Balance> getBalancesBySubscriberId(Integer subscriberId) {
        return repo.findBySubscriberId(subscriberId);
    }
}
