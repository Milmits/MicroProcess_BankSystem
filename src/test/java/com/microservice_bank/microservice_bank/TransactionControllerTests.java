package com.microservice_bank.microservice_bank;

import com.microservice_bank.microservice_bank.controller.TransactionController;
import com.microservice_bank.microservice_bank.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionControllerTests {

    @Autowired
    private TransactionController transactionController;

    @Test
    public void testTransactionLimitExceeded() {
        Transaction transaction = new Transaction();
        transaction.setAccountFrom(1L);  // Используем тестового пользователя
        transaction.setAccountTo(2L);
        transaction.setAmount(1500.0);  // Сумма больше лимита
        transaction.setExpenseCategory("goods");
        transaction.setDatetime(LocalDateTime.now());

        ResponseEntity<Transaction> response = transactionController.addTransaction(transaction);

        assertEquals(403, response.getStatusCodeValue());  // Проверяем, что вернулся статус 403 (Forbidden)
    }
}

