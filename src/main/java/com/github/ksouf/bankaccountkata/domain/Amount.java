package com.github.ksouf.bankaccountkata.domain;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Amount {

    private final Integer value;

    public Amount(Integer value) {
        notNull(value, "initial value of amount is required");
        isTrue(value >= 0, "initial value of amount must be positive");

        this.value = value;
    }

    Amount add(Amount amountToAdd) {
        notNull(amountToAdd, "amount to add is required");
        return new Amount(value + amountToAdd.value);
    }

    Amount subtract(Amount amountToSubtract) {
        notNull(amountToSubtract, "amount to subtract is required");
        isTrue((value - amountToSubtract.value) >= 0, "value amount to subtract must be inferior to current amount value");

        return new Amount(value - amountToSubtract.value);
    }

    @Override
    public boolean equals(Object o) {
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }

    Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public boolean isNotZero() {
        return !new Amount(0).equals(this);
    }
}