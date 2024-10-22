#include "RandomGenerator.h"

std::string RandomGenerator::getRandomString(size_t length)
{
    const char charset[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    std::string result;
    result.resize(length);
    std::default_random_engine rng(std::random_device{}());
    std::uniform_int_distribution<> dist(0, sizeof(charset) - 2);
    for (size_t i = 0; i < length; ++i)
    {
        result[i] = charset[dist(rng)];
    }
    return result;
}

int RandomGenerator::getRandomInt(int min, int max)
{
    std::default_random_engine rng(std::random_device{}());
    std::uniform_int_distribution<> dist(min, max);
    return dist(rng);
}

void RandomGenerator::generateRandomObjects(Library &library, BookShelf &bookShelf, MagazineShelf &magazineShelf, int numCustomers, int numBooks, int numMagazines)
{
    for (int i = 0; i < numCustomers; ++i)
    {
        int id = getRandomInt(1, 1000);
        std::string firstName = getRandomString(5);
        std::string lastName = getRandomString(7);
        Customer customer(id, firstName, lastName);
        library.addCustomer(customer);
    }

    for (int i = 0; i < numBooks; ++i)
    {
        int id = getRandomInt(1, 1000);
        std::string title = getRandomString(10);
        std::string authorFirstName = getRandomString(5);
        std::string authorLastName = getRandomString(7);
        Author author(authorFirstName, authorLastName);
        int year = getRandomInt(1900, 2023);
        int pages = getRandomInt(100, 1000);
        int totalCopies = getRandomInt(1, 10);
        int availableCopies = totalCopies;
        Book *book = new Book(id, title, author, year, pages, totalCopies, availableCopies);
        bookShelf.addPublication(book);
    }

    for (int i = 0; i < numMagazines; ++i)
    {
        int id = getRandomInt(1, 1000);
        std::string title = getRandomString(10);
        int year = getRandomInt(1900, 2023);
        int issueNumber = getRandomInt(1, 100);
        int totalCopies = getRandomInt(1, 10);
        int availableCopies = totalCopies;
        Magazine *magazine = new Magazine(id, title, year, issueNumber, totalCopies, availableCopies);
        magazineShelf.addPublication(magazine);
    }
}
