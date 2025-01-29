package com.microservice_bank.microservice_bank.repository;

import com.microservice_bank.microservice_bank.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    // Найти курс для заданной валюты
    Optional<CurrencyRate> findByCurrency(String currency);
}
