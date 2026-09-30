package parkinglot;

import java.time.LocalDateTime;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(ParkingSpot parkingSpot, Vehicle vehicle, String ticketId) {
        this.entryTime = LocalDateTime.now();
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
        this.ticketId = ticketId;
        this.exitTime = null;

    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getExitTime() {
        return exitTime ;
    }

    public void setExitTime() {
       exitTime = LocalDateTime.now();
    }
}
