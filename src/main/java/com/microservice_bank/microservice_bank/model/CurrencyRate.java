package com.microservice_bank.microservice_bank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "currency_rates")
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;  // например, USD, EUR, GBP

    @Column(name = "rate", nullable = false)
    private Double rate;  // курс валюты

    // Конструкторы, геттеры и сеттеры
    public CurrencyRate() {}

    public CurrencyRate(String currency, Double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}

