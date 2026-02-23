import example.model.api.AccountHolder;
import example.model.api.BankAccount;
import example.model.impl.SimpleBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest extends AbstractBankAccountTest {

    @BeforeEach
    void beforeEach() {
        final String name = "Mario";
        final String surname = "Rossi";
        final int id = 1;
        final AccountHolder accountHolder = new AccountHolder(name, surname, id);
        final BankAccount bankAccount = new SimpleBankAccount(accountHolder, AbstractBankAccountTest.INITIAL_BALANCE);
        super.setTestEnvironment(accountHolder, bankAccount);
    }

    @Test
    void testWithdrawalIsSuccessful() {
        final double expectedRemainingAmount = FIRST_DEPOSIT_AMOUNT - WITHDRAWAL_AMOUNT;
        super.getBankAccount().deposit(super.getAccountHolder().id(), FIRST_DEPOSIT_AMOUNT);
        super.getBankAccount().withdraw(super.getAccountHolder().id(), WITHDRAWAL_AMOUNT);
        assertEquals(expectedRemainingAmount, super.getBankAccount().getBalance());
    }

}
