package com.microservice_bank.microservice_bank.model;

import java.util.Map;

public class CurrencyApiResponse {

    private String base;  // Базовая валюта (например, USD)
    private Map<String, Double> rates;  // Словарь валют и их курсов относительно базовой валюты

    // Геттеры и сеттеры
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyApiResponse{" +
                "base='" + base + '\'' +
                ", rates=" + rates +
                '}';
    }
}


