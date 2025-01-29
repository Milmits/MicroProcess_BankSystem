package com.microservice_bank.microservice_bank.repository;

import com.microservice_bank.microservice_bank.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findTopByCurrencyPairOrderByDateDesc(String currencyPair);
    Optional<ExchangeRate> findByCurrencyPairAndDate(String currencyPair, LocalDate date);
}
