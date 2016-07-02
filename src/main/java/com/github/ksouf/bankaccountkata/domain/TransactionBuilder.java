package com.github.ksouf.bankaccountkata.domain;

import java.time.LocalDate;

public class TransactionBuilder {

    private OperationType operation;
    private LocalDate date;
    private Amount amount;
    private Amount balance;

    public TransactionBuilder withOperation(OperationType operation) {
        this.operation = operation;
        return this;
    }

    public TransactionBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public TransactionBuilder withAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder withBalance(Amount balance) {
        this.balance = balance;
        return this;
    }

    public Transaction build() {
        return new Transaction(operation, date, amount, balance);
    }
}