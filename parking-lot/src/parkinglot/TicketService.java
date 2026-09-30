package parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TicketService {
    private Map<String, Ticket> activeTickets = new HashMap<>();

    public Ticket createTicket(Vehicle vehicle, ParkingSpot parkingSpot)
    {
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(parkingSpot,vehicle,ticketId);
        activeTickets.put(ticketId,ticket);
        return ticket;
    }
    public void closeTicket(Ticket ticket)
    {
        String ticketId = ticket.getTicketId();
        if(activeTickets.containsKey(ticketId))
        {
            ticket.setExitTime();
            activeTickets.remove(ticketId);
        }
    }
}
