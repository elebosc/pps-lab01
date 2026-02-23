import example.model.api.AccountHolder;
import example.model.api.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class AbstractBankAccountTest {

    protected static final int INITIAL_BALANCE = 0;
    protected static final double FIRST_DEPOSIT_AMOUNT = 100.0;
    protected static final double WITHDRAWAL_AMOUNT = 70.0;
    private static final int WRONG_USER_ID = 2;
    private static final int NEGATIVE_AMOUNT = -1;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    protected void setTestEnvironment(AccountHolder accountHolder, BankAccount bankAccount) {
        this.accountHolder = accountHolder;
        this.bankAccount = bankAccount;
    }

    protected BankAccount getBankAccount() {
        return this.bankAccount;
    }

    protected AccountHolder getAccountHolder() {
        return this.accountHolder;
    }

    @Test
    void testInitialBalanceIsCorrect() {
        assertEquals(INITIAL_BALANCE, this.bankAccount.getBalance());
    }

    @Test
    void testDepositIsSuccessful() {
        this.bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, this.bankAccount.getBalance());
    }

    @Test
    void testCannotDepositNegativeAmounts() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.bankAccount.deposit(this.accountHolder.id(), NEGATIVE_AMOUNT)
        );
    }

    @Test
    void testWrongDepositDoesNotHappen() {
        final double secondDepositAmount = 50.0;
        this.bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        this.bankAccount.deposit(WRONG_USER_ID, secondDepositAmount);
        assertEquals(FIRST_DEPOSIT_AMOUNT, this.bankAccount.getBalance());
    }

    @Test
    abstract void testWithdrawalIsSuccessful();

    @Test
    void testCannotWithdrawNegativeAmounts() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.bankAccount.withdraw(this.accountHolder.id(), NEGATIVE_AMOUNT)
        );
    }

    @Test
    void testWrongWithdrawalDoesNotHappen() {
        this.bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        this.bankAccount.withdraw(WRONG_USER_ID, WITHDRAWAL_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, this.bankAccount.getBalance());
    }

    @Test
    void testExceedingWithdrawalDoesNotHappen() {
        final double withdrawalAmount = FIRST_DEPOSIT_AMOUNT + 1;
        this.bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        this.bankAccount.withdraw(accountHolder.id(), withdrawalAmount);
        assertEquals(FIRST_DEPOSIT_AMOUNT, this.bankAccount.getBalance());
    }

}
