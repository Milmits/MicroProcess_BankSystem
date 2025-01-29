package com.microservice_bank.microservice_bank.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "monthly_limits")
public class MonthlyLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "goods_limit", nullable = false, precision = 10, scale = 2)
    private BigDecimal goodsLimit;

    @Column(name = "services_limit", nullable = false, precision = 10, scale = 2)
    private BigDecimal servicesLimit;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    public MonthlyLimit() {
        // Пустой конструктор для JPA
    }

    public MonthlyLimit(Long userId, BigDecimal goodsLimit, BigDecimal servicesLimit) {
        this.userId = userId;
        this.goodsLimit = goodsLimit != null ? goodsLimit : BigDecimal.valueOf(1000);
        this.servicesLimit = servicesLimit != null ? servicesLimit : BigDecimal.valueOf(1000);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getGoodsLimit() {
        return goodsLimit;
    }

    public void setGoodsLimit(BigDecimal goodsLimit) {
        this.goodsLimit = goodsLimit;
    }

    public BigDecimal getServicesLimit() {
        return servicesLimit;
    }

    public void setServicesLimit(BigDecimal servicesLimit) {
        this.servicesLimit = servicesLimit;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}



