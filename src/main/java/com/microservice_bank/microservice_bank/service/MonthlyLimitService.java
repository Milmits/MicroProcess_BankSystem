package com.microservice_bank.microservice_bank.service;

import com.microservice_bank.microservice_bank.model.MonthlyLimit;
import com.microservice_bank.microservice_bank.repository.MonthlyLimitRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MonthlyLimitService {

    private final MonthlyLimitRepository monthlyLimitRepository;

    public MonthlyLimitService(MonthlyLimitRepository monthlyLimitRepository) {
        this.monthlyLimitRepository = monthlyLimitRepository;
    }

    // Получить или создать лимит
    public MonthlyLimit getOrCreateLimit(Long userId) {
        return monthlyLimitRepository.findByUserId(userId)
                .orElseGet(() -> {
                    MonthlyLimit defaultLimit = new MonthlyLimit(userId, BigDecimal.valueOf(1000), BigDecimal.valueOf(1000));
                    return monthlyLimitRepository.save(defaultLimit);
                });
    }

    // Установить новый лимит
    public void setNewLimit(Long userId, BigDecimal goodsLimit, BigDecimal servicesLimit) {
        Optional<MonthlyLimit> existingLimit = monthlyLimitRepository.findByUserId(userId);

        if (existingLimit.isPresent()) {
            throw new IllegalArgumentException("Лимит уже существует для данного пользователя.");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();

        MonthlyLimit newLimit = new MonthlyLimit(userId, goodsLimit, servicesLimit);
        newLimit.setCreationDate(currentDateTime);  // Устанавливаем текущую дату для нового лимита

        monthlyLimitRepository.save(newLimit);
    }

    // Обновить лимиты
    public void updateLimits(Long userId, BigDecimal goodsLimit, BigDecimal servicesLimit) {
        MonthlyLimit limit = getOrCreateLimit(userId);
        if (goodsLimit != null) limit.setGoodsLimit(goodsLimit);
        if (servicesLimit != null) limit.setServicesLimit(servicesLimit);
        monthlyLimitRepository.save(limit);
    }
}



