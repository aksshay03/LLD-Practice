package atm;

public class IdleState implements  ATMState{
    private final ATM machine;
    public IdleState(ATM machine)
    {
        this.machine = machine;
    }
    @Override
    public void insertCard() {
            machine.setState(new AuthenticationState(machine));
    }

    @Override
    public void authenticate(String pin) {
        throw  new IllegalStateException("Cannot Authenticate when the machine is Idle");
    }

    @Override
    public void checkBalance() {
        throw  new IllegalStateException("Cannot checkBalance when the machine is Idle");
    }

    @Override
    public void withdraw(double amount) {
        throw  new IllegalStateException("Cannot withdraw when the machine is Idle");
    }

    @Override
    public void deposit(double amount) {
        throw  new IllegalStateException("Cannot deposit when the machine is Idle");
    }

    @Override
    public void ejectCard() {
        throw  new IllegalStateException("Cannot ejectCard when the machine is Idle");
    }
}
