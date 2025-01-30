package com.microservice_bank.microservice_bank.repository;

import com.microservice_bank.microservice_bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t, m.goodsLimit, m.servicesLimit, m.creationDate " +
            "FROM Transaction t " +
            "JOIN MonthlyLimit m ON m.userId = t.accountFrom " +
            "WHERE (t.expenseCategory = 'goods' AND t.amount > m.goodsLimit) " +
            "OR (t.expenseCategory = 'services' AND t.amount > m.servicesLimit)")
    List<Object[]> findTransactionsExceedingLimits();
}



