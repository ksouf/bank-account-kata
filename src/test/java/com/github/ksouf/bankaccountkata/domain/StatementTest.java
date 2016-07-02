package com.github.ksouf.bankaccountkata.domain;

import org.junit.FixMethodOrder;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@FixMethodOrder
public class StatementTest {

    @Test(expected = NullPointerException.class)
    public void checkIfTransactionsMustBeNonNullable() {
     new Statement(null);
    }

    @Test
    public void checkCurrentBalance() {
        Statement statement = new Statement();
        statement.registerDeposit(new Amount(100), LocalDate.of(2016, 5, 5));

        assertThat(statement.currentBalance()).isEqualTo(new Amount(100));
    }

    @Test
    public void mustPrintHeader() {
        Statement statement = new Statement();
        List<String> transactions = new ArrayList<>();
        statement.print(transactions::add);

        assertThat(transactions.get(0)).isEqualToIgnoringWhitespace("| operation | date | amount | balance |");
    }

    @Test
    public void mustRegisterDeposit() {
        Statement statement = new Statement();
        statement.registerDeposit(new Amount(100), LocalDate.of(2016, 5, 5));
        List<String> transactions = new ArrayList<>();

        statement.print(transactions::add);

        assertThat(transactions.get(1)).isEqualToIgnoringWhitespace("|DEPOSIT|2016-05-05|100|0|");
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustRegisterNotWithdrawWithoutCurrentBalanceSuperiorToAmount() {
        Statement statement = new Statement();
        statement.registerWithdraw(new Amount(100), LocalDate.of(2016, 5, 5));
    }

    @Test
    public void mustRegisterWithdraw() {
        Statement statement = new Statement();
        statement.registerDeposit(new Amount(1000), LocalDate.of(2016, 5, 5));

        statement.registerWithdraw(new Amount(100), LocalDate.of(2016, 5, 5));
        List<String> transactions = new ArrayList<>();

        statement.print(transactions::add);

        assertThat(transactions.get(2)).isEqualToIgnoringWhitespace("|WITHDRAW|2016-05-05|100|1000|");
    }
}
