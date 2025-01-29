package com.microservice_bank.microservice_bank.controller;

import com.microservice_bank.microservice_bank.model.Transaction;
import com.microservice_bank.microservice_bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        // Если лимит превышен, возвращаем ошибку
        if (savedTransaction.getLimitExceeded()) {
            return ResponseEntity.status(403).body(savedTransaction);  // 403 Forbidden
        }

        return ResponseEntity.status(201).body(savedTransaction);  // 201 Created
    }

    @GetMapping("/exceeding-limits")
    public ResponseEntity<List<Transaction>> getTransactionsExceedingLimits() {
        List<Transaction> transactions = transactionService.getTransactionsExceedingLimits();
        return ResponseEntity.ok(transactions);
    }
}


