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

    private static final double EXPECTED_FEE = 1;

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
        final double expectedRemainingAmount = FIRST_DEPOSIT_AMOUNT - (WITHDRAWAL_AMOUNT + EXPECTED_FEE);
        super.getBankAccount().deposit(super.getAccountHolder().id(), FIRST_DEPOSIT_AMOUNT);
        super.getBankAccount().withdraw(super.getAccountHolder().id(), WITHDRAWAL_AMOUNT);
        assertEquals(expectedRemainingAmount, super.getBankAccount().getBalance());
    }

    @Test
    void testWithdrawalExceedingWithFeeDoesNotHappen() {
        super.getBankAccount().deposit(super.getAccountHolder().id(), FIRST_DEPOSIT_AMOUNT);
        super.getBankAccount().withdraw(super.getAccountHolder().id(), FIRST_DEPOSIT_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, super.getBankAccount().getBalance());
    }

}
