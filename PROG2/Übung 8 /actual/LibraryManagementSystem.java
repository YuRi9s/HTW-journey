import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The LibraryManagementSystem class manages the library's collection of books
 * and users.
 * It provides methods to manage borrowing, returning, and querying books.
 */
public class LibraryManagementSystem {
    private SortedSet<Book> books;
    private Map<String, User> users; // please change to int // user ste to null TODO fix soon
    private PriorityQueue<Book> borrowedBooks;

    /**
     * Constructs a LibraryManagementSystem object.
     */
    public LibraryManagementSystem() {
        this.books = new TreeSet<>();
        this.users = new HashMap<>();
        this.borrowedBooks = new PriorityQueue<>(Comparator.comparing(Book::getReturnDate));
    }

    /**
     * Adds a book to the library's collection.
     *
     * @param book The book to be added.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    // TODO please document this
    public SortedSet<Book> getBooks() {
        return books;
    }

    // TODO please document this
    public Map<String, User> getUsers() {
        return users;
    }

    // TODO please document this
    public PriorityQueue<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Adds a user to the library system.
     *
     * @param user The user to be added.
     */
    public void addUser(User user) {
        users.put(user.getReaderID(), user);
    }

    /**
     * Borrows a book for a user.
     *
     * @param readerID   The reader ID of the user.
     * @param book       The book to be borrowed.
     * @param returnDate The return date for the borrowed book.
     */

    // TODO change here
    public void borrowBook(String readerID, Book book, LocalDate returnDate) {
        User user = users.get(readerID);
        if (user != null && books.contains(book) && !book.isBorrowed()) {
            book.setReturnDate(returnDate);
            user.borrowBook(book);
            borrowedBooks.add(book);
        }
    }

    /**
     * Gets the list of books borrowed by a user.
     *
     * @param readerID The reader ID of the user.
     * @return The list of books borrowed by the user.
     */
    public List<Book> getBooksBorrowedByUser(String readerID) {
        User user = users.get(readerID);
        if (user != null) {
            return new ArrayList<>(user.getBorrowedBooks());
        }
        return Collections.emptyList();
    }

    /**
     * Gets the list of all borrowed books.
     *
     * @return The list of all borrowed books.
     */
    public List<Book> getAllBorrowedBooks() {
        return borrowedBooks.stream().collect(Collectors.toList());
    }

    /**
     * Gets the list of books published after a specific year.
     *
     * @param year The year to filter books by.
     * @return The list of books published after the specified year.
     */
    public List<Book> getBooksPublishedAfter(int year) {
        return books.stream()
                .filter(book -> book.getYear() > year)
                .collect(Collectors.toList());
    }

    /**
     * Gets the list of books sorted by the number of pages.
     *
     * @return The list of books sorted by pages.
     */
    public List<Book> getBooksSortedByPages() {
        return books.stream().sorted(Comparator.comparingInt(Book::getPages)).collect(Collectors.toList());
    }

    /**
     * Gets the total number of pages in all books in the library.
     *
     * @return The total number of pages.
     */
    // TODO reduce geht auch
    public int getTotalPages() {
        return books.stream().mapToInt(Book::getPages).sum();
    }

    /**
     * Gets the list of books in a specific genre.
     *
     * @param genre The genre to filter books by.
     * @return The list of books in the specified genre.
     */
    public List<Book> getBooksByGenre(String genre) {
        return books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    /**
     * Gets the average rating of books in a specific genre.
     *
     * @param genre The genre to filter books by.
     * @return The average rating of books in the specified genre.
     */
    // TODO chnage this the question is no correct use grouping
    public double getAverageRatingByGenre(String genre) {
        return books.stream().filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .mapToDouble(Book::getRating).average().orElse(0.0);
    }

    /**
     * Gets the top-rated books in the library.
     *
     * @param topN The number of top-rated books to retrieve.
     * @return The list of top-rated books.
     */

    public List<Book> getTopRatedBooks(int topN) {
        return books.stream().sorted(Comparator.comparingDouble(Book::getRating).reversed()).limit(topN)
                .collect(Collectors.toList());
    }

    /**
     * Gets the authors with the most books in the library.
     *
     * @return A map of authors and their respective number of books.
     */
    //TODO this is incorrect 
    public Map<String, Long> getAuthorsWithMostBooks() {
        return books.stream().collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()));
    }

    /**
     * Gets the list of books sorted by rating.
     *
     * @return The list of books sorted by rating.
     */
    public List<Book> getBooksSortedByRating() {
        return books.stream().sorted(Comparator.comparingDouble(Book::getRating).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Gets a filtered and sorted list of books based on the provided filter and
     * sorter.
     *
     * @param filter The predicate to filter books.
     * @param sorter The comparator to sort books.
     * @return The filtered and sorted list of books.
     */
    public List<Book> getFilteredAndSortedBooks(Predicate<Book> filter, Comparator<Book> sorter) {
        return books.stream().filter(filter).sorted(sorter).collect(Collectors.toList());
    }
}
