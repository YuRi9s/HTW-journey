import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * The LibraryCLI class provides a command-line interface for interacting with
 * the LibraryManagementSystem.
 * It allows users to perform operations such as adding books, adding users,
 * borrowing books,
 * returning books, and querying books.
 */
public class LibraryCLI {
    private LibraryManagementSystem lms;
    private Scanner scanner;

    /**
     * Constructs a LibraryCLI object.
     */
    public LibraryCLI() {
        this.lms = new LibraryManagementSystem();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the command-line interface.
     */
    public void start() {
        loadBooksFromCSV("books.csv");

        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display All Borrowed Books");
            System.out.println("6. Display Books Borrowed by User");
            System.out.println("7. Display Books Published After Year");
            System.out.println("8. Display Books Sorted by Pages");
            System.out.println("9. Display Total Pages");
            System.out.println("10. Display Books by Genre");
            System.out.println("11. Display Average Rating by Genre");
            System.out.println("12. Display Top Rated Books");
            System.out.println("13. Display Authors with Most Books");
            System.out.println("14. Display Books Sorted by Rating");
            System.out.println("15. Display Filtered and Sorted Books");
            System.out.println("16. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addUser();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    displayAllBorrowedBooks();
                    break;
                case 6:
                    displayBooksBorrowedByUser();
                    break;
                case 7:
                    displayBooksPublishedAfterYear();
                    break;
                case 8:
                    displayBooksSortedByPages();
                    break;
                case 9:
                    displayTotalPages();
                    break;
                case 10:
                    displayBooksByGenre();
                    break;
                case 11:
                    displayAverageRatingByGenre();
                    break;
                case 12:
                    displayTopRatedBooks();
                    break;
                case 13:
                    displayAuthorsWithMostBooks();
                    break;
                case 14:
                    displayBooksSortedByRating();
                    break;
                case 15:
                    displayFilteredAndSortedBooks();
                    break;
                case 16:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            printSeparator();
        }
    }

    /**
     * Prints a separator line.
     */
    private void printSeparator() {
        System.out.println("\n------------------------------------------------------------\n");
    }

    /**
     * Prompts the user to add a book to the library.
     */
    private void addBook() {
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter author: ");
        String author = scanner.nextLine();
        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        System.out.println("Enter pages: ");
        int pages = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.println("Enter rating (1.0 to 5.0): ");
        double rating = scanner.nextDouble();

        Book book = new Book(title, author, year, pages, genre, rating);
        lms.addBook(book);
        System.out.println("Book added successfully.");
    }

    /**
     * Prompts the user to add a user to the library system.
     */
    private void addUser() {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter reader ID: ");
        String readerID = scanner.nextLine();

        User user = new User(name, readerID);
        lms.addUser(user);
        System.out.println("User added successfully.");
    }

    /**
     * Prompts the user to borrow a book from the library.
     */
    private void borrowBook() {
        System.out.println("Enter reader ID: ");
        String readerID = scanner.nextLine();
        System.out.println("Enter book title: ");
        String title = scanner.nextLine();
        System.out.println("Enter return date (YYYY-MM-DD): ");
        LocalDate returnDate = LocalDate.parse(scanner.nextLine());

        Book book = lms.getBooks().stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
        if (book != null) {
            lms.borrowBook(readerID, book, returnDate);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    /**
     * Prompts the user to return a borrowed book to the library.
     */
    private void returnBook() {
        System.out.println("Enter reader ID: ");
        String readerID = scanner.nextLine();
        System.out.println("Enter book title: ");
        String title = scanner.nextLine();

        User user = lms.getUsers().get(readerID);
        Book book = lms.getBooks().stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
        if (user != null && book != null) {
            user.returnBook(book);
            lms.getBorrowedBooks().remove(book);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("User or book not found.");
        }
    }

    /**
     * Displays all borrowed books.
     */
    private void displayAllBorrowedBooks() {
        List<Book> books = lms.getAllBorrowedBooks();
        printBooksTable(books, true);
    }

    /**
     * Displays the books borrowed by a specific user.
     */
    private void displayBooksBorrowedByUser() {
        System.out.println("Enter reader ID: ");
        String readerID = scanner.nextLine();
        List<Book> books = lms.getBooksBorrowedByUser(readerID);
        printBooksTable(books, true);
    }

    /**
     * Displays the books published after a specific year.
     */
    private void displayBooksPublishedAfterYear() {
        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        List<Book> books = lms.getBooksPublishedAfter(year);
        printBooksTable(books, false);
    }

    /**
     * Displays the books sorted by the number of pages.
     */
    private void displayBooksSortedByPages() {
        List<Book> books = lms.getBooksSortedByPages();
        printBooksTable(books, false);
    }

    /**
     * Displays the total number of pages in all books in the library.
     */
    private void displayTotalPages() {
        System.out.println("Total pages: " + lms.getTotalPages());
    }

    /**
     * Displays the books in a specific genre.
     */
    private void displayBooksByGenre() {
        System.out.println("Enter genre: ");
        String genre = scanner.nextLine();
        List<Book> books = lms.getBooksByGenre(genre);
        printBooksTable(books, false);
    }

    /**
     * Displays the average rating of books in a specific genre.
     */
    private void displayAverageRatingByGenre() {
        System.out.println("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.println("Average rating: " + lms.getAverageRatingByGenre(genre));
    }

    /**
     * Displays the top-rated books.
     */
    private void displayTopRatedBooks() {
        System.out.println("Enter number of top-rated books to display: ");
        int topN = scanner.nextInt();
        List<Book> books = lms.getTopRatedBooks(topN);
        printBooksTable(books, false);
    }

    /**
     * Displays the authors with the most books in the library.
     */
    private void displayAuthorsWithMostBooks() {
        lms.getAuthorsWithMostBooks().forEach((author, count) -> System.out.println(author + ": " + count + " books"));
    }

    /**
     * Displays the books sorted by rating.
     */
    private void displayBooksSortedByRating() {
        List<Book> books = lms.getBooksSortedByRating();
        printBooksTable(books, false);
    }

    /**
     * Displays the filtered and sorted books based on user input.
     */
    private void displayFilteredAndSortedBooks() {
        System.out.println("Enter genre to filter by (leave blank for no filter): ");
        String genre = scanner.nextLine().trim();

        System.out.println("Sort by: 1) Rating 2) Year 3) Title");
        int sortOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Predicate<Book> filter = genre.isEmpty() ? book -> true : book -> book.getGenre().equalsIgnoreCase(genre);
        Comparator<Book> sorter;

        switch (sortOption) {
            case 1:
                sorter = Comparator.comparingDouble(Book::getRating).reversed();
                break;
            case 2:
                sorter = Comparator.comparingInt(Book::getYear);
                break;
            case 3:
                sorter = Comparator.comparing(Book::getTitle);
                break;
            default:
                System.out.println("Invalid sort option. Defaulting to sort by title.");
                sorter = Comparator.comparing(Book::getTitle);
                break;
        }

        List<Book> books = lms.getFilteredAndSortedBooks(filter, sorter);
        printBooksTable(books, false);
    }

    /**
     * Loads books from a CSV file.
     *
     * @param fileName The name of the CSV file.
     */
    private void loadBooksFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header line
                }
                String[] values = line.split(",");
                String title = values[0];
                String author = values[1];
                int year = Integer.parseInt(values[2]);
                int pages = Integer.parseInt(values[3]);
                String genre = values[4];
                double rating = Double.parseDouble(values[5]);

                Book book = new Book(title, author, year, pages, genre, rating);
                lms.addBook(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method to start the CLI.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        LibraryCLI cli = new LibraryCLI();
        cli.start();
    }

    /**
     * Prints the list of books in a table format.
     *
     * @param books      The list of books to print.
     * @param withReturn Whether to include the return date column.
     */
    private void printBooksTable(List<Book> books, boolean withReturn) {
        String format = withReturn ? "| %-20s | %-20s | %-4s | %-5s | %-10s | %-5s | %-12s |\n"
                : "| %-20s | %-20s | %-4s | %-5s | %-10s | %-5s |\n";
        String line = withReturn
                ? "+----------------------+----------------------+------+"
                        + "-------+------------+-------+--------------+\n"
                : "+----------------------+----------------------+------+" +
                        "-------+------------+-------+\n";

        System.out.print(line);
        System.out.printf(format, "Title", "Author", "Year", "Pages", "Genre", "Rating",
                withReturn ? "Return Date" : "");
        System.out.print(line);

        for (Book book : books) {
            if (withReturn) {
                System.out.printf(format, book.getTitle(), book.getAuthor(), book.getYear(),
                        book.getPages(), book.getGenre(), book.getRating(),
                        book.getReturnDate() != null ? book.getReturnDate().toString() : "N/A");
            } else {
                System.out.printf(format, book.getTitle(), book.getAuthor(), book.getYear(),
                        book.getPages(), book.getGenre(), book.getRating());
            }
        }
        System.out.print(line);
    }
}
