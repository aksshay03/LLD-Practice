package atm;

public class AuthenticationState implements ATMState{
    private final ATM machine;
    private int failedAttempts=0;
    public AuthenticationState(ATM machine)
    {
        this.machine = machine;
    }
    @Override
    public void insertCard() {
        throw  new IllegalStateException("Cannot insertCard when the machine is in Authentication State");
    }

    @Override
    public void authenticate(String pin) {
        if(pin == null ||pin.isBlank())
            throw new IllegalArgumentException("Pin cannot be null or blank");
        if(!pin.equals("1234") && failedAttempts<3)
        {
            failedAttempts++;
            if(failedAttempts==3)
            {
                System.out.println("Incorrect Pin, 0 Attempts left, Please try again after sometime.");
                machine.setState(new IdleState(machine));
                return;
            }
            int attemptsLeft = 3 - failedAttempts;
            System.out.println("Incorrect Pin. Please try Again. "+ attemptsLeft + " Attempt Left");
            return;
        }
        System.out.println("Authenticated Successfully");
        machine.setState(new AuthenticatedState(machine));
    }

    @Override
    public void checkBalance() {
        throw  new IllegalStateException("Cannot checkBalance when the machine is in Authentication State");
    }

    @Override
    public void withdraw(double amount) {
        throw  new IllegalStateException("Cannot withdraw when the machine is in Authentication State");
    }

    @Override
    public void deposit(double amount) {
        throw  new IllegalStateException("Cannot deposit when the machine is in Authentication State");
    }

    @Override
    public void ejectCard() {
        machine.setState(new IdleState(machine));
    }
}
