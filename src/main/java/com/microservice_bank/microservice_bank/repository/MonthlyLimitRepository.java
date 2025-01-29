package com.microservice_bank.microservice_bank.repository;

import com.microservice_bank.microservice_bank.model.MonthlyLimit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonthlyLimitRepository extends JpaRepository<MonthlyLimit, Long> {
    Optional<MonthlyLimit> findByUserId(Long userId);
}


