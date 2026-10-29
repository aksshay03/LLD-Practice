package vendingmachine;

public class IdleState implements VendingMachineState {
    private final VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(String productID) {
        if (productID == null || productID.isBlank())
            throw new IllegalArgumentException("ProductId cannot be empty or blank");

        Product foundProduct = machine.findProduct(productID);
        if(!machine.isProductAvailable(productID))
            throw new IllegalArgumentException("the Product is out of stock");
        machine.setSelectedProduct(foundProduct);
        machine.setState(new PaymentPendingState(machine));
    }

    @Override
    public void insertMoney(double amount) {
        throw new IllegalStateException("Cannot make payment before selecting the product");
    }

    @Override
    public double dispenseProduct() {
        throw new IllegalStateException("Cannot dispense product for the current state");
    }

    @Override
    public double cancelTransaction() {
        throw new IllegalStateException("Cannot Cancel now as the transaction is not yet started");
    }
}
