package snakeandladder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args)
    {
        Player player1 = new Player(UUID.randomUUID().toString(),"Aksshay");
        Player player2 = new Player(UUID.randomUUID().toString(),"Nitya");
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);

        Board board = new Board(100);
        BoardJump snake1 = new BoardJump(96,17);
        BoardJump snake2 = new BoardJump(32,5);
        BoardJump snake3 = new BoardJump(63,23);
        BoardJump ladder1 = new BoardJump(15,35);
        BoardJump ladder2 = new BoardJump(9,67);
        BoardJump ladder3 = new BoardJump(58,88);
        board.addJump(snake1);
        board.addJump(snake2);
        board.addJump(snake3);
        board.addJump(ladder1);
        board.addJump(ladder2);
        board.addJump(ladder3);

        Dice dice = new Dice(6);

        GameManager gameManager = new GameManager(playerList,board,dice);
        gameManager.playGame();

        System.out.println("And the winner is "+gameManager.getWinner().getPlayerName());
    }
}
