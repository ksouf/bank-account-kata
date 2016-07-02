package com.github.ksouf.bankaccountkata.domain.steps;

import com.github.ksouf.bankaccountkata.domain.Account;
import com.github.ksouf.bankaccountkata.domain.Amount;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class DepositSteps {

    @Given("^i have a bank account with balance (\\d+)$")
    public void i_have_a_bank_account_with_balance(Integer balance) throws Throwable {
        World.account = new Account(new Amount(balance));
    }

    @When("^i am depositing (-?\\d+) in my account$")
    public void i_deposit(Integer amount) {
        try {
            World.account.deposit(new Amount(amount));
        }catch(IllegalArgumentException e) {
            //DO Nothing (this is what we are waiting for)
        }
    }

    @Then("^my balance must be (\\d+)$")
    public void my_balance_must_be(Integer amount) throws Throwable {
        assertThat(World.account.currentBalance()).isEqualTo(new Amount(amount));
    }
}