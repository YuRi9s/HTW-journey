#ifndef RANDOMGENERATOR_H
#define RANDOMGENERATOR_H

#include "Library.h"
#include "BookShelf.h"
#include "MagazineShelf.h"
#include <random>

/**
 * Quick info for Professor Olberts this class was me trying to be funny and testing if I could find a way to make the claas generate names by its own However I didnt have time to check and correct any potential mistakes so I will leave it as it is
 * @class RandomGenerator
 * @brief Utility class for generating random library objects for testing.
 */
class RandomGenerator
{
private:
    /**
     * @brief Generates a random string of the specified length.
     * @param length Length of the random string
     * @return Randomly generated string
     */
    static std::string getRandomString(size_t length);

    /**
     * @brief Generates a random integer within the specified range.
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return Randomly generated integer
     */
    static int getRandomInt(int min, int max);

public:
    /**
     * @brief Generates random customers, books, and magazines and adds them to the library.
     * @param library Reference to the library
     * @param bookShelf Reference to the book shelf
     * @param magazineShelf Reference to the magazine shelf
     * @param numCustomers Number of random customers to generate
     * @param numBooks Number of random books to generate
     * @param numMagazines Number of random magazines to generate
     */
    static void generateRandomObjects(Library &library, BookShelf &bookShelf, MagazineShelf &magazineShelf, int numCustomers, int numBooks, int numMagazines);
};

#endif // RANDOMGENERATOR_H
