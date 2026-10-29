package vendingmachine;

public class DispensingState implements VendingMachineState{
    private final VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }
    @Override
    public void selectProduct(String productId) {
        throw new IllegalStateException("Cannot Select Product while Dispensing Product");
    }

    @Override
    public void insertMoney(double amount) {
        throw new IllegalStateException("Cannot make payment while Dispensing Product");
    }

    @Override
    public double dispenseProduct() {
        double balanceAmount  = machine.calculateBalance();
        machine.reduceInventoryStock();
        machine.resetPayment();
        machine.resetProductSelection();
        machine.setState(new IdleState(machine));
        return balanceAmount;
    }

    @Override
    public double cancelTransaction() {
        throw new IllegalStateException("Cannot cancel Transaction while Dispensing Product");
    }
}
