#ifndef BOOKSHELF_H
#define BOOKSHELF_H

#include "Shelf.h"
#include "Book.h"
#include <map>
#include <list>
#include <vector>

/**
 * @class BookShelf
 * @brief Represents a shelf that holds books, inheriting from the Shelf class.
 */

class BookShelf : public Shelf
{
private:
    /**
     * @brief Constructs a BookShelf object with the given maximum number of objects and floor.
     * @param maxObjects Maximum number of objects
     * @param floor Floor number
     */
    std::map<std::string, std::list<Book>> booksByAuthor;

public:
    BookShelf(int maxObjects, int floor);

    void addPublication(Publication *publication) override;
    void removePublication(int id) override;
    Publication *borrowPublication(int id) override;
    void returnPublication(Publication *publication) override;
    void addExemplar(int id) override;
    /**
     * @brief Retrieves books by a specific author.
     * @param author Author's last name
     * @return Vector of books by the specified author
     */
    std::vector<Book> getBooksByAuthor(const std::string &author) const;
    /**
     * @brief Retrieves available books by a specific author.
     * @param author Author's last name
     * @return Vector of available books by the specified author
     */
    std::vector<Book> getAvailableBooksByAuthor(const std::string &author) const;
    /**
     * @brief Retrieves all available books on the shelf.
     * @return Vector of all available books
     */
    std::vector<Book> getAllAvailableBooks() const;
};

#endif // BOOKSHELF_H
