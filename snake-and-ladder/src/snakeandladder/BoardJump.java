package snakeandladder;

public class BoardJump {
    private final int startPosition;
    private final int endPosition;

    public BoardJump(int startPosition, int endPosition) {
        if(startPosition<1 || endPosition < 1)
            throw new IllegalArgumentException("Position must me positive");
        if(startPosition == endPosition)
            throw new IllegalArgumentException("Start and end position can't be same");
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
    public int getStartPosition() {
        return startPosition;
    }
    public int getEndPosition() {
        return endPosition;
    }
}
