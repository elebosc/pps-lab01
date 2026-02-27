package example.model.impl;

import example.model.api.AccountHolder;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount extends AbstractBankAccount {

    public SimpleBankAccount(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    double getFee() {
        return 0;
    }


}
