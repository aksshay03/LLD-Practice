package atm;

public interface ATMState {
    void insertCard();
    void authenticate(String pin);
    void checkBalance();
    void withdraw(double amount);
    void deposit(double amount);
    void ejectCard();
}
