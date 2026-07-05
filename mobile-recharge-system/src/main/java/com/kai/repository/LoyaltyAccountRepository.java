package com.kai.repository;

import com.kai.entity.LoyaltyAccount;
import com.kai.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyAccountRepository extends JpaRepository<LoyaltyAccount, Long> {
	
	
	 Optional<LoyaltyAccount> findByUser(User user);
}