package parkinglot;

import java.util.List;

public interface SpotAssignmentStrategy {
    ParkingSpot findSpot(List<ParkingSpot> parkingSpotList, Vehicle vehicle);
}
