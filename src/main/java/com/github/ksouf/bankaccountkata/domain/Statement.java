package com.github.ksouf.bankaccountkata.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

public class Statement {

    private List<Transaction> transactions = new ArrayList<>();

    public Statement(List<Transaction> initialTransactions) {
        notNull(initialTransactions, "initial transactions must not be null");
        this.transactions = initialTransactions;
    }

    public Statement() {
        transactions = new ArrayList<>();
    }

    void registerDeposit(Amount amount, LocalDate date) {
        Transaction transaction = new TransactionBuilder().withAmount(amount)
                .withDate(date)
                .withOperation(OperationType.DEPOSIT)
                .withBalance(currentBalance())
                .build();

        transactions.add(transaction);
    }

    Amount currentBalance() {
        return transactions.stream()
                .reduce((previous, next) -> next)
                .map(Transaction::newBalance)
                .orElse(new Amount(0));
    }

    void registerWithdraw(Amount amount, LocalDate date) {
        Transaction transaction = new TransactionBuilder().withAmount(amount)
                .withDate(date)
                .withOperation(OperationType.WITHDRAW)
                .withBalance(currentBalance())
                .build();

        transactions.add(transaction);
    }

    void print(Printer printer) {
        printer.print(Transaction.HEADER);
        transactions.stream().forEach(transaction -> printer.print(transaction.toPrint()));
    }
}