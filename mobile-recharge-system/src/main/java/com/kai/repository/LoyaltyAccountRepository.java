package com.kai.repository;

import com.kai.entity.LoyaltyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyAccountRepository extends JpaRepository<LoyaltyAccount, Long> {
}