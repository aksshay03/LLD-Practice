package snakeandladder;

public class Player {
    private final String playerId;
    private final String playerName;
    private int position;

    public Player(String playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.position=0;
    }

    public int getPosition() {
        return position;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerId() {
        return playerId;
    }
    public void moveTo(int position)
    {
        this.position=position;
    }
}
