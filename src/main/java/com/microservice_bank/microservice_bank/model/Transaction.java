package com.microservice_bank.microservice_bank.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_from", nullable = false)
    private Long accountFrom;

    @Column(name = "account_to", nullable = false)
    private Long accountTo;

    @Column(name = "currency_shortname", nullable = false, length = 3)
    private String currencyShortname;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "expense_category", nullable = false)
    private String expenseCategory;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(name = "limit_exceeded", nullable = false)
    private Boolean limitExceeded = false;

    @Column(name = "limit_sum")
    private Double limitSum;

    @Column(name = "limit_datetime")
    private LocalDateTime limitDatetime;

    @Column(name = "limit_currency_shortname")
    private String limitCurrencyShortname;

    // Конструкторы, геттеры и сеттеры
    public Transaction() {}

    public Transaction(Long accountFrom, Long accountTo, String currencyShortname, Double amount,
                       String expenseCategory, LocalDateTime datetime, Boolean limitExceeded,
                       Double limitSum, LocalDateTime limitDatetime, String limitCurrencyShortname) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.currencyShortname = currencyShortname;
        this.amount = amount;
        this.expenseCategory = expenseCategory;
        this.datetime = datetime;
        this.limitExceeded = limitExceeded;
        this.limitSum = limitSum;
        this.limitDatetime = limitDatetime;
        this.limitCurrencyShortname = limitCurrencyShortname;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAccountFrom() { return accountFrom; }
    public void setAccountFrom(Long accountFrom) { this.accountFrom = accountFrom; }

    public Long getAccountTo() { return accountTo; }
    public void setAccountTo(Long accountTo) { this.accountTo = accountTo; }

    public String getCurrencyShortname() { return currencyShortname; }
    public void setCurrencyShortname(String currencyShortname) { this.currencyShortname = currencyShortname; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getExpenseCategory() { return expenseCategory; }
    public void setExpenseCategory(String expenseCategory) { this.expenseCategory = expenseCategory; }

    public LocalDateTime getDatetime() { return datetime; }
    public void setDatetime(LocalDateTime datetime) { this.datetime = datetime; }

    public Boolean getLimitExceeded() { return limitExceeded; }
    public void setLimitExceeded(Boolean limitExceeded) { this.limitExceeded = limitExceeded; }

    public Double getLimitSum() { return limitSum; }
    public void setLimitSum(Double limitSum) { this.limitSum = limitSum; }

    public LocalDateTime getLimitDatetime() { return limitDatetime; }
    public void setLimitDatetime(LocalDateTime limitDatetime) { this.limitDatetime = limitDatetime; }

    public String getLimitCurrencyShortname() { return limitCurrencyShortname; }
    public void setLimitCurrencyShortname(String limitCurrencyShortname) { this.limitCurrencyShortname = limitCurrencyShortname; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", currencyShortname='" + currencyShortname + '\'' +
                ", amount=" + amount +
                ", expenseCategory='" + expenseCategory + '\'' +
                ", datetime=" + datetime +
                ", limitExceeded=" + limitExceeded +
                '}';
    }
}





