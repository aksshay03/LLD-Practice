package snakeandladder;

import java.util.Arrays;
import java.util.List;

public class GameManagerTest {

    public static void main(String[] args) {
        testWinnerIsRecorded();
        testNoTurnsAfterWinning();
        testTurnRotation();

        System.out.println("\nAll GameManager tests passed!");
    }

    private static void testWinnerIsRecorded() {
        Player aksshay = new Player("P1", "Aksshay");
        Player nitya = new Player("P2", "Nitya");

        // Put Aksshay one step away from winning.
        aksshay.moveTo(99);

        GameManager game = createGame(
                Arrays.asList(aksshay, nitya),
                new FixedDice(1)
        );

        game.playTurn();

        assertEquals(
                100, aksshay.getPosition(),
                "Winner reaches position 100"
        );
        assertSame(
                aksshay, game.getWinner(),
                "Winner is recorded correctly"
        );
        assertTrue(
                game.isGameOver(),
                "Game ends after a win"
        );
    }

    private static void testNoTurnsAfterWinning() {
        Player aksshay = new Player("P1", "Aksshay");
        Player nitya = new Player("P2", "Nitya");

        aksshay.moveTo(99);

        GameManager game = createGame(
                Arrays.asList(aksshay, nitya),
                new FixedDice(1)
        );

        game.playTurn(); // Aksshay wins.

        int nityaPositionBefore = nitya.getPosition();

        game.playTurn(); // Must do nothing.

        assertEquals(
                nityaPositionBefore, nitya.getPosition(),
                "No player moves after the game ends"
        );
        assertSame(
                aksshay, game.getWinner(),
                "Winner remains unchanged"
        );
    }

    private static void testTurnRotation() {
        Player aksshay = new Player("P1", "Aksshay");
        Player nitya = new Player("P2", "Nitya");

        GameManager game = createGame(
                Arrays.asList(aksshay, nitya),
                new FixedDice(3, 4)
        );

        game.playTurn();

        assertEquals(
                3, aksshay.getPosition(),
                "First player's turn"
        );
        assertEquals(
                0, nitya.getPosition(),
                "Second player waits for their turn"
        );

        game.playTurn();

        assertEquals(
                4, nitya.getPosition(),
                "Second player's turn"
        );
    }

    private static GameManager createGame(
            List<Player> players, DiceRoller dice) {
        return new GameManager(
                players,
                new Board(100),
                dice
        );
    }

    private static void assertEquals(
            int expected, int actual, String testName) {
        if (expected != actual) {
            throw new AssertionError(
                    testName + ": expected " + expected
                            + ", got " + actual
            );
        }
        System.out.println("[PASS] " + testName);
    }

    private static void assertSame(
            Object expected, Object actual, String testName) {
        if (expected != actual) {
            throw new AssertionError(testName);
        }
        System.out.println("[PASS] " + testName);
    }

    private static void assertTrue(
            boolean actual, String testName) {
        if (!actual) {
            throw new AssertionError(testName);
        }
        System.out.println("[PASS] " + testName);
    }
}
