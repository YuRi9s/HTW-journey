#include <iostream>
#include <stdexcept>
#include <limits> // Include this header for std::numeric_limits
#include "Library.h"
#include "BookShelf.h"
#include "MagazineShelf.h"
#include "Stack.h"
#include "RandomGenerator.h" // Include RandomGenerator header

// CLI functions
void displayMenu() {
    std::cout << "\nLibrary System Menu:\n";
    std::cout << "1. Add Customer\n";
    std::cout << "2. Add Book\n";
    std::cout << "3. Add Magazine\n";
    std::cout << "4. Borrow Publications\n";
    std::cout << "5. Return Publications\n";
    std::cout << "6. Display All Books\n";
    std::cout << "7. Display All Magazines\n";
    std::cout << "8. Display Customer Borrowed Publications\n";
    std::cout << "9. Empty Return Stack\n";
    std::cout << "10. Generate Random Objects\n"; // New menu option
    std::cout << "11. Exit\n"; // Updated exit option
}

void addCustomer(Library& library) {
    int id;
    std::string firstName, lastName;
    std::cout << "Enter Customer ID: ";
    std::cin >> id;
    std::cout << "Enter First Name: ";
    std::cin >> firstName;
    std::cout << "Enter Last Name: ";
    std::cin >> lastName;

    Customer customer(id, firstName, lastName);
    library.addCustomer(customer);
    std::cout << "Customer added successfully.\n";
}

void addBook(BookShelf &bookShelf) {
    int id, year, pages, totalCopies, availableCopies;
    std::string title, firstName, lastName;

    std::cout << "Enter Book ID: ";
    std::cin >> id;
    std::cout << "Enter Title: ";
    std::cin.ignore();
    std::getline(std::cin, title);
    std::cout << "Enter Author First Name: ";
    std::getline(std::cin, firstName);
    std::cout << "Enter Author Last Name: ";
    std::getline(std::cin, lastName);
    std::cout << "Enter Year: ";
    std::cin >> year;
    std::cout << "Enter Pages: ";
    std::cin >> pages;
    std::cout << "Enter Total Copies: ";
    std::cin >> totalCopies;
    std::cout << "Enter Available Copies: ";
    std::cin >> availableCopies;

    Author author(firstName, lastName);
    Book* book = new Book(id, title, author, year, pages, totalCopies, availableCopies);
    bookShelf.addPublication(book);
    std::cout << "Book added successfully.\n";
}

void addMagazine(MagazineShelf &magazineShelf) {
    int id, year, issueNumber, totalCopies, availableCopies;
    std::string title;

    std::cout << "Enter Magazine ID: ";
    std::cin >> id;
    std::cout << "Enter Title: ";
    std::cin.ignore();
    std::getline(std::cin, title);
    std::cout << "Enter Year: ";
    std::cin >> year;
    std::cout << "Enter Issue Number: ";
    std::cin >> issueNumber;
    std::cout << "Enter Total Copies: ";
    std::cin >> totalCopies;
    std::cout << "Enter Available Copies: ";
    std::cin >> availableCopies;

    Magazine* magazine = new Magazine(id, title, year, issueNumber, totalCopies, availableCopies);
    magazineShelf.addPublication(magazine);
    std::cout << "Magazine added successfully.\n";
}

void borrowPublications(Library& library) {
    int customerId;
    std::cout << "Enter Customer ID: ";
    std::cin >> customerId;

    std::vector<Publication*> publications;
    int pubId;
    while (true) {
        std::cout << "Enter Publication ID to Borrow (0 to stop): ";
        std::cin >> pubId;
        if (pubId == 0) break;

        // Dummy publication for ID
        Publication* pub = new Publication(pubId, "", 0, 0, 0);
        publications.push_back(pub);
    }

    library.borrowPublications(customerId, publications);
    std::cout << "Borrowing process completed.\n";
}

void returnPublications(Library& library) {
    int customerId;
    std::cout << "Enter Customer ID: ";
    std::cin >> customerId;

    std::vector<Publication*> publications;
    int pubId;
    while (true) {
        std::cout << "Enter Publication ID to Return (0 to stop): ";
        std::cin >> pubId;
        if (pubId == 0) break;

        // Dummy publication for ID
        Publication* pub = new Publication(pubId, "", 0, 0, 0);
        publications.push_back(pub);
    }

    library.returnPublications(customerId, publications);
    std::cout << "Return process completed.\n";
}

void generateRandomObjects(Library& library, BookShelf& bookShelf, MagazineShelf& magazineShelf) {
    int numCustomers, numBooks, numMagazines;
    std::cout << "Enter number of random customers to generate: ";
    std::cin >> numCustomers;
    std::cout << "Enter number of random books to generate: ";
    std::cin >> numBooks;
    std::cout << "Enter number of random magazines to generate: ";
    std::cin >> numMagazines;

    RandomGenerator::generateRandomObjects(library, bookShelf, magazineShelf, numCustomers, numBooks, numMagazines);
    std::cout << "Random objects generated successfully.\n";
}

int main() {
    Library library;
    BookShelf bookShelf(100, 1);
    MagazineShelf magazineShelf(100, 1);
    library.addShelf(&bookShelf);
    library.addShelf(&magazineShelf);

    int choice;
    do {
        displayMenu();
        std::cout << "Enter your choice: ";
        try {
            std::cin >> choice;

            if (std::cin.fail()) {
                std::cin.clear();
                std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                throw std::invalid_argument("Invalid input. Please enter a number.");
            }

            switch (choice) {
                case 1:
                    addCustomer(library);
                    break;
                case 2:
                    addBook(bookShelf);
                    break;
                case 3:
                    addMagazine(magazineShelf);
                    break;
                case 4:
                    borrowPublications(library);
                    break;
                case 5:
                    returnPublications(library);
                    break;
                case 6:
                    library.displayAllBooks();
                    break;
                case 7:
                    library.displayAllMagazines();
                    break;
                case 8:
                    int customerId;
                    std::cout << "Enter Customer ID: ";
                    std::cin >> customerId;
                    library.displayCustomerBorrowedPublications(customerId);
                    break;
                case 9:
                    library.emptyPublicationStack();
                    std::cout << "Return stack emptied.\n";
                    break;
                case 10:
                    generateRandomObjects(library, bookShelf, magazineShelf);
                    break;
                case 11:
                    std::cout << "Exiting the program.\n";
                    break;
                default:
                    throw std::invalid_argument("Invalid choice. Please enter a number between 1 and 11.");
            }
        } catch (const std::invalid_argument& e) {
            std::cout << e.what() << std::endl;
        }
    } while (choice != 11);

    return 0;
}
