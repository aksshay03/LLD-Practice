public class Main {

    private static GameManager createGame()
    {
        Player player1 = new Player("P001", "Aksshay", Symbol.X);
        Player player2 = new Player("P002", "Nitya", Symbol.O);
        Board board = new Board(3);
        return  new GameManager(board,player1,player2);
    }

public static void main(String[] args) {
    System.out.println("===== TEST 1: X wins by row =====");

    GameManager game = createGame();

    game.play(0, 0); // X
    game.play(1, 0); // O
    game.play(0, 1); // X
    game.play(1, 1); // O
    game.play(0, 2); // X

    System.out.println("Winner: "
            + game.getWinner().getName());

    System.out.println();


    // =====================================================
    // TEST 2: O wins by ROW
    // =====================================================

    System.out.println("===== TEST 2: O wins by row =====");

    game = createGame();

    game.play(0, 0); // X
    game.play(1, 0); // O
    game.play(0, 1); // X
    game.play(1, 1); // O
    game.play(2, 0); // X
    game.play(1, 2); // O

    System.out.println("Winner: "
            + game.getWinner().getName());

    System.out.println();


    // =====================================================
    // TEST 3: X wins by COLUMN
    // =====================================================

    System.out.println("===== TEST 3: X wins by column =====");

    game = createGame();

    game.play(0, 0); // X
    game.play(0, 1); // O
    game.play(1, 0); // X
    game.play(1, 1); // O
    game.play(2, 0); // X

    System.out.println("Winner: "
            + game.getWinner().getName());

    System.out.println();


    // =====================================================
    // TEST 4: O wins by COLUMN
    // =====================================================

    System.out.println("===== TEST 4: O wins by column =====");

    game = createGame();

    game.play(0, 0); // X
    game.play(0, 1); // O
    game.play(1, 0); // X
    game.play(1, 1); // O
    game.play(2, 2); // X
    game.play(2, 1); // O

    System.out.println("Winner: "
            + game.getWinner().getName());

    System.out.println();


    // =====================================================
    // TEST 5: X wins by MAIN DIAGONAL
    // =====================================================

    System.out.println("===== TEST 5: X wins by main diagonal =====");

    game = createGame();

    game.play(0, 0); // X
    game.play(0, 1); // O
    game.play(1, 1); // X
    game.play(0, 2); // O
    game.play(2, 2); // X

    System.out.println("Winner: "
            + game.getWinner().getName());

    System.out.println();


    // =====================================================
    // TEST 6: O wins by OPPOSITE DIAGONAL
    // =====================================================

    System.out.println("===== TEST 6: O wins by opposite diagonal =====");

    game = createGame();

    game.play(0, 0); // X
    game.play(0, 2); // O
    game.play(0, 1); // X
    game.play(1, 1); // O
    game.play(2, 1); // X
    game.play(2, 0); // O

    System.out.println("Winner: "
            + game.getWinner().getName());

    System.out.println();


    // =====================================================
    // TEST 7: DRAW
    // =====================================================

    System.out.println("===== TEST 7: Draw =====");

    game = createGame();

    game.play(0, 0); // X
    game.play(0, 1); // O
    game.play(0, 2); // X
    game.play(1, 1); // O
    game.play(1, 0); // X
    game.play(1, 2); // O
    game.play(2, 1); // X
    game.play(2, 0); // O
    game.play(2, 2); // X

    System.out.println("Winner: "
            + game.getWinner());

    System.out.println();


    // =====================================================
    // TEST 8: OCCUPIED CELL
    // =====================================================

    System.out.println("===== TEST 8: Occupied cell =====");

    game = createGame();

    System.out.println("First move: "
            + game.play(0, 0));

    // O tries to play in X's cell
    System.out.println("Occupied move: "
            + game.play(0, 0));

    System.out.println();


    // =====================================================
    // TEST 9: OUT OF BOUNDS
    // =====================================================

    System.out.println("===== TEST 9: Out of bounds =====");

    game = createGame();

    System.out.println("Negative row: "
            + game.play(-1, 0));

    System.out.println("Row too large: "
            + game.play(3, 0));

    System.out.println("Column too large: "
            + game.play(0, 3));

    System.out.println();


    // =====================================================
    // TEST 10: MOVE AFTER WIN
    // =====================================================

    System.out.println("===== TEST 10: Move after win =====");

    game = createGame();

    game.play(0, 0); // X
    game.play(1, 0); // O
    game.play(0, 1); // X
    game.play(1, 1); // O
    game.play(0, 2); // X wins

    // Game already over
    System.out.println("Move after win: "
            + game.play(2, 2));

    System.out.println();


    // =====================================================
    // TEST 11: WINNER STORED CORRECTLY
    // =====================================================

    System.out.println("===== TEST 11: Winner =====");

    game = createGame();

    game.play(0, 0);
    game.play(1, 0);
    game.play(0, 1);
    game.play(1, 1);
    game.play(0, 2);

    System.out.println("Winner ID: "
            + game.getWinner().getPlayerId());

    System.out.println("Winner name: "
            + game.getWinner().getName());

    System.out.println();


    // =====================================================
    // TEST 12: CURRENT PLAYER SWITCHING
    // =====================================================

    System.out.println("===== TEST 12: Current player =====");

    game = createGame();

    System.out.println("Initial player: "
            + game.getCurrentPlayer().getName());

    game.play(0, 0);

    System.out.println("After X move: "
            + game.getCurrentPlayer().getName());

    game.play(1, 0);

    System.out.println("After O move: "
            + game.getCurrentPlayer().getName());

    System.out.println();
}
}
