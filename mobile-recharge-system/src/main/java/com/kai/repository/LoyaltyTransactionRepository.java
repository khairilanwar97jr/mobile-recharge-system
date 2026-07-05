package com.kai.repository;

import com.kai.entity.LoyaltyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyTransactionRepository extends JpaRepository<LoyaltyTransaction, Long> {
}