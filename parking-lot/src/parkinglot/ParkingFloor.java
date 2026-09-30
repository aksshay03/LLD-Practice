package parkinglot;

public class ParkingFloor {
    private int floorId;
    private ParkingSpotManager parkingSpotManager;

    public ParkingFloor(int floorId, ParkingSpotManager parkingSpotManager) {
        this.floorId = floorId;
        this.parkingSpotManager = parkingSpotManager;
    }

     public int getFloorId() {
        return floorId;
    }

    public ParkingSpot findSpot(Vehicle vehicle)
    {
       return parkingSpotManager.findSpot(vehicle);
    }
}
