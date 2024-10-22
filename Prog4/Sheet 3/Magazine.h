#ifndef MAGAZINE_H
#define MAGAZINE_H

#include "Publication.h"

/**
 * @class Magazine
 * @brief Represents a magazine, which is a type of publication with an issue number.
 */

class Magazine : public Publication
{
public:
    int issueNumber;

    /**
     * @brief Constructs a Magazine object with the given parameters.
     * @param id ID of the magazine
     * @param title Title of the magazine
     * @param year Year of publication
     * @param issueNumber Issue number of the magazine
     * @param totalCopies Total number of copies
     * @param availableCopies Number of available copies
     */

    Magazine(int id, const std::string &title, int year, int issueNumber, int totalCopies, int availableCopies);
};

#endif // MAGAZINE_H
