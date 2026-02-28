package example.model.impl;

import example.model.api.AccountHolder;
import example.model.api.BankAccount;

/**
 * This class represents an abstraction of the different types of bank account.
 */
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
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposited amount cannot be negative or null.");
        }
        if (isUserTheAccountHolder(userID)) {
            this.balance = this.balance + amount;
        }
    }

    private boolean isWithdrawalAllowed(final double amount) {
        return this.balance >= amount + getFee();
    }

    private boolean isUserTheAccountHolder(final int id) {
        return this.holder.id() == id;
    }

    abstract double getFee();

    public void withdraw(final int userID, final double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawn amount cannot be negative or null.");
        }
        if (isUserTheAccountHolder(userID) && isWithdrawalAllowed(amount)) {
            this.balance = this.balance - (amount + getFee());
        }
    }

}
