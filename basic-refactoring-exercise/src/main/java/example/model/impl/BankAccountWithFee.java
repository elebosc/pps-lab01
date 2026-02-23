package example.model.impl;

import example.model.api.AccountHolder;

public class BankAccountWithFee extends AbstractBankAccount {

    public static final double WITHDRAWAL_FEE = 1.0;

    public BankAccountWithFee(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        super.withdraw(userID, amount + WITHDRAWAL_FEE);
    }

}
