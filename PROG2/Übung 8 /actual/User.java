import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The User class represents a user of the library.
 * It contains details such as the user's name, reader ID, and a collection of
 * borrowed books.
 */
public class User {
    private String name;
    private String readerID;
    private SortedSet<Book> borrowedBooks;

    /**
     * Constructs a User object with the specified name and reader ID.
     *
     * @param name     The name of the user.
     * @param readerID The reader ID of the user.
     */
    public User(String name, String readerID) {
        this.name = name;
        this.readerID = readerID;
        this.borrowedBooks = new TreeSet<>((b1, b2) -> b1.getReturnDate().compareTo(b2.getReturnDate()));
    }

    /**
     * Borrows a book for the user.
     *
     * @param book The book to be borrowed.
     */
    public void borrowBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            borrowedBooks.add(book);
            book.setBorrowed(true);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public SortedSet<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(SortedSet<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    /**
     * Returns a book for the user.
     *
     * @param book The book to be returned.
     */
    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setBorrowed(false);
            book.setReturnDate(null);
        }
    }

    // Getters and setters for each attribute

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", readerID='" + readerID + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
