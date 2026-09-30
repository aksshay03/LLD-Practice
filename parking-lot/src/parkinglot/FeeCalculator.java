package parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;

public class FeeCalculator {
    public double calculateFee(Ticket ticket)
    {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = ticket.getExitTime();
        double duration = Duration.between(entryTime,exitTime).toMinutes();

        double fee = Math.max(1,Math.ceil(duration/60)) * 20;
        return fee;
    }
}
