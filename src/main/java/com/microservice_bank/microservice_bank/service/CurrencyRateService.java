package com.microservice_bank.microservice_bank.service;

import com.microservice_bank.microservice_bank.model.CurrencyRate;
import com.microservice_bank.microservice_bank.model.CurrencyApiResponse;
import com.microservice_bank.microservice_bank.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRateService {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD"; // Пример API

    // Метод для получения и сохранения курсов валют
    public void fetchAndSaveCurrencyRates() {
        RestTemplate restTemplate = new RestTemplate();
        // Выполняем запрос к API
        CurrencyApiResponse response = restTemplate.getForObject(API_URL, CurrencyApiResponse.class);

        // Преобразуем и сохраняем курсы валют в базу
        if (response != null && response.getRates() != null) {
            response.getRates().forEach((currency, rate) -> {
                CurrencyRate currencyRate = new CurrencyRate(currency, rate);
                currencyRateRepository.save(currencyRate);
            });
        }
    }

    // Получение курса валюты по названию
    public Double getCurrencyRate(String currency) {
        return currencyRateRepository.findByCurrency(currency)
                .map(CurrencyRate::getRate)
                .orElseThrow(() -> new RuntimeException("Курс валюты не найден: " + currency));
    }
}

