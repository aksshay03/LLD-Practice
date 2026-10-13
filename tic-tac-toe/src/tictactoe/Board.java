package tictactoe;

import java.util.Arrays;

public class Board {
    private final int size;
    private final Symbol[][] board;
    public Board(int size)
    {
        this.size=size;
        this.board = new Symbol[size][size];
        for(Symbol[] row:board)
            Arrays.fill(row,Symbol.EMPTY);
    }
    public boolean placeSymbol(int row,int col, Symbol symbol)
    {
        if( ( row<0 || row >= size || col<0 || col>=size) || !board[row][col].equals( Symbol.EMPTY)) {
            return false;
        }
        board[row][col] = symbol;
        return true;
    }
    public boolean hasWon(Symbol symbol)
    {
        //check rows
        for(int row=0;row<size;row++){
            boolean rowWon=true;
            for(int col=0;col<size;col++) {
                if (!board[row][col].equals(symbol)) {
                    rowWon = false;
                    break;
                }

            }
            if (rowWon)
                return true;
        }
        //check cols
        for(int col=0;col<size;col++){
            boolean colwon = true;
            for(int row=0;row<size;row++) {
                if (!board[row][col].equals(symbol)) {
                    colwon = false;
                    break;
                }
            }
            if (colwon)
                return true;
        }
        //left to right diagonal
        boolean mainDiagonalWon=true;
        for(int row=0;row<size;row++)
        {
           if (!board[row][row].equals(symbol))
           {
               mainDiagonalWon = false;
               break;
           }

        }
        if(mainDiagonalWon)
            return true;
        //right to left diagonal
        boolean sideDiagonalWon=true;
        for(int row=0;row<size;row++)
        {
            if(!board[row][size-1-row].equals(symbol)) {
                sideDiagonalWon = false;
                break;
            }

        }
        if(sideDiagonalWon)
            return true;

        return false;
    }

    public  boolean isFull()
    {
        for(int row = 0; row<size; row++)
        {
            for(int col = 0; col<size; col++)
            {
                if(board[row][col].equals(Symbol.EMPTY))
                {
                   return false;
                }
            }
        }
        return true;
    }

}
