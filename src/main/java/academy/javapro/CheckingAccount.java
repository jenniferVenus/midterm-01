package academy.javapro;

/**
 * CheckingAccount class extending the abstract Account class.
 * Features overdraft protection and transaction fees.
 */
public class CheckingAccount extends Account {
    private double overdraftLimit;
    private static final double TRANSACTION_FEE = 1.5; // Fee per withdrawal

    /**
     * Constructor for creating a new checking account.
     *
     * @param accountNumber The account number
     * @param customerName The name of the account holder
     * @param initialBalance The initial balance
     * @param overdraftLimit The maximum allowed overdraft
     */
    public CheckingAccount(String accountNumber, String customerName, double initialBalance, double overdraftLimit) {
        super(accountNumber, customerName, initialBalance); // Call to the parent constructor
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Getter for overdraft limit.
     *
     * @return The overdraft limit
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
        
    }

    /**
     * Sets a new overdraft limit.
     *
     * @param overdraftLimit The new overdraft limit
     */
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;

        System.out.println("Overdraft limit has been updated to: $" + String.format("%.2f", overdraftLimit));
    }

    /**
     * Overrides the withdraw method with checking account-specific rules.
     * Implements overdraft protection and applies transaction fees.
     */
    @Override
    public void withdraw(double amount) {

        if (getBalance() - amount >= -overdraftLimit) 
        {

            double newBalance = getBalance() - amount - TRANSACTION_FEE;
            setBalance(newBalance); 
            

            System.out.println("Withdrew $" + String.format("%.2f", amount) + " from checking account.");
            System.out.println("Transaction fee: $" + String.format("%.2f", TRANSACTION_FEE));
            System.out.println("Account is in overdraft. Current balance: $" + String.format("%.2f", getBalance()));

            logTransaction("WITHDRAWAL", amount); 
            logTransaction("FEE", TRANSACTION_FEE);
    
        } else 
            {
                System.out.println("Overdraft limit exceeded. There are insufficient funds to execute withdrawal.");
            }   
    }

    /**
     * Overrides the displayInfo method to include checking account-specific information.
     */
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call to the parent method
        System.out.println("Account Type: Checking Account");
        System.out.println("Overdraft Limit: $" + String.format("%.2f", overdraftLimit));
        System.out.println("Transaction Fee: $" + String.format("%.2f", TRANSACTION_FEE));
    }
}
