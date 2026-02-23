package example.model;

public class BankAccountWithFee extends AbstractBankAccount {

    public static final double WITHDRAWAL_FEE = 1.0;

    public BankAccountWithFee(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        super.withdraw(userID, amount + WITHDRAWAL_FEE);
    }

}
