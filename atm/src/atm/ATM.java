package atm;

public class ATM {
    private ATMState state;
    private final BankAccount bankAccount;

    public ATM()
    {
        this.state = new IdleState(this);
        this.bankAccount = new BankAccount(1000);
    }
    void setState(ATMState state)
    {
        this.state = state;
    }
    BankAccount getBankAccount() {
        return bankAccount;
    }
    public void insertCard()
    {
        state.insertCard();
    }
    public void authenticate(String pin)
    {
        state.authenticate(pin);
    }
    public void checkBalance()
    {
        state.checkBalance();
    }
    public void withdraw(double amount)
    {
        state.withdraw(amount);
    }
    public void deposit(double amount)
    {
        state.deposit(amount);
    }
    public void ejectCard()
    {
        state.ejectCard();
    }
}
