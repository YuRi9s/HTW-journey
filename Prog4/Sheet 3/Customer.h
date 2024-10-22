#ifndef CUSTOMER_H
#define CUSTOMER_H

#include "Publication.h"
#include <vector>
#include <string>

/**
 * @class Customer
 * @brief Represents a customer who can borrow publications from the library.
 */

class Customer
{
public:
    int customerId;
    std::string firstName;
    std::string lastName;
    std::vector<Publication *> borrowedPublications;
    /**
     * @brief Constructs a Customer object with default values.
     */
    Customer();
    /**
     * @brief Constructs a Customer object with the given parameters.
     * @param id ID of the customer
     * @param fName First name of the customer
     * @param lName Last name of the customer
     */
    Customer(int id, const std::string &fName, const std::string &lName);
    /**
     * @brief Borrows a publication for the customer.
     * @param publication Pointer to the publication to be borrowed
     */
    void borrowPublication(Publication *publication);
    /**
     * @brief Returns a publication for the customer.
     * @param id ID of the publication to be returned
     */

    void returnPublication(int id);
};

#endif // CUSTOMER_H
