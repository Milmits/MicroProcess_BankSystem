package com.microservice_bank.microservice_bank.service;

import com.microservice_bank.microservice_bank.client.AlphaVantageClient;
import com.microservice_bank.microservice_bank.model.ExchangeRate;
import com.microservice_bank.microservice_bank.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final AlphaVantageClient alphaVantageClient;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, AlphaVantageClient alphaVantageClient) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.alphaVantageClient = alphaVantageClient;
    }

    public Double getExchangeRate(String currencyPair) {
        LocalDate today = LocalDate.now();

        Optional<ExchangeRate> todayRate = exchangeRateRepository.findByCurrencyPairAndDate(currencyPair, today);
        if (todayRate.isPresent()) {
            return todayRate.get().getExchangeRate();
        }

        Optional<ExchangeRate> lastKnownRate = exchangeRateRepository.findTopByCurrencyPairOrderByDateDesc(currencyPair);
        if (lastKnownRate.isPresent()) {
            return lastKnownRate.get().getExchangeRate();
        }

        Double fetchedRate = alphaVantageClient.getExchangeRate(currencyPair);
        exchangeRateRepository.save(new ExchangeRate(currencyPair, fetchedRate, today));

        return fetchedRate;
    }
}
