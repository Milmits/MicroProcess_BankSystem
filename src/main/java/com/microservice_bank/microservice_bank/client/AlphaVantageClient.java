package com.microservice_bank.microservice_bank.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class AlphaVantageClient {

    private final String API_KEY = "YOUR_ALPHA_VANTAGE_API_KEY";
    private final String BASE_URL = "https://www.alphavantage.co/query";

    public Double getExchangeRate(String currencyPair) {
        String fromCurrency = currencyPair.split("/")[0];
        String toCurrency = currencyPair.split("/")[1];

        String url = String.format("%s?function=CURRENCY_EXCHANGE_RATE&from_currency=%s&to_currency=%s&apikey=%s",
                BASE_URL, fromCurrency, toCurrency, API_KEY);

        RestTemplate restTemplate = new RestTemplate();
        Map response = restTemplate.getForObject(url, Map.class);

        try {
            Map exchangeRateData = (Map) response.get("Realtime Currency Exchange Rate");
            return Double.parseDouble((String) exchangeRateData.get("5. Exchange Rate"));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения данных из Alpha Vantage");
        }
    }
}
