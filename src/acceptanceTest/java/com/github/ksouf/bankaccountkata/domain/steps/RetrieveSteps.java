package com.github.ksouf.bankaccountkata.domain.steps;

import com.github.ksouf.bankaccountkata.domain.Amount;
import cucumber.api.java.en.When;

public class RetrieveSteps {

    @When("^i am withdrawing (-?\\d+) from my account$")
    public void i_am_withdrawing_from_my_account(Integer amountToRetrieve) {
        try {
            World.account.withdraw(new Amount(amountToRetrieve));
        }catch (IllegalArgumentException e) {
            //DO Nothing this is to be expected in some cases
        }
    }
}
