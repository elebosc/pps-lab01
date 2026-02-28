package example.model.impl;

import example.model.api.AccountHolder;

/**
 * This class represents a bank account that applies a fee when a withdrawal is performed.
 */
public class BankAccountWithFee extends AbstractBankAccount {

    private static final double WITHDRAWAL_FEE = 1.0;

    public BankAccountWithFee(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    double getFee() {
        return WITHDRAWAL_FEE;
    }

}
