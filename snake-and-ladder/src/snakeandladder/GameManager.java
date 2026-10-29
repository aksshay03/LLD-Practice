package snakeandladder;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private final List<Player> playerList;
    private final Board board;
    private final DiceRoller dice;
    private Player winner;
    private boolean gameOver;
    private int currentPlayerIndex;

    public GameManager(List<Player> playerList, Board board, DiceRoller dice) {
        if(playerList==null || playerList.size()<2)
            throw new IllegalArgumentException("Invalid Player List");
        if(board==null)
            throw new IllegalArgumentException("Invalid Board");
        if(dice == null)
            throw new IllegalArgumentException("Invalid dice");
        for(Player player : playerList)
        {
            if(player == null)
                throw new IllegalArgumentException("Player object can't be null");
        }
        this.playerList = new ArrayList<>(playerList);
        this.board = board;
        this.dice = dice;
        this.winner = null;
        this.gameOver = false;
    }

    public void playTurn()
    {
        if(gameOver)
            return;
        Player currentPlayer = playerList.get(currentPlayerIndex);
        int currentPosition = currentPlayer.getPosition();
        System.out.println(currentPlayer.getPlayerName() +" is in "+ currentPosition+" Position");
        int diceRoll = dice.roll();
        System.out.println("Rolling number "+ diceRoll);
        int landingPosition = board.resolvePosition(currentPosition,diceRoll);
        currentPlayer.moveTo(landingPosition);
        System.out.println(currentPlayer.getPlayerName() +" moved to "+ currentPlayer.getPosition()+" Position");
        if(landingPosition == board.getSize()) {
            winner = currentPlayer;
            gameOver = true;
        }
        else
        {
            currentPlayerIndex = (currentPlayerIndex+1) % playerList.size();
        }
    }

    public void playGame()
    {
        while(!gameOver)
        {
            playTurn();

        }
    }

    public Player getWinner()
    {
        return winner;
    }
    public boolean isGameOver()
    {
        return gameOver;
    }

}
