#ifndef SHELF_H
#define SHELF_H

#include "Publication.h"

/**
 * @class Shelf
 * @brief Abstract base class representing a shelf that can hold publications.
 */

class Shelf
{
protected:
    int maxObjects;
    int floor;

public:
    /**
     * @brief Constructs a Shelf object with the given maximum number of objects and floor.
     * @param maxObjects Maximum number of objects
     * @param floor Floor number
     */
    Shelf(int maxObjects, int floor);

    /**
     * @brief Adds a publication to the shelf.
     * @param publication Pointer to the publication to be added
     */

    virtual void addPublication(Publication *publication) = 0;
    /**
     * @brief Removes a publication from the shelf by ID.
     * @param id ID of the publication to be removed
     */
    virtual void removePublication(int id) = 0;
    /**
     * @brief Borrows a publication from the shelf by ID.
     * @param id ID of the publication to be borrowed
     * @return Pointer to the borrowed publication
     */
    virtual Publication *borrowPublication(int id) = 0;
    /**
     * @brief Returns a publication to the shelf.
     * @param publication Pointer to the publication to be returned
     */
    virtual void returnPublication(Publication *publication) = 0;
    /**
     * @brief Adds an exemplar (copy) of a publication to the shelf by ID.
     * @param id ID of the publication
     */
    virtual void addExemplar(int id) = 0;
    /**
     * @brief Virtual destructor for the Shelf class.
     */
    virtual ~Shelf() = default;
};

#endif // SHELF_H
