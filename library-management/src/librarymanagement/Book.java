package librarymanagement;

import java.util.HashMap;
import java.util.Map;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private BookCategory category;
    private Map<String, BookCopy> copies;

    public Book( String ISBN,String title,String author, BookCategory category) {
        this.isbn = ISBN;
        this.title = title;
        this.author = author;
        this.category = category;
        this.copies = new HashMap<>();
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookCategory getCategory() {
        return category;
    }
    public void addCopy(BookCopy copy)
    {
        copies.put(copy.getCopyID(), copy);
    }
    public BookCopy getAvailableCopy()
    {
        for(BookCopy bookcopy:copies.values())
        {
            if(bookcopy.isAvailable())
                return bookcopy;
        }
        return null;
    }
}
