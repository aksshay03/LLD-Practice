package atm;

public class Main {

    public static void main(String[] args) {

        // Test 1: Normal ATM flow and balance persistence
        System.out.println("\n--- Test 1: Normal ATM Flow ---");

        ATM atm = new ATM();

        atm.insertCard();
        atm.authenticate("1234");

        atm.checkBalance();  // Expected: 1000
        atm.deposit(500);
        atm.withdraw(200);
        atm.checkBalance();  // Expected: 1300

        atm.ejectCard();

        atm.insertCard();
        atm.authenticate("1234");
        atm.checkBalance();  // Expected: 1300

        atm.ejectCard();


        // Test 2: Three incorrect PIN attempts
        System.out.println("\n--- Test 2: Three Incorrect PIN Attempts ---");

        ATM atm2 = new ATM();

        atm2.insertCard();
        atm2.authenticate("1111");
        atm2.authenticate("2222");
        atm2.authenticate("3333");

        // ATM should now be in IdleState.
        expectException(
                IllegalStateException.class,
                atm2::checkBalance,
                "Balance check rejected after three incorrect PIN attempts"
        );

        // A new session should be possible.
        atm2.insertCard();
        atm2.authenticate("1234");
        atm2.checkBalance();  // Expected: 1000

        atm2.ejectCard();


        // Test 3: Insufficient balance
        System.out.println("\n--- Test 3: Insufficient Balance ---");

        ATM atm3 = new ATM();

        atm3.insertCard();
        atm3.authenticate("1234");

        double balanceBefore = atm3.getBankAccount().getBalance();

        expectException(
                IllegalArgumentException.class,
                () -> atm3.withdraw(2000),
                "Withdrawal exceeding balance rejected"
        );

        double balanceAfter = atm3.getBankAccount().getBalance();

        if (balanceBefore == balanceAfter) {
            System.out.println("PASS: Balance unchanged after failed withdrawal");
        } else {
            System.out.println("FAIL: Balance changed after failed withdrawal");
        }

        atm3.ejectCard();


        // Test 4: Invalid deposit amounts
        System.out.println("\n--- Test 4: Invalid Deposits ---");

        ATM atm4 = new ATM();

        atm4.insertCard();
        atm4.authenticate("1234");

        expectException(
                IllegalArgumentException.class,
                () -> atm4.deposit(0),
                "Zero deposit rejected"
        );

        expectException(
                IllegalArgumentException.class,
                () -> atm4.deposit(-100),
                "Negative deposit rejected"
        );

        atm4.ejectCard();


        // Test 5: Invalid withdrawal amounts
        System.out.println("\n--- Test 5: Invalid Withdrawals ---");

        ATM atm5 = new ATM();

        atm5.insertCard();
        atm5.authenticate("1234");

        expectException(
                IllegalArgumentException.class,
                () -> atm5.withdraw(0),
                "Zero withdrawal rejected"
        );

        expectException(
                IllegalArgumentException.class,
                () -> atm5.withdraw(-100),
                "Negative withdrawal rejected"
        );

        atm5.ejectCard();


        // Test 6: Operations while the ATM is idle
        System.out.println("\n--- Test 6: Operations While Idle ---");

        ATM atm6 = new ATM();

        expectException(
                IllegalStateException.class,
                atm6::checkBalance,
                "Balance check rejected while idle"
        );

        expectException(
                IllegalStateException.class,
                () -> atm6.withdraw(100),
                "Withdrawal rejected while idle"
        );

        expectException(
                IllegalStateException.class,
                () -> atm6.deposit(100),
                "Deposit rejected while idle"
        );

        System.out.println("\nAll test cases executed!");
    }


    // Helper method to check expected exceptions
    private static void expectException(
            Class<? extends RuntimeException> expectedException,
            Runnable operation,
            String testName) {

        try {
            operation.run();

            System.out.println("FAIL: " + testName
                    + " - Expected " + expectedException.getSimpleName());

        } catch (RuntimeException e) {

            if (expectedException.isInstance(e)) {
                System.out.println("PASS: " + testName);
            } else {
                System.out.println("FAIL: " + testName
                        + " - Expected " + expectedException.getSimpleName()
                        + ", but got " + e.getClass().getSimpleName());
            }
        }
    }
}