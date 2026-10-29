package vendingmachine;

public class Payment {

    private double totalamountpaid;

    public Payment() {
        this.totalamountpaid = 0.0;
    }

    public void addMoney(double amount) {
        if (!Double.isFinite(amount) || amount <= 0)
            throw new IllegalArgumentException("Amount cannot be less then or equal to 0");
        totalamountpaid += amount;
    }

    public boolean isSufficient(double price) {
        if (price > totalamountpaid)
            return false;
        return true;
    }

    public double calculateBalance(double price) {
        return Math.max(0.0, totalamountpaid - price);
    }

    public double refundMoney() {
        double refundamount = totalamountpaid;
        reset();
        return refundamount;
    }

    public double getAmountPaid() {
        return totalamountpaid;
    }

    public void reset() {
        totalamountpaid = 0.0;
    }
}
