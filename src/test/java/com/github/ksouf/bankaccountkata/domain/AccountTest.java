package com.github.ksouf.bankaccountkata.domain;


import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {


    private String nowAsString = LocalDate.now()
            .format(DateTimeFormatter.ISO_DATE);

    @Test(expected = NullPointerException.class)
    public void initialBalanceMustBeNonNullable() {
        new Account(null);
    }


    @Test
    public void depositMustAddToCurrentBalance() {
        Account account = new Account(new Amount(10));
        Amount amountToDeposit = new Amount(100);

        account.deposit(amountToDeposit);

        assertThat(account.currentBalance()).isEqualTo(new Amount(110));
    }

    @Test
    public void retrieveMustSubtractToCurrentBalance() {
        Account account = new Account(new Amount(2000));
        Amount amountToRetrieve = new Amount(1000);

        account.withdraw(amountToRetrieve);

        assertThat(account.currentBalance()).isEqualTo(new Amount(1000));
    }

    @Test(expected = NullPointerException.class)
    public void transactionsMustBeNonNullable() {
        new Account(null, new Amount(1000));
    }


    @Test(expected = NullPointerException.class)
    public void initialBalanceMustBeNonNullableEvenWithTransactions() {
        new Account(new Statement(), null);
    }

    @Test
    public void depositMustGenerateATransactionInStatement() {
        Account account = new Account(new Statement(), new Amount(0));
        account.deposit(new Amount(2000));
        account.withdraw(new Amount(1200));


        List<String> printedStatement = new ArrayList<>();

        account.printStatement(printedStatement::add);

        assertThat(printedStatement.get(0)).isEqualTo("| operation | date | amount | balance |");
        assertThat(printedStatement.get(1)).isEqualTo("|DEPOSIT|"+nowAsString+"|2000|0|");
        assertThat(printedStatement.get(2)).isEqualTo("|WITHDRAW|"+nowAsString+"|1200|2000|");

        assertThat(account.currentBalance()).isEqualTo(new Amount(800));
    }
}