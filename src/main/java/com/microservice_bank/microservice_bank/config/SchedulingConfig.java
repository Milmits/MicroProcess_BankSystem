package com.microservice_bank.microservice_bank.config;

import com.microservice_bank.microservice_bank.service.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Autowired
    private CurrencyRateService currencyRateService;

    // Периодическое обновление курса валют
    // Метод будет выполняться каждый день в полночь
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateCurrencyRates() {
        currencyRateService.fetchAndSaveCurrencyRates();
    }
}
