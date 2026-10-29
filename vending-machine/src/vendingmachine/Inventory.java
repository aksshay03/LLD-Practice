package vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String, InventoryItem> inventory = new HashMap<>();

    public void addProduct(InventoryItem inventoryItem) {
        if (inventoryItem == null)
            throw new IllegalArgumentException("InventoryItem cannot be null or blank");
        if (inventory.containsKey(inventoryItem.getProduct().getProductId()))
            throw new IllegalArgumentException("Cannot overwrite the existing product, if you want to add quantity call refill stock");
        inventory.put(inventoryItem.getProduct().getProductId(), inventoryItem);
    }

    public boolean isProductAvailable(String productId) {
        if (productId == null || productId.isBlank())
            throw new IllegalArgumentException("productId cannot be null");
        if (inventory.containsKey(productId)) {
            InventoryItem item = inventory.get(productId);
            if (item.getQuantity() > 0)
                return true;
        }
        return false;
    }

    public void reduceStock(String productId, int reduceStockQuantity) {
        if (productId == null || productId.isBlank())
            throw new IllegalArgumentException("productId cannot be null or blank");
        if (!inventory.containsKey(productId))
            throw new IllegalArgumentException("Cannot reduce the stock for unavailable product");
        InventoryItem item = inventory.get(productId);
        item.reduceQuantity(reduceStockQuantity);
    }

    public void refillStock(String productId, int refillQuantity) {
        if (productId == null || productId.isBlank())
            throw new NullPointerException("productId cannot be null");
        if (!inventory.containsKey(productId))
            throw new IllegalArgumentException("Cannot refill the unavailable stock, first add the product to the inventory");

        InventoryItem item = inventory.get(productId);
        item.addQuantity(refillQuantity);
    }

    public Product getProduct(String productId) {
        if (productId == null || productId.isBlank())
            throw new IllegalArgumentException("productId cannot be null or blank");

        if (inventory.containsKey(productId)) {
            InventoryItem item = inventory.get(productId);
            return item.getProduct();
        }
        throw new IllegalArgumentException("Cannot find the productId " + productId);
    }
}
