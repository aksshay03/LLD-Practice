package vendingmachine;

public interface VendingMachineState {
    void selectProduct(String productId);
    void insertMoney(double amount);
    double dispenseProduct();
    double cancelTransaction();
}
