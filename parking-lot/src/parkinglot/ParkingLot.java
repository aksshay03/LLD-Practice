package parkinglot;

import java.util.List;

public class ParkingLot {
    private List<ParkingFloor> parkingFloors;
    private TicketService ticketService;
    private FeeCalculator feeCalculator;

    public ParkingLot(List<ParkingFloor> parkingFloors,TicketService ticketService,FeeCalculator feeCalculator) {
        this.parkingFloors = parkingFloors;
        this.ticketService = ticketService;
        this.feeCalculator = feeCalculator;
    }

    public Ticket parkVehicle(Vehicle vehicle)
    {
        for(ParkingFloor floor : parkingFloors)
        {
            ParkingSpot spot = floor.findSpot(vehicle);
            if(spot!=null)
            {
                spot.occupy();
                return ticketService.createTicket(vehicle,spot);
            }
        }
        return null;
    }
    public double unparkVehicle(Ticket ticket)
    {
        ticket.setExitTime();
        double fee = feeCalculator.calculateFee(ticket);
        ticket.getParkingSpot().release();
        ticketService.closeTicket(ticket);
        return fee;
    }
}
