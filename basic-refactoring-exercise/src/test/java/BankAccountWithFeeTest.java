import example.model.api.AccountHolder;
import example.model.api.BankAccount;
import example.model.impl.BankAccountWithFee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the BankAccountWithFeeTest implementation
 */
class BankAccountWithFeeTest extends AbstractBankAccountTest {

    @BeforeEach
    void beforeEach() {
        final String name = "Mario";
        final String surname = "Rossi";
        final int id = 1;
        final double initialBalance = 0;
        final AccountHolder accountHolder = new AccountHolder(name, surname, id);
        final BankAccount bankAccount = new BankAccountWithFee(accountHolder, initialBalance);
        super.initTestEnvironment(accountHolder, bankAccount);
    }

    @Test
    void testWithdrawalIsSuccessful() {
        final double withdrawalAmount = 70.0;
        final double expectedFee = 1.0;
        final double expectedRemainingAmount = FIRST_DEPOSIT_AMOUNT - (withdrawalAmount + expectedFee);
        super.getBankAccount().deposit(super.getAccountHolder().id(), FIRST_DEPOSIT_AMOUNT);
        super.getBankAccount().withdraw(super.getAccountHolder().id(), withdrawalAmount);
        assertEquals(expectedRemainingAmount, super.getBankAccount().getBalance());
    }

    @Test
    void testWithdrawalExceedingWithFeeDoesNotHappen() {
        super.getBankAccount().deposit(super.getAccountHolder().id(), FIRST_DEPOSIT_AMOUNT);
        super.getBankAccount().withdraw(super.getAccountHolder().id(), FIRST_DEPOSIT_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, super.getBankAccount().getBalance());
    }

}
