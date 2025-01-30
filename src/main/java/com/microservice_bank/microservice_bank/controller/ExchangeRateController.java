package com.microservice_bank.microservice_bank.controller;

import com.microservice_bank.microservice_bank.service.ExchangeRateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/{currencyPair}")
    public Double getExchangeRate(@PathVariable String currencyPair) {
        return exchangeRateService.getExchangeRate(currencyPair);
    }
}


