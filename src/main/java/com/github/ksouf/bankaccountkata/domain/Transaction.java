package com.github.ksouf.bankaccountkata.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.github.ksouf.bankaccountkata.domain.OperationType.DEPOSIT;
import static com.github.ksouf.bankaccountkata.domain.OperationType.WITHDRAW;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Transaction {

    static final String HEADER = "| operation | date | amount | balance |";
    private OperationType operation;
    private LocalDate date;
    private Amount amount;
    private Amount balance;

    Transaction(OperationType operation,
                LocalDate date,
                Amount amount,
                Amount balance) {

        notNull(operation, "operation is required");
        notNull(amount, "amount is required");
        notNull(date, "date is required");
        notNull(balance, "balance is required");

        if (WITHDRAW.equals(operation)) {
            isTrue((balance.value() - amount.value()) >= 0, "current balance must be superior or equal amount");
        }

        this.operation = operation;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    String toPrint() {
        return String.format("|%s|%s|%s|%s|",
                operation.toString(),
                date.format(DateTimeFormatter.ISO_DATE),
                amount,
                balance);
    }

    Amount newBalance() {
        if (DEPOSIT.equals(operation)) {
            return balance.add(amount);
        } else {
            return balance.subtract(amount);
        }
    }
}