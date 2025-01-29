package com.microservice_bank.microservice_bank.service;

import com.microservice_bank.microservice_bank.model.MonthlyLimit;
import com.microservice_bank.microservice_bank.model.Transaction;
import com.microservice_bank.microservice_bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MonthlyLimitService monthlyLimitService;

    @Autowired
    private CurrencyRateService currencyRateService;

    // Метод для получения транзакций, которые превышают лимит
    public List<Transaction> getTransactionsExceedingLimits() {
        List<Object[]> results = transactionRepository.findTransactionsExceedingLimits();
        List<Transaction> transactions = new ArrayList<>();

        for (Object[] result : results) {
            Transaction transaction = (Transaction) result[0];  // Транзакция
            Double goodsLimit = (Double) result[1];  // Лимит для товаров
            Double servicesLimit = (Double) result[2];  // Лимит для услуг
            LocalDateTime limitDateTime = (LocalDateTime) result[3];  // Дата установки лимита

            // Добавляем информацию о лимите в транзакцию
            transaction.setLimitSum(goodsLimit != null ? goodsLimit : servicesLimit);  // Выбираем соответствующий лимит
            transaction.setLimitDatetime(limitDateTime);
            transaction.setLimitCurrencyShortname("USD");

            transactions.add(transaction);
        }

        return transactions;
    }

    // Метод для сохранения транзакции с проверкой лимита
    public Transaction saveTransaction(Transaction transaction) {
        Long userId = transaction.getAccountFrom();  // Предполагаем, что accountFrom - это ID пользователя

        // Получаем лимиты пользователя для товаров и услуг
        MonthlyLimit monthlyLimit = monthlyLimitService.getOrCreateLimit(userId);

        // Получаем курс валюты, если транзакция не в USD
        Double conversionRate = 1.0;
        if (!transaction.getCurrencyShortname().equals("USD")) {
            conversionRate = currencyRateService.getCurrencyRate(transaction.getCurrencyShortname());
        }

        // Преобразуем сумму в USD
        BigDecimal transactionAmount = BigDecimal.valueOf(transaction.getAmount() * conversionRate);

        // Проверяем, превышает ли транзакция лимит на товары или услуги
        if (transaction.getExpenseCategory().equalsIgnoreCase("goods")) {
            if (monthlyLimit.getGoodsLimit().compareTo(transactionAmount) < 0) {
                // Лимит на товары превышен
                transaction.setLimitExceeded(true);
                return transaction; // Транзакция отклонена
            }
        } else if (transaction.getExpenseCategory().equalsIgnoreCase("services")) {
            if (monthlyLimit.getServicesLimit().compareTo(transactionAmount) < 0) {
                // Лимит на услуги превышен
                transaction.setLimitExceeded(true);
                return transaction; // Транзакция отклонена
            }
        }

        // Если лимит не превышен, сохраняем транзакцию
        return transactionRepository.save(transaction);
    }
}




