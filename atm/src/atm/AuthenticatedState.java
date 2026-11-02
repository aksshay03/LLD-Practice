package atm;

public class AuthenticatedState implements ATMState{
    private final ATM machine;
    private final BankAccount bankAccount;
    public AuthenticatedState(ATM machine)
    {
        this.machine = machine;
        this.bankAccount = machine.getBankAccount();
    }
    @Override
    public void insertCard() {
        throw  new IllegalStateException("Cannot insertCard when the machine is in Authenticated State");
    }

    @Override
    public void authenticate(String pin) {
        throw  new IllegalStateException("Cannot authenticate when the machine is in Authenticated State");
    }

    @Override
    public void checkBalance() {
       double balance = bankAccount.getBalance();
       System.out.println("Current Balance is Rs."+ balance);
    }

    @Override
    public void withdraw(double amount) {
        bankAccount.withdraw(amount);
    }

    @Override
    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    @Override
    public void ejectCard() {
        machine.setState(new IdleState(machine));
    }
}
