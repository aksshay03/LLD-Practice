package librarymanagement;

import librarymanagement.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, Member> members = new HashMap<>();
        Map<String, Book> books = new HashMap<>();
        Map<String, BorrowRecord> borrowRecords = new HashMap<>();
        Map<String, BorrowRecord> activeBorrowRecords = new HashMap<>();
        FineCalculator fineCalculator = new FineCalculator(10.0);

        Library library = new Library(members,books,borrowRecords,activeBorrowRecords,fineCalculator);

        Member aksshay = new Member("Aksshay","aksshaykumar1998@gmail.com","9732389190");
        Member nitya = new Member("Nityasri","nityasri2001@gmail.com","9732389190");

        library.addMember(aksshay);
        library.addMember(nitya);

        Book cleanCode = new Book("ISBN001","Clean Code","Robert Martin",BookCategory.PROGRAMMING);
        BookCopy copy1 = new BookCopy("C001");
        BookCopy copy2 = new BookCopy("C002");

        cleanCode.addCopy(copy1);
        cleanCode.addCopy(copy2);

        library.addBook(cleanCode);

        List<Book> result  = library.searchBook("Clean Code");
        System.out.println("Book found "+result.size());

        BorrowRecord record1 = library.borrowBook(aksshay.getMemberID(),"ISBN001");
        System.out.println("Borrow ID:" + record1.getBorrowID());
        System.out.println("Due Date:"+ record1.getDueDate());
        System.out.println("Copy borrowed :"+record1.getBookCopy().getCopyID());

        BorrowRecord record2 = library.borrowBook(aksshay.getMemberID(),"ISBN001");
        System.out.println("Second Copy borrowed :"+record2.getBookCopy().getCopyID());

        library.borrowBook(aksshay.getMemberID(),"ISBN001");

        double fine = library.returnBook(record1.getBorrowID());
        System.out.println("Fine: Rs."+fine);

        BorrowRecord record3 = library.borrowBook(nitya.getMemberID(),"ISBN001");
        System.out.println("Nitya borrowed Copy:"+record3.getBookCopy().getCopyID());

        System.out.println("Fine: Rs."+ library.returnBook(record3.getBorrowID()) );

    }
}