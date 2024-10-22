#ifndef BOOK_H
#define BOOK_H

#include "Publication.h"
#include "Author.h"

/**
 * @class Book
 * @brief Represents a book, which is a type of publication with an author and number of pages.
 */

class Book : public Publication
{
public:
    Author author;
    int pages;

    /**
     * @brief Constructs a Book object with the given parameters.
     * @param id ID of the book
     * @param title Title of the book
     * @param author Author of the book
     * @param year Year of publication
     * @param pages Number of pages
     * @param totalCopies Total number of copies
     * @param availableCopies Number of available copies
     */

    Book(int id, const std::string &title, const Author &author, int year, int pages, int totalCopies, int availableCopies);
};

#endif // BOOK_H
