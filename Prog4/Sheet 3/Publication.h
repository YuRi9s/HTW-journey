#ifndef PUBLICATION_H
#define PUBLICATION_H

#include <string>

/**
 * @class Publication
 * @brief Represents a publication with an ID, title, year, total copies, and available copies.
 */

class Publication
{
public:
    int id;
    std::string title;
    int year;
    int totalCopies;
    int availableCopies;

    /**
     * @brief Constructs a Publication object with the given parameters.
     * @param id ID of the publication
     * @param title Title of the publication
     * @param year Year of publication
     * @param totalCopies Total number of copies
     * @param availableCopies Number of available copies
     */

    Publication(int id, const std::string &title, int year, int totalCopies, int availableCopies);

    /**
     * @brief Virtual destructor for the Publication class.
     */
    virtual ~Publication() = default;
};

#endif // PUBLICATION_H
