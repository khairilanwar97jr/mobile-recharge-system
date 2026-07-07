package com.kai.repository;

import com.kai.entity.RechargeTransaction;
import com.kai.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RechargeTransactionRepository extends JpaRepository<RechargeTransaction, Long> {
	
	List<RechargeTransaction> findByUser(User user);

	
}