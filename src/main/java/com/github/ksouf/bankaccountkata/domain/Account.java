package com.github.ksouf.bankaccountkata.domain;


import java.time.LocalDate;

import static org.apache.commons.lang3.Validate.notNull;

public class Account {

    private Statement statement;

    public Account(Amount initialBalance) {
        notNull(initialBalance, "initial balance must not be null");

        statement = new Statement();
        if(initialBalance.isNotZero()) {
            statement.registerDeposit(initialBalance, LocalDate.now());
        }
    }

    public Account(Statement statement, Amount initialBalance) {
        this(initialBalance);
        notNull(statement, "list of transaction must not be null");
    }

    public void deposit(Amount amountToDeposit) {
        statement.registerDeposit(amountToDeposit, LocalDate.now());
    }

    public Amount currentBalance() {
        return statement.currentBalance();
    }

    public void withdraw(Amount amountToWithdraw) {
        statement.registerWithdraw(amountToWithdraw, LocalDate.now());
    }

    public void printStatement(Printer printer) {
        statement.print(printer);
    }
}