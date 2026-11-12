package elevatorsystem;

import java.util.TreeSet;
import java.util.UUID;

public class Elevator {
    private final String elevatorId;
    private Direction direction;
    private ElevatorState elevatorState;
    private final Door door = new Door();
    private int currentFloor;
    private final int maximumPassengerCount;
    private int currentPassengerCount;
    private TreeSet<Integer> upwardRequest= new TreeSet<>();
    private TreeSet<Integer> downwardRequest=new TreeSet<>();


    public Elevator()
    {
        this.elevatorId = UUID.randomUUID().toString();
        this.direction  = Direction.IDLE;
        this.elevatorState = ElevatorState.IDLE;
        this.currentFloor = 0;
        this.maximumPassengerCount = 8;
        this.currentPassengerCount = 0;
    }

    public void addUpwardRequest(int floor)
    {
        if(floor< 0 || floor > 9)
            throw new IllegalArgumentException("Floor should be 0-9");
        if(currentFloor> floor)
            throw new IllegalArgumentException("Requested floor cannot be added to upward Request");
        if(floor == currentFloor  ) {
            if(!door.isOpen())door.open();
            if(elevatorState != ElevatorState.DOOR_OPEN)elevatorState = ElevatorState.DOOR_OPEN;
            return;
        }
        upwardRequest.add(floor);
    }
    public void addDownwardRequest(int floor)
    {
        if(floor< 0 || floor > 9)
            throw new IllegalArgumentException("Floor should be 0-9");
        if(currentFloor< floor)
            throw new IllegalArgumentException("Requested floor cannot be added to Downward Request");
        if(floor == currentFloor ) {
            if(!door.isOpen())door.open();
            if(elevatorState != ElevatorState.DOOR_OPEN)elevatorState = ElevatorState.DOOR_OPEN;
            return;
        }
        downwardRequest.add(floor);
    }

    public void moveUp()
    {
        if(currentFloor==9)
            throw new IllegalStateException("Cannot Move the elevator up");
        if(door.isOpen())door.close();
        elevatorState = ElevatorState.MOVING;
        direction = Direction.UP;
        currentFloor+=1;
    }
    public void moveDown()
    {
        if(currentFloor==0)
            throw new IllegalStateException("Cannot Move the elevator down");
        if(door.isOpen())door.close();
        elevatorState = ElevatorState.MOVING;
        direction = Direction.DOWN;
        currentFloor-=1;
    }
    public void startMovingUp()
    {
        if(door.isOpen())
            throw new IllegalStateException("Cannot move up while the door is Open");
        elevatorState = ElevatorState.MOVING;
        direction     = Direction.UP;
    }
    public void startMovingDown()
    {
        if(door.isOpen())
            throw new IllegalStateException("Cannot move down while the door is Open");
        elevatorState = ElevatorState.MOVING;
        direction     = Direction.DOWN;
    }
    public void stopMoving()
    {
        elevatorState = ElevatorState.IDLE;
        direction     = Direction.IDLE;
    }

    public void openDoor()
    {
        elevatorState = ElevatorState.DOOR_OPEN;
        door.open();
    }
    public void closeDoor()
    {
        door.close();
    }

    public boolean isUpwardRequestComplete()
    {
        if(upwardRequest.isEmpty())
            return true;
        return false;
    }
    public boolean isDownwardRequestComplete()
    {
        if(downwardRequest.isEmpty())
            return true;
        return false;
    }
    public int getNextRequestedFloor()
    {
        if(direction == Direction.UP && !upwardRequest.isEmpty())
            return upwardRequest.first();
        else if(direction == Direction.DOWN && !downwardRequest.isEmpty())
            return downwardRequest.last();
        return -1;
    }

    public void removeFloor(int floor)
    {
        if(direction == Direction.UP && !upwardRequest.isEmpty())
            upwardRequest.remove(floor);
        else if(direction == Direction.DOWN && !downwardRequest.isEmpty())
            downwardRequest.remove(floor);
    }
}
