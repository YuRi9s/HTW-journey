#ifndef LIBRARY_H
#define LIBRARY_H

#include "Customer.h"
#include "Shelf.h"
#include <map>
#include <stack>
#include <vector>

/**
 * @class Library
 * @brief Represents a library that holds customers and shelves with publications.
 */

class Library
{
private:
    std::map<int, Customer> customers;
    std::vector<Shelf *> shelves;
    std::stack<Publication *> returnStack;

public:
    /**
     * @brief Adds a customer to the library.
     * @param customer The customer to be added
     */
    void addCustomer(const Customer &customer);
    /**
     * @brief Adds a shelf to the library.
     * @param shelf Pointer to the shelf to be added
     */
    void addShelf(Shelf *shelf);
    /**
     * @brief Borrows publications for a customer.
     * @param customerId ID of the customer
     * @param publications Vector of publications to be borrowed
     */
    void borrowPublications(int customerId, std::vector<Publication *> &publications);
    /**
     * @brief Returns publications for a customer.
     * @param customerId ID of the customer
     * @param publications Vector of publications to be returned
     */
    void returnPublications(int customerId, std::vector<Publication *> &publications);
    /**
     * @brief Empties the return stack and returns publications to their shelves.
     */
    void emptyPublicationStack();
    /**
     * @brief Displays all books in the library.
     */
    void displayAllBooks() const;
    /**
     * @brief Displays all magazines in the library.
     */
    void displayAllMagazines() const;

    /**
     * @brief Displays borrowed publications for a customer.
     * @param customerId ID of the customer
     */
    void displayCustomerBorrowedPublications(int customerId) const;
};

#endif // LIBRARY_H
