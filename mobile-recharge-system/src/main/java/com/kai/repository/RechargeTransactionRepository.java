package com.kai.repository;

import com.kai.entity.RechargeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RechargeTransactionRepository extends JpaRepository<RechargeTransaction, Long> {
}