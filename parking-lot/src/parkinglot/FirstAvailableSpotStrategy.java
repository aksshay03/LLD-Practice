package parkinglot;

import java.util.List;

public class FirstAvailableSpotStrategy implements SpotAssignmentStrategy{

    public ParkingSpot findSpot(List<ParkingSpot> parkingSpotList, Vehicle vehicle)
    {
        for(ParkingSpot spot : parkingSpotList )
        {
            if(!spot.isOccupied() && spot.canFit(vehicle))
            {
                return spot;
            }
        }
        return null;
    }
}
