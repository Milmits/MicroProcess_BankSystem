package com.microservice_bank.microservice_bank.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_pair", nullable = false)
    private String currencyPair; // Например, KZT/USD

    @Column(name = "exchange_rate", nullable = false)
    private Double exchangeRate;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public ExchangeRate() {}

    public ExchangeRate(String currencyPair, Double exchangeRate, LocalDate date) {
        this.currencyPair = currencyPair;
        this.exchangeRate = exchangeRate;
        this.date = date;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public LocalDate getDate() {
        return date;
    }
}
