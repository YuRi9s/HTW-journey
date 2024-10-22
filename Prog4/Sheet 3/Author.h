#ifndef AUTHOR_H
#define AUTHOR_H

#include <string>

/**
 * @class Author
 * @brief Represents an author with a first name and a last name.
 */

class Author
{
public:
    std::string firstName;
    std::string lastName;
    /**
     * @brief Constructs an Author object with the given first and last name.
     * @param fName First name of the author
     * @param lName Last name of the author
     */

    Author(const std::string &fName, const std::string &lName);
};

#endif // AUTHOR_H
