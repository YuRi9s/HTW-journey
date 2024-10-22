#include "Library.h"
#include "BookShelf.h"
#include "MagazineShelf.h"
#include <iostream>
#include <stdexcept>
#include <iomanip>

void Library::addCustomer(const Customer &customer)
{
    customers[customer.customerId] = customer;
}

void Library::addShelf(Shelf *shelf)
{
    shelves.push_back(shelf);
}

void Library::borrowPublications(int customerId, std::vector<Publication *> &publications)
{
    auto &customer = customers.at(customerId);
    for (auto &publication : publications)
    {
        try
        {
            for (auto &shelf : shelves)
            {
                Publication *borrowed = shelf->borrowPublication(publication->id);
                customer.borrowPublication(borrowed);
            }
        }
        catch (const std::exception &)
        {
            // Publication could not be borrowed, continue with next one
            continue;
        }
    }
}

void Library::returnPublications(int customerId, std::vector<Publication *> &publications)
{
    auto &customer = customers.at(customerId);
    for (auto &publication : publications)
    {
        try
        {
            customer.returnPublication(publication->id);
            returnStack.push(publication);
        }
        catch (const std::exception &)
        {
            // Publication could not be returned, continue with next one
            continue;
        }
    }
}

void Library::emptyPublicationStack()
{
    while (!returnStack.empty())
    {
        auto *publication = returnStack.top();
        returnStack.pop();
        for (auto &shelf : shelves)
        {
            try
            {
                shelf->returnPublication(publication);
            }
            catch (const std::exception &)
            {
                // Publication could not be returned to this shelf, try next one
                continue;
            }
        }
    }
}

void Library::displayAllBooks() const
{
    std::cout << "+-----+----------------------------+--------------------+------+-------+-------+\n";
    std::cout << "| ID  | Title                      | Author             | Year | Total | Avail |\n";
    std::cout << "+-----+----------------------------+--------------------+------+-------+-------+\n";
    for (const auto &shelf : shelves)
    {
        const BookShelf *bookShelf = dynamic_cast<const BookShelf *>(shelf);
        if (bookShelf)
        {
            for (const auto &book : bookShelf->getAllAvailableBooks())
            {
                std::cout << "| " << std::setw(3) << book.id
                          << " | " << std::setw(26) << std::left << book.title
                          << " | " << std::setw(18) << std::left << (book.author.firstName + " " + book.author.lastName)
                          << " | " << std::setw(4) << book.year
                          << " | " << std::setw(5) << book.totalCopies
                          << " | " << std::setw(5) << book.availableCopies
                          << " |\n";
            }
        }
    }
    std::cout << "+-----+----------------------------+--------------------+------+-------+-------+\n";
}

void Library::displayAllMagazines() const
{
    std::cout << "+-----+----------------------------+------+-------+-------+-------+\n";
    std::cout << "| ID  | Title                      | Year | Issue | Total | Avail |\n";
    std::cout << "+-----+----------------------------+------+-------+-------+-------+\n";
    for (const auto &shelf : shelves)
    {
        const MagazineShelf *magazineShelf = dynamic_cast<const MagazineShelf *>(shelf);
        if (magazineShelf)
        {
            for (const auto &magazine : magazineShelf->getAvailableMagazinesByTitleAndYear("", 0))
            {
                std::cout << "| " << std::setw(3) << magazine.id
                          << " | " << std::setw(26) << std::left << magazine.title
                          << " | " << std::setw(4) << magazine.year
                          << " | " << std::setw(5) << magazine.issueNumber
                          << " | " << std::setw(5) << magazine.totalCopies
                          << " | " << std::setw(5) << magazine.availableCopies
                          << " |\n";
            }
        }
    }
    std::cout << "+-----+----------------------------+------+-------+-------+-------+\n";
}

void Library::displayCustomerBorrowedPublications(int customerId) const
{
    auto it = customers.find(customerId);
    if (it != customers.end())
    {
        const auto &customer = it->second;
        std::cout << "Customer: " << customer.firstName << " " << customer.lastName << "\n";
        std::cout << "+-----+----------------------------+------+-------+\n";
        std::cout << "| ID  | Title                      | Year | Type  |\n";
        std::cout << "+-----+----------------------------+------+-------+\n";
        for (const auto &pub : customer.borrowedPublications)
        {
            std::cout << "| " << std::setw(3) << pub->id
                      << " | " << std::setw(26) << std::left << pub->title
                      << " | " << std::setw(4) << pub->year
                      << " | " << std::setw(5) << (dynamic_cast<Book *>(pub) ? "Book" : "Mag")
                      << " |\n";
        }
        std::cout << "+-----+----------------------------+------+-------+\n";
    }
}
