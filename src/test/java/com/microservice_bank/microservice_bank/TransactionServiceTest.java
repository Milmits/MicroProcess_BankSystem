package com.microservice_bank.microservice_bank;

import com.microservice_bank.microservice_bank.model.MonthlyLimit;
import com.microservice_bank.microservice_bank.model.Transaction;
import com.microservice_bank.microservice_bank.repository.MonthlyLimitRepository;
import com.microservice_bank.microservice_bank.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private MonthlyLimitRepository monthlyLimitRepository;

    @Test
    public void shouldMarkTransactionAsExceeded() {
        Long userId = 1L;
        Transaction transaction = new Transaction(1L, 2L, "USD", 2000.00, "goods", LocalDateTime.now(), false, null, null, null);
        MonthlyLimit limit = new MonthlyLimit(userId, BigDecimal.valueOf(1000), BigDecimal.valueOf(500));

        Mockito.when(monthlyLimitRepository.findByUserId(userId)).thenReturn(Optional.of(limit));

    }
}

