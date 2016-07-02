package com.github.ksouf.bankaccountkata.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AmountTest {

    @Test(expected = NullPointerException.class)
    public void amountValueMustBeNonNullable() {
        new Amount(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initialBalanceMustBePositive() {
        new Amount(-100);
    }

    @Test
    public void twoAmountWithSameValueAreEqual() {
        Amount amount1 = new Amount(10);
        Amount amount2 = new Amount(10);

        assertThat(amount1).isEqualTo(amount2);
    }

    @Test(expected = NullPointerException.class)
    public void addedAmountMustNotBeNull() {
        Amount amount = new Amount(100);
        amount.add(null);
    }


    @Test
    public void mustAddAmountToAnother() {
        Amount amount = new Amount(100);
        Amount amountToDeposit = new Amount(400);
        Amount newAmount = amount.add(amountToDeposit);

        assertThat(newAmount).isEqualTo(new Amount(500));
    }



    @Test(expected = NullPointerException.class)
    public void subtractedAmountMustNotBeNull() {
        Amount amount = new Amount(100);
        amount.subtract(null);
    }



    @Test(expected = IllegalArgumentException.class)
    public void subtractedAmountMustNotSuperiorToCurrentAmount() {
        Amount amount = new Amount(1000);
        Amount amountToRetrieve = new Amount(2000);
        amount.subtract(amountToRetrieve);
    }

    @Test
    public void mustSubtractAmountToAnother() {
        Amount amount = new Amount(1000);
        Amount amountToRetrieve = new Amount(400);
        Amount newAmount = amount.subtract(amountToRetrieve);

        assertThat(newAmount).isEqualTo(new Amount(600));
    }
}