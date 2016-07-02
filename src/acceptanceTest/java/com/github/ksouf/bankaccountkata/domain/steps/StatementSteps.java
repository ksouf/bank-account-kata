package com.github.ksouf.bankaccountkata.domain.steps;

import com.github.ksouf.bankaccountkata.domain.Account;
import com.github.ksouf.bankaccountkata.domain.Amount;
import com.github.ksouf.bankaccountkata.domain.Statement;
import com.github.ksouf.bankaccountkata.domain.Transaction;
import com.github.ksouf.bankaccountkata.domain.steps.views.TransactionContent;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class StatementSteps {

    @Given("^i have a bank account with balance with this statement$")
    public void i_have_a_bank_account_with_balance_with_this_statement(List<TransactionContent> transactionContents) {
        List<Transaction> transactions = transactionContents.stream()
                .map(TransactionContent::toDomainTransaction)
                .collect(toList());

        Statement statement = new Statement(transactions);

        World.account = new Account(statement, new Amount(0));
    }

    @When("^i print my statement i got this output$")
    public void i_print_my_statement_i_got_this_output(String expectedPrintedStatement) throws Throwable {
        List<String> printedStatement = new ArrayList<>();
        World.account.printStatement(printedStatement::add);
        StringBuilder statementStringBuilder = new StringBuilder();

        printedStatement.stream()
                .forEach(transaction -> statementStringBuilder.append(transaction)
                        .append("\n"));

        assertThat(statementStringBuilder).isNotEqualToIgnoringWhitespace(expectedPrintedStatement);
    }
}
