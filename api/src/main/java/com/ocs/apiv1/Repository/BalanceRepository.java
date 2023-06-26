package com.ocs.apiv1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocs.apiv1.Models.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Object> {
    List<Balance> findBySubscriberId(Integer subscriberId);
}
