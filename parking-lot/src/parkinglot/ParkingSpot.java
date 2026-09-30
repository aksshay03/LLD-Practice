package parkinglot;

public class ParkingSpot {
    private int id;
    private SpotType spotType;
    private boolean occupied;

    public ParkingSpot(int id, SpotType spotType )
    {
        this.id=id;
        this.spotType=spotType;
        this.occupied = false;
    }
    public int getId() {
        return id;
    }
    public SpotType getSpotType() {
        return spotType;
    }
    public boolean canFit(Vehicle vehicle)
    {
        if(spotType == spotType.valueOf(vehicle.getVehicleType().name()))
        {
            return true;
        }
        return false;
    }
    public boolean isOccupied()
    {
        return occupied;
    }
    public void occupy()
    {
        if(!occupied)
            occupied=true;
    }
    public void release()
    {
        if(occupied)
            occupied =false;
    }
}
