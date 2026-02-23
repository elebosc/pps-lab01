package example;

import example.model.api.AccountHolder;
import example.model.api.BankAccount;
import example.model.impl.SimpleBankAccount;

public class Main {
    public static void main(String[] args) {
        final String name = "Mario";
        final String surname = "Rossi";
        final int id = 1;
        final AccountHolder accountHolder = new AccountHolder(name, surname, id);
        final int initialBalance = 0;
        final BankAccount bankAccount = new SimpleBankAccount(accountHolder, initialBalance);
        final int firstDepositAmount = 100;
        bankAccount.deposit(accountHolder.id(), firstDepositAmount);
        System.out.println("Current balance is " + bankAccount.getBalance());
        final int firstWithdrawalAmount = 30;
        bankAccount.withdraw(accountHolder.id(), firstWithdrawalAmount);
        System.out.println("Current balance is " + bankAccount.getBalance());
        final int secondWithdrawalAmount = 80;
        bankAccount.withdraw(accountHolder.id(), secondWithdrawalAmount);
        System.out.println("Current balance is " + bankAccount.getBalance());
    }
}
