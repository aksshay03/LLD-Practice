package librarymanagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {
    private final double finePerDay;

    public FineCalculator(double finePerDay) {
        this.finePerDay = finePerDay;
    }

    public double calculateFine(BorrowRecord borrowRecord)
    {
        double totalfine = 0.0;
        LocalDate returnDate = borrowRecord.getReturnDate();
        LocalDate dueDate    = borrowRecord.getDueDate();

        int lateDays = (int)ChronoUnit.DAYS.between(dueDate,returnDate);

        if(lateDays < 1)
            totalfine = 0.0;
        else
            totalfine = lateDays * finePerDay;

        return totalfine;
    }
}
