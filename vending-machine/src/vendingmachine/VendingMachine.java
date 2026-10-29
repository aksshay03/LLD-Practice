package vendingmachine;

public class VendingMachine {
    private final Inventory inventory;
    private Product product;
    private final Payment payment;
    private VendingMachineState state;

    public VendingMachine(Inventory inventory) {
        if (inventory == null)
            throw new IllegalArgumentException("Inventory cannot be null");
        this.inventory = inventory;
        this.state = new IdleState(this);
        this.product = null;
        this.payment = new Payment();
    }

    public void setState(VendingMachineState state) {
        if(state == null)
            throw new IllegalArgumentException("State cannot be null");
        this.state = state;
    }

    public void selectProduct(String productID) {
        state.selectProduct(productID);
    }

    public void insertMoney(double amount) {
        state.insertMoney(amount);
    }

    public double dispenseProduct() {
        return state.dispenseProduct();
    }

    public double cancelTransaction() {
        return state.cancelTransaction();
    }

    public Product findProduct(String productId) {
       Product foundProduct = inventory.getProduct(productId);
        if (foundProduct == null)
            throw new IllegalArgumentException("Product not found");
        return foundProduct;
    }

    public boolean isProductAvailable(String productId) {
        if (!inventory.isProductAvailable(productId))
            return false;
        return true;
    }

    public void setSelectedProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null");
        this.product = product;
    }
    public void makePayment(double amount)
    {
        payment.addMoney(amount);
    }
    public boolean isPaymentSufficient()
    {
        return payment.isSufficient(product.getProductPrice());
    }

    public double getRefundAmount()
    {
        return payment.refundMoney();
    }
    public void resetProductSelection()
    {
        product=null;
    }
    public void reduceInventoryStock()
    {
        inventory.reduceStock(product.getProductId(),1);
    }
    public double calculateBalance()
    {
        return payment.calculateBalance(product.getProductPrice());
    }
    public void resetPayment()
    {
        payment.reset();
    }
}
