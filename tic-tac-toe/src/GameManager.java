public class GameManager {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player winner;
    private States gameState;

    public GameManager(Board board, Player player1, Player player2 ) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.winner = null;
        this.gameState = States.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Player getWinner() {
        return winner;
    }

    public boolean play(int row, int col)
    {
        if(gameState !=States.IN_PROGRESS) return false;
        if(!board.placeSymbol(row,col,currentPlayer.getSymbol())) return false;
        if(board.hasWon(currentPlayer.getSymbol()))
        {
            winner = currentPlayer;
            gameState = States.WIN;
            return true;
        }
        if(board.isFull())
        {
            gameState=States.DRAW;
            return true;
        }
        switchPlayers();
        return true;
    }

    private void switchPlayers()
    {
        if(currentPlayer==player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }
}
