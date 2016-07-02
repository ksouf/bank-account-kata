package com.github.ksouf.bankaccountkata.domain;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {

    @Test(expected = NullPointerException.class)
    public void checkIfTransactionOperationIsNull() {
        new TransactionBuilder().withOperation(null)
                .withAmount(new Amount(0))
                .withBalance(new Amount(0))
                .withDate(LocalDate.now())
                .build();
    }


    @Test(expected = NullPointerException.class)
    public void checkIfTransactionAmountIsNull() {
        new TransactionBuilder().withOperation(OperationType.DEPOSIT)
                .withAmount(null)
                .withBalance(new Amount(0))
                .withDate(LocalDate.now())
                .build();
    }


    @Test(expected = NullPointerException.class)
    public void checkIfTransactionBalanceIsNull() {
        new TransactionBuilder().withOperation(OperationType.DEPOSIT)
                .withAmount(new Amount(0))
                .withBalance(null)
                .withDate(LocalDate.now())
                .build();
    }

    @Test
    public void checkToPrintDeposit() {
        Transaction transaction = new TransactionBuilder().withOperation(OperationType.DEPOSIT)
                .withAmount(new Amount(200))
                .withBalance(new Amount(3000))
                .withDate(LocalDate.of(2016, 5, 5))
                .build();

        assertThat(transaction.toPrint()).isEqualToIgnoringWhitespace("|DEPOSIT|2016-05-05|200|3000|");
    }


    @Test
    public void checkToPrintWithdraw() {
        Transaction transaction = new TransactionBuilder().withOperation(OperationType.WITHDRAW)
                .withAmount(new Amount(400))
                .withBalance(new Amount(5000))
                .withDate(LocalDate.of(2016, 5, 7))
                .build();

        assertThat(transaction.toPrint()).isEqualToIgnoringWhitespace("|WITHDRAW|2016-05-07|400|5000|");
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawBalanceMustBeSuperiorToAmount() {
        new TransactionBuilder().withOperation(OperationType.WITHDRAW)
                .withAmount(new Amount(1000))
                .withBalance(new Amount(500))
                .withDate(LocalDate.of(2016, 5, 7))
                .build();

    }

    @Test
    public void checkNewBalanceWithdraw() {
        Transaction transaction = new TransactionBuilder().withOperation(OperationType.WITHDRAW)
                .withAmount(new Amount(400))
                .withBalance(new Amount(5000))
                .withDate(LocalDate.of(2016, 5, 7))
                .build();

        assertThat(transaction.newBalance()).isEqualTo(new Amount(4600));
    }


    @Test
    public void checkNewBalanceDeposit() {
        Transaction transaction = new TransactionBuilder().withOperation(OperationType.DEPOSIT)
                .withAmount(new Amount(200))
                .withBalance(new Amount(3000))
                .withDate(LocalDate.of(2016, 5, 5))
                .build();

        assertThat(transaction.newBalance()).isEqualTo(new Amount(3200));
    }
}