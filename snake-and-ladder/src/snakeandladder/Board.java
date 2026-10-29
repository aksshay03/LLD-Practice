package snakeandladder;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int size;

    private final Map<Integer,BoardJump> jumps = new HashMap<>();

    public Board(int size) {
        if(size<2)
            throw new IllegalArgumentException("Board size cannot be less than 2");
        this.size = size;
    }
    public int getSize() {
        return size;
    }
    public void addJump(BoardJump jump)
    {
        if(jump==null) throw new IllegalArgumentException("Jump Argument can't be null");
        if(jump.getStartPosition()>size || jump.getEndPosition()>size)
            throw new IllegalArgumentException("Positions cannot exceed the size of the board");
        if(jumps.containsKey(jump.getStartPosition()))
            throw new IllegalArgumentException("Starting Position already added, try other starting position");
        if(jumps.containsKey(jump.getEndPosition()))
            throw new IllegalArgumentException("End Position is already other jump start position");
        jumps.put(jump.getStartPosition(),jump);
    }
    public int resolvePosition(int currentPosition, int diceRoll)
    {
        if(diceRoll > 6 || diceRoll <1 || currentPosition <0 || currentPosition >size)
            throw new IllegalArgumentException("Invalid current position or dice number");
        int nextPosition = currentPosition + diceRoll;
        if(nextPosition>size)
            return currentPosition;
        if(jumps.containsKey(nextPosition)) {
            System.out.println("Steped on Ladder or Snake");
            return jumps.get(nextPosition).getEndPosition();
        }
        return nextPosition;
    }
}
