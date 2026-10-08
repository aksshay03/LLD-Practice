package librarymanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<String,Member> members;
    private Map<String,Book> books;
    private Map<String, BorrowRecord> borrowRecords;
    private Map<String, BorrowRecord> activeBorrowRecords;
    private FineCalculator fineCalculator;
    private final static int BORROW_LIMIT = 7;

    public Library(Map<String, Member> members, Map<String, Book> books, Map<String, BorrowRecord> borrowRecords, Map<String, BorrowRecord> activeBorrowRecords,FineCalculator fineCalculator) {
        this.members = members;
        this.books = books;
        this.borrowRecords = borrowRecords;
        this.activeBorrowRecords = activeBorrowRecords;
        this.fineCalculator = fineCalculator;
    }

    public void addMember(Member member)
    {
        members.put(member.getMemberID(), member);
    }

    public void addBook(Book book)
    {
        books.put(book.getIsbn(),book);
    }
    public List<Book> searchBook(String title)
    {
        List<Book> result = new ArrayList<>();
        for(Book bookToFind:books.values())
        {
            if(bookToFind.getTitle().equalsIgnoreCase(title))
                result.add(bookToFind);
        }
        return result;
    }
    private int getActiveBorrowCount(String memberId)
    {
        int count=0;
        for(BorrowRecord activeBorrowRecord: activeBorrowRecords.values()){
            if(activeBorrowRecord.getMember().getMemberID().equals(memberId))
                count++;
        }
        return count;
    }
    private boolean isMember(String memberId)
    {
        return members.containsKey(memberId);
    }
    public BorrowRecord borrowBook(String memberID, String isbn)
    {
        //1. check if the member is a member of this library
        if(!isMember(memberID)){
            System.out.println("Member not found");
            return null;
        }
            //2. Check if the member crossed the borrow limit
            int borrowCount = getActiveBorrowCount(memberID);
            if(borrowCount>=BORROW_LIMIT){
                System.out.println("You currently have "+ borrowCount + "Books. Cannot borrow more than "+ BORROW_LIMIT+" Books.");
                return null;
            }
            //3. Search the book the Book collection
                Book book = books.get(isbn);
                if(book != null)
                {
                    BookCopy bookCopy = book.getAvailableCopy(); //4. check if the book has a copy available
                    if(bookCopy == null){
                        System.out.println("No copies available to borrow");
                        return null;
                    }
                    bookCopy.markBorrowed(); //5. Mark the copy as borrowed
                    BorrowRecord borrowRecord = new BorrowRecord(members.get(memberID),bookCopy); //6. create a new borrow record the member
                    borrowRecords.put(borrowRecord.getBorrowID(),borrowRecord);
                    activeBorrowRecords.put(borrowRecord.getBorrowID(),borrowRecord);
                    return borrowRecord;
                }

        return null;
    }

    public double returnBook(String borrowRecordId)
    {
        BorrowRecord borrowRecord = borrowRecords.get(borrowRecordId);
        if(borrowRecord==null)
            return 0.0;
        if(borrowRecord.getReturnDate()!=null)
            return 0.0;
        borrowRecord.setReturnDate(LocalDate.now());
        double fine = fineCalculator.calculateFine(borrowRecord);
        borrowRecord.setFine(fine);
        borrowRecord.getBookCopy().markAvailable();
        activeBorrowRecords.remove(borrowRecordId);
        return fine;
    }

}
