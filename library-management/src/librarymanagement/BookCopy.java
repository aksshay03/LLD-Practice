package librarymanagement;

public class BookCopy {
    private String copyID;
    private CopyStatus copyStatus;

    public BookCopy(String copyID) {
        this.copyID = copyID;
        this.copyStatus = CopyStatus.AVAILABLE;
    }

    public String getCopyID() {
        return copyID;
    }

    public boolean isAvailable()
    {
        return this.copyStatus == CopyStatus.AVAILABLE;
    }

    public void markBorrowed()
    {
        this.copyStatus = CopyStatus.BORROWED;
    }

    public void markAvailable()
    {
        this.copyStatus = CopyStatus.AVAILABLE;
    }

}
