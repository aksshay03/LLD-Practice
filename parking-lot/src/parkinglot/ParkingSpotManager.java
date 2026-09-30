package parkinglot;

import java.util.List;

public class ParkingSpotManager {
    private List<ParkingSpot> parkingSpots;
    private SpotAssignmentStrategy strategy;
    public ParkingSpotManager(List<ParkingSpot> parkingSpots, SpotAssignmentStrategy strategy)
    {
        this.parkingSpots= parkingSpots;
        this.strategy = strategy;
    }
    public ParkingSpot findSpot(Vehicle vehicle) {
        return strategy.findSpot(parkingSpots,vehicle);
    }
}
