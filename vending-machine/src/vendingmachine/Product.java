package vendingmachine;

public class Product {
    private final String productId;
    private final String productName;
    private final double productPrice;

    public Product(String productId, String productName, double productPrice) {
        if (productId == null || productId.isBlank())
            throw new IllegalArgumentException("ProductId cannot be null or empty");
        if (productName == null || productName.isBlank())
            throw new IllegalArgumentException("ProductName cannot be null or empty");
        if (!Double.isFinite(productPrice) || productPrice < 0.0)
            throw new IllegalArgumentException("productPrice cannot be infinite and negative");
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public double getProductPrice() {
        return productPrice;
    }

}
