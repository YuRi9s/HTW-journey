#ifndef MAGAZINESHELF_H
#define MAGAZINESHELF_H

#include "Shelf.h"
#include "Magazine.h"
#include <map>
#include <vector>

/**
 * @class MagazineShelf
 * @brief Represents a shelf that holds magazines, inheriting from the Shelf class.
 */

class MagazineShelf : public Shelf
{
private:
    std::map<std::string, std::vector<Magazine>> magazinesByTitle;

public:
    /**
     * @brief Constructs a MagazineShelf object with the given maximum number of objects and floor.
     * @param maxObjects Maximum number of objects
     * @param floor Floor number
     */
    MagazineShelf(int maxObjects, int floor);

    void addPublication(Publication *publication) override;
    void removePublication(int id) override;
    Publication *borrowPublication(int id) override;
    void returnPublication(Publication *publication) override;
    void addExemplar(int id) override;
    /**
     * @brief Retrieves magazines by title.
     * @param title Title of the magazines
     * @return Vector of magazines with the specified title
     */
    std::vector<Magazine> getMagazinesByTitle(const std::string &title) const;
    /**
     * @brief Retrieves available magazines by title and year.
     * @param title Title of the magazines
     * @param year Year of publication
     * @return Vector of available magazines with the specified title and year
     */
    std::vector<Magazine> getAvailableMagazinesByTitleAndYear(const std::string &title, int year) const;
};

#endif // MAGAZINESHELF_H
