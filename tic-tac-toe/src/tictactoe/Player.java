package tictactoe;
public class Player {
    private final String playerId;
    private final String name;
    private final Symbol symbol;

    public Player(String playerId, String name, Symbol symbol) {
        this.playerId = playerId;
        this.name = name;
        this.symbol = symbol;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
