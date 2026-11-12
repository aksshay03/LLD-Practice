package elevatorsystem;

import java.util.TreeSet;
import java.util.UUID;

public class Elevator {
    private String elevatorId;
    private Direction direction;
    private ElevatorState elevatorState;
    private Door door = new Door();
    private int currentFloor;
    private int maximumPassengerCount;
    private int currentPassengerCount;
    private TreeSet<Integer> upwardRequest= new TreeSet<>();
    private TreeSet<Integer> downwardRequest=new TreeSet<>();


    public Elevator()
    {
        this.elevatorId = UUID.randomUUID().toString();
        this.direction  = Direction.IDLE;
        this.elevatorState = ElevatorState.IDLE;
        door.close();
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
}
