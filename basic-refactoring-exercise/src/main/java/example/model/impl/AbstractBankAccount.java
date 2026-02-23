package example.model.impl;

import example.model.api.AccountHolder;
import example.model.api.BankAccount;

abstract class AbstractBankAccount implements BankAccount {

    private double balance;
    private final AccountHolder holder;

    public AbstractBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(final int userID, final double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        if (isUserTheAccountHolder(userID)) {
            this.balance = this.balance + amount;
        }
    }

    public void withdraw(final int userID, final double amount) {
        if (isUserTheAccountHolder(userID) && isWithdrawalAllowed(amount)) {
            this.balance = this.balance - amount;
        }
    }

    private boolean isWithdrawalAllowed(final double amount) {
        return this.balance >= amount;
    }

    private boolean isUserTheAccountHolder(final int id) {
        return this.holder.id() == id;
    }

}
