package atm;

public class BankAccount {
    private double balance;
    public BankAccount(double initialBalance)
    {
        if(!Double.isFinite(initialBalance) || initialBalance < 0)
            throw new IllegalArgumentException("initialBalance must be finite and non-negative");
        this.balance = initialBalance;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount)
    {
        if(!Double.isFinite(amount) || amount <= 0)
            throw new IllegalArgumentException("Deposit amount must be finite and non-negative");
        balance+=amount;
    }
    public void withdraw(double amount)
    {
        if(!Double.isFinite(amount) || amount <= 0)
            throw new IllegalArgumentException("Withdraw amount must be finite and non-negative");
        if(amount>balance)
            throw new IllegalArgumentException("Insufficient Balance");
        balance-=amount;
    }

}
