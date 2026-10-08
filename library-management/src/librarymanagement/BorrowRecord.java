package librarymanagement;

import java.time.LocalDate;
import java.util.UUID;

public class BorrowRecord {
    private String borrowID;
    private Member member;
    private BookCopy bookCopy;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;

    public BorrowRecord(Member member, BookCopy bookCopy) {
        this.borrowID = UUID.randomUUID().toString();
        this.member = member;
        this.bookCopy = bookCopy;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(7);
        this.returnDate = null;
    }

    public Member getMember() {
        return member;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public String getBorrowID() {
        return borrowID;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

}
