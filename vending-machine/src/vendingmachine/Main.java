package vendingmachine;

public class Main {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("========== VENDING MACHINE TESTS ==========\n");

        testSuccessfulPurchaseWithChange();
        testExactPayment();
        testInsufficientPaymentAndCancellation();
        testCancelBeforePayment();
        testCancelAfterPartialPayment();
        testSuccessfulPurchaseAfterCancellation();
        testOutOfStockProduct();
        testUnknownProduct();
        testSelectAnotherProductDuringTransaction();
        testInsertMoneyBeforeSelectingProduct();
        testDispenseBeforeSufficientPayment();
        testCancelWhenMachineIsIdle();
        testInvalidMoneyAmounts();
        testStockReductionAfterPurchase();
        testRefillStock();
        testPaymentDoesNotCarryOver();
        testProductAvailability();

        System.out.println("\n========== TEST SUMMARY ==========");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));

        if (failed > 0) {
            System.out.println("\nSome tests failed. Review the output above.");
        } else {
            System.out.println("\nAll tests passed!");
        }
    }

    private static VendingMachine createMachine() {
        Inventory inventory = new Inventory();

        Product coke = new Product("P1", "Coke", 40.0);
        Product chips = new Product("P2", "Chips", 20.0);
        Product water = new Product("P3", "Water", 15.0);
        Product juice = new Product("P4", "Juice", 30.0);

        inventory.addProduct(new InventoryItem(coke, 5));
        inventory.addProduct(new InventoryItem(chips, 3));
        inventory.addProduct(new InventoryItem(water, 2));
        inventory.addProduct(new InventoryItem(juice, 0));

        return new VendingMachine(inventory);
    }

    private static void check(String testName, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + testName);
        } else {
            failed++;
            System.out.println("[FAIL] " + testName);
        }
    }

    private static boolean approximatelyEqual(double actual, double expected) {
        return Math.abs(actual - expected) < 0.000001;
    }

    private static boolean throwsException(
            Runnable operation,
            Class<? extends RuntimeException> expectedException) {

        try {
            operation.run();
            return false;
        } catch (RuntimeException exception) {
            return expectedException.isInstance(exception);
        }
    }

    // Test 1: Successful purchase with change
    private static void testSuccessfulPurchaseWithChange() {
        VendingMachine machine = createMachine();

        machine.selectProduct("P1");
        machine.insertMoney(50.0);

        double change = machine.dispenseProduct();

        check("Purchase with change: ₹50 for ₹40 product",
                approximatelyEqual(change, 10.0));
    }

    // Test 2: Exact payment
    private static void testExactPayment() {
        VendingMachine machine = createMachine();

        machine.selectProduct("P1");
        machine.insertMoney(40.0);

        double change = machine.dispenseProduct();

        check("Exact payment: ₹40 for ₹40 product",
                approximatelyEqual(change, 0.0));
    }

    // Test 3: Insufficient payment followed by cancellation
    private static void testInsufficientPaymentAndCancellation() {
        VendingMachine machine = createMachine();

        machine.selectProduct("P2");
        machine.insertMoney(10.0);

        double refund = machine.cancelTransaction();

        check("Insufficient payment: refund partial payment",
                approximatelyEqual(refund, 10.0));
    }

    // Test 4: Cancel after selecting product but before inserting money
    private static void testCancelBeforePayment() {
        VendingMachine machine = createMachine();

        machine.selectProduct("P1");

        double refund = machine.cancelTransaction();

        check("Cancel before payment: refund is zero",
                approximatelyEqual(refund, 0.0));
    }

    // Test 5: Insert money in multiple installments, then cancel
    private static void testCancelAfterPartialPayment() {
        VendingMachine machine = createMachine();

        machine.selectProduct("P1");
        machine.insertMoney(10.0);
        machine.insertMoney(5.0);

        double refund = machine.cancelTransaction();

        check("Cancel after multiple partial payments: refund ₹15",
                approximatelyEqual(refund, 15.0));
    }

    // Test 6: Start a new transaction after cancelling a previous one
    private static void testSuccessfulPurchaseAfterCancellation() {
        VendingMachine machine = createMachine();

        machine.selectProduct("P2");
        machine.insertMoney(10.0);
        machine.cancelTransaction();

        machine.selectProduct("P2");
        machine.insertMoney(20.0);

        double change = machine.dispenseProduct();

        check("Purchase succeeds after previous transaction was cancelled",
                approximatelyEqual(change, 0.0));
    }

    // Test 7: Out-of-stock product
    private static void testOutOfStockProduct() {
        VendingMachine machine = createMachine();

        boolean exceptionThrown = throwsException(
                () -> machine.selectProduct("P4"),
                IllegalArgumentException.class);

        check("Cannot select an out-of-stock product", exceptionThrown);
    }

    // Test 8: Unknown product ID
    private static void testUnknownProduct() {
        VendingMachine machine = createMachine();

        boolean exceptionThrown = throwsException(
                () -> machine.selectProduct("UNKNOWN"),
                IllegalArgumentException.class);

        check("Cannot select a product that does not exist",
                exceptionThrown);
    }

    // Test 9: Cannot select another product during a transaction
    private static void testSelectAnotherProductDuringTransaction() {
        VendingMachine machine = createMachine();

        machine.selectProduct("P1");

        boolean exceptionThrown = throwsException(
                () -> machine.selectProduct("P2"),
                IllegalStateException.class);

        check("Cannot select another product while a transaction is active",
                exceptionThrown);
    }

    // Test 10: Cannot insert money before selecting a product
    private static void testInsertMoneyBeforeSelectingProduct() {
        VendingMachine machine = createMachine();

        boolean exceptionThrown = throwsException(
                () -> machine.insertMoney(10.0),
                IllegalStateException.class);

        check("Cannot insert money before selecting a product",
                exceptionThrown);
    }

    // Test 11: Cannot dispense before sufficient payment
    private static void testDispenseBeforeSufficientPayment() {
        VendingMachine machine = createMachine();

        machine.selectProduct("P1");
        machine.insertMoney(10.0);

        boolean exceptionThrown = throwsException(
                () -> machine.dispenseProduct(),
                IllegalStateException.class);

        check("Cannot dispense when payment is insufficient",
                exceptionThrown);

        // Clean up the transaction.
        machine.cancelTransaction();
    }

    // Test 12: Cannot cancel when machine is idle
    private static void testCancelWhenMachineIsIdle() {
        VendingMachine machine = createMachine();

        boolean exceptionThrown = throwsException(
                () -> machine.cancelTransaction(),
                IllegalStateException.class);

        check("Cannot cancel when no transaction is active",
                exceptionThrown);
    }

    // Test 13: Invalid money amounts
    private static void testInvalidMoneyAmounts() {
        VendingMachine machine = createMachine();
        machine.selectProduct("P1");

        boolean zeroRejected = throwsException(
                () -> machine.insertMoney(0.0),
                IllegalArgumentException.class);

        boolean negativeRejected = throwsException(
                () -> machine.insertMoney(-10.0),
                IllegalArgumentException.class);

        boolean nanRejected = throwsException(
                () -> machine.insertMoney(Double.NaN),
                IllegalArgumentException.class);

        boolean infinityRejected = throwsException(
                () -> machine.insertMoney(Double.POSITIVE_INFINITY),
                IllegalArgumentException.class);

        check("Reject zero, negative, NaN and infinite payments",
                zeroRejected && negativeRejected
                        && nanRejected && infinityRejected);

        machine.cancelTransaction();
    }

    // Test 14: Verify stock decreases after a purchase
    private static void testStockReductionAfterPurchase() {
        Inventory inventory = new Inventory();
        Product coke = new Product("P1", "Coke", 40.0);

        inventory.addProduct(new InventoryItem(coke, 2));

        VendingMachine machine = new VendingMachine(inventory);

        machine.selectProduct("P1");
        machine.insertMoney(40.0);
        machine.dispenseProduct();

        machine.selectProduct("P1");
        machine.insertMoney(40.0);
        machine.dispenseProduct();

        boolean outOfStock = throwsException(
                () -> machine.selectProduct("P1"),
                IllegalArgumentException.class);

        check("Stock decreases after each purchase and reaches zero",
                outOfStock);
    }

    // Test 15: Refill inventory
    private static void testRefillStock() {
        Inventory inventory = new Inventory();
        Product coke = new Product("P1", "Coke", 40.0);

        inventory.addProduct(new InventoryItem(coke, 0));

        boolean unavailableInitially = !inventory.isProductAvailable("P1");

        inventory.refillStock("P1", 2);

        boolean availableAfterRefill = inventory.isProductAvailable("P1");

        check("Refilling stock makes an unavailable product available",
                unavailableInitially && availableAfterRefill);
    }

    // Test 16: Previous payment must not carry over
    private static void testPaymentDoesNotCarryOver() {
        VendingMachine machine = createMachine();

        machine.selectProduct("P1");
        machine.insertMoney(10.0);
        machine.cancelTransaction();

        machine.selectProduct("P1");
        machine.insertMoney(20.0);

        boolean stillInsufficient = throwsException(
                () -> machine.dispenseProduct(),
                IllegalStateException.class);

        check("Payment from a cancelled transaction does not carry over",
                stillInsufficient);

        machine.cancelTransaction();
    }

    // Test 17: Product availability
    private static void testProductAvailability() {
        VendingMachine machine = createMachine();

        // Product availability is checked through Inventory.
        // This test confirms that the machine rejects an unavailable product.
        boolean unavailableRejected = throwsException(
                () -> machine.selectProduct("P4"),
                IllegalArgumentException.class);

        check("Unavailable product is rejected",
                unavailableRejected);
    }
}