package com.kai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kai.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}