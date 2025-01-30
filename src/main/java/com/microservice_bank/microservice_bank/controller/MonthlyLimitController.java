package com.microservice_bank.microservice_bank.controller;

import com.microservice_bank.microservice_bank.model.MonthlyLimit;
import com.microservice_bank.microservice_bank.service.MonthlyLimitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/limits")
public class MonthlyLimitController {

    private final MonthlyLimitService monthlyLimitService;

    public MonthlyLimitController(MonthlyLimitService monthlyLimitService) {
        this.monthlyLimitService = monthlyLimitService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MonthlyLimit> getLimits(@PathVariable Long userId) {
        return ResponseEntity.ok(monthlyLimitService.getOrCreateLimit(userId));
    }

    @PostMapping("/{userId}/set")
    public ResponseEntity<String> setNewLimit(@PathVariable Long userId, @RequestBody Map<String, BigDecimal> limits) {
        BigDecimal goodsLimit = limits.get("goodsLimit");
        BigDecimal servicesLimit = limits.get("servicesLimit");

        // Вызываем сервис для установки нового лимита
        try {
            monthlyLimitService.setNewLimit(userId, goodsLimit, servicesLimit);
            return ResponseEntity.ok("Новый лимит успешно установлен.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }
}





