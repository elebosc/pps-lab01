import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.BankAccountWithFee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountWithFeeTest {
    private static final int INITIAL_BALANCE = 0;
    private static final double FIRST_DEPOSIT_AMOUNT = 100.0;
    private static final double WITHDRAWAL_AMOUNT = 70.0;
    private static final int WRONG_USER_ID = 2;
    private static final int NEGATIVE_AMOUNT = -1;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        final String name = "Mario";
        final String surname = "Rossi";
        final int id = 1;
        accountHolder = new AccountHolder(name, surname, id);
        bankAccount = new BankAccountWithFee(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalanceIsCorrect() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDepositIsSuccessful() {
        bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testCannotDepositNegativeAmounts() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(accountHolder.id(), NEGATIVE_AMOUNT));
    }

    @Test
    void testWrongDepositDoesNotHappen() {
        final double secondDepositAmount = 50.0;
        bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        bankAccount.deposit(WRONG_USER_ID, secondDepositAmount);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdrawalIsSuccessful() {
        final double expectedRemainingAmount = FIRST_DEPOSIT_AMOUNT - (WITHDRAWAL_AMOUNT + BankAccountWithFee.WITHDRAWAL_FEE);
        bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), WITHDRAWAL_AMOUNT);
        assertEquals(expectedRemainingAmount, bankAccount.getBalance());
    }

    @Test
    void testCannotWithdrawNegativeAmounts() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(accountHolder.id(), NEGATIVE_AMOUNT));
    }

    @Test
    void testWrongWithdrawalDoesNotHappen() {
        bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        bankAccount.withdraw(WRONG_USER_ID, WITHDRAWAL_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testExceedingWithdrawalDoesNotHappen() {
        final double withdrawalAmount = FIRST_DEPOSIT_AMOUNT + 1;
        bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), withdrawalAmount);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdrawalExceedingWithFeeDoesNotHappen() {
        bankAccount.deposit(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), FIRST_DEPOSIT_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
