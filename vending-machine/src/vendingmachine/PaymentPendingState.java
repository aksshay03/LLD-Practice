package vendingmachine;

public class PaymentPendingState implements VendingMachineState {
    private final VendingMachine machine;

    public PaymentPendingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(String productId) {
        throw new IllegalStateException("Cannot Select Product while making payment");
    }

    @Override
    public void insertMoney(double amount) {
        machine.makePayment(amount);
        if( !machine.isPaymentSufficient())
        {
            System.out.println("Amount paid is not sufficent to withdraw the product" );
            return;
        }
       machine.setState(new DispensingState(machine));
    }

    @Override
    public double dispenseProduct() {
        throw new IllegalStateException("Cannot Dispense Product while making payment");
    }

    @Override
    public double cancelTransaction() {
        double refundMoney = machine.getRefundAmount();
        machine.resetProductSelection();
        machine.setState(new IdleState(machine));
        return refundMoney;
    }
}
