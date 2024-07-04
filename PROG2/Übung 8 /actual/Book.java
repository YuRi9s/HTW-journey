import java.time.LocalDate;

/**
 * The Book class represents a book in the library.
 * It contains details such as title, author, year of publication, number of
 * pages,
 * genre, rating, and whether the book is currently borrowed.
 */
public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int year;
    private int pages;
    private String genre;
    private double rating;
    private boolean borrowed;
    private LocalDate returnDate;

    /**
     * Constructs a Book object with the specified details.
     *
     * @param title  The title of the book.
     * @param author The author of the book.
     * @param year   The year the book was published.
     * @param pages  The number of pages in the book.
     * @param genre  The genre of the book.
     * @param rating The rating of the book (1.0 to 5.0).
     */
    public Book(String title, String author, int year, int pages, String genre, double rating) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.genre = genre;
        this.rating = rating;
        this.borrowed = false;
        this.returnDate = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", borrowed=" + borrowed +
                ", returnDate=" + returnDate +
                '}';
    }
}
