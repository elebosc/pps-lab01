import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {
    static final int INITIAL_BALANCE = 0;
    static final int FIRST_DEPOSIT_AMOUNT = 100;
    static final int WITHDRAWAL_AMOUNT = 70;
    static final int WRONG_USER_ID = 2;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        final String name = "Mario";
        final String surname = "Rossi";
        final int id = 1;
        accountHolder = new AccountHolder(name, surname, id);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        final int secondDepositAmount = 50;
        bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        bankAccount.deposit(WRONG_USER_ID, secondDepositAmount);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        final int expectedRemainingAmount = 30;
        bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), WITHDRAWAL_AMOUNT);
        assertEquals(expectedRemainingAmount, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        bankAccount.withdraw(WRONG_USER_ID, WITHDRAWAL_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
