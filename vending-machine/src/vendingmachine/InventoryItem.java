package vendingmachine;

public class InventoryItem {
    private final Product product;
    private int quantity;

    public InventoryItem(Product product, int quantity) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null");
        if (quantity < 0)
            throw new IllegalArgumentException("Initial quantity cannot be negative");
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void addQuantity(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity cannot be less than or equal to 0");
        if (quantity > Integer.MAX_VALUE - this.quantity)
            throw new IllegalArgumentException("Quantity exceed maximum supported value");
        this.quantity += quantity;
    }

    public void reduceQuantity(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity cannot be less than or equal to 0");
        if (this.quantity - quantity < 0)
            throw new IllegalStateException("Available stock cannot go negative");
        this.quantity -= quantity;
    }
}
