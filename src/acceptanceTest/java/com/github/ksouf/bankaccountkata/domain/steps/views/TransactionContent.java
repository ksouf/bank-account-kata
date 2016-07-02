package com.github.ksouf.bankaccountkata.domain.steps.views;

import com.github.ksouf.bankaccountkata.domain.Amount;
import com.github.ksouf.bankaccountkata.domain.OperationType;
import com.github.ksouf.bankaccountkata.domain.Transaction;
import com.github.ksouf.bankaccountkata.domain.TransactionBuilder;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionContent {

    private String operation;
    private String date;
    private Integer amount;
    private Integer balance;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }



    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Transaction toDomainTransaction() {
        return new TransactionBuilder().withOperation(OperationType.findByOperationName(operation))
                .withDate(toIsoDate())
                .withAmount(new Amount(amount))
                .withBalance(new Amount(balance))
                .build();
    }

    private LocalDate toIsoDate() {
        if (StringUtils.isEmpty(date)) return null;
        else return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
