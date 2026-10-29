package snakeandladder;

public class BoardTest {

    public static void main(String[] args) {
        testSnakeResolution();
        testLadderResolution();
        testNormalMovement();
        testOvershooting();
        testDuplicateJumpStart();
        testInvalidBoardSize();

        System.out.println("\nAll Board tests passed!");
    }

    private static void testSnakeResolution() {
        Board board = new Board(100);
        board.addJump(new BoardJump(18, 7));

        int result = board.resolvePosition(14, 4);

        assertEquals(7, result, "Snake resolution");
    }

    private static void testLadderResolution() {
        Board board = new Board(100);
        board.addJump(new BoardJump(12, 34));

        int result = board.resolvePosition(10, 2);

        assertEquals(34, result, "Ladder resolution");
    }

    private static void testNormalMovement() {
        Board board = new Board(100);

        int result = board.resolvePosition(20, 3);

        assertEquals(23, result, "Normal movement");
    }

    private static void testOvershooting() {
        Board board = new Board(100);

        int result = board.resolvePosition(98, 5);

        assertEquals(98, result, "Overshooting");
    }

    private static void testDuplicateJumpStart() {
        Board board = new Board(100);
        board.addJump(new BoardJump(18, 7));

        assertThrows(
                IllegalArgumentException.class,
                () -> board.addJump(new BoardJump(18, 30)),
                "Duplicate jump start"
        );
    }

    private static void testInvalidBoardSize() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Board(0),
                "Invalid board size"
        );
    }

    private static void assertEquals(
            int expected, int actual, String testName) {

        if (expected != actual) {
            throw new AssertionError(
                    testName + " failed. Expected: "
                            + expected + ", Actual: " + actual
            );
        }

        System.out.println("[PASS] " + testName);
    }

    private static void assertThrows(
            Class<? extends Exception> expectedException,
            Runnable action,
            String testName) {

        try {
            action.run();
        } catch (Exception exception) {
            if (expectedException.isInstance(exception)) {
                System.out.println("[PASS] " + testName);
                return;
            }

            throw new AssertionError(
                    testName + " failed. Unexpected exception: "
                            + exception.getClass().getSimpleName(),
                    exception
            );
        }

        throw new AssertionError(
                testName + " failed. Expected exception: "
                        + expectedException.getSimpleName()
        );
    }
}
