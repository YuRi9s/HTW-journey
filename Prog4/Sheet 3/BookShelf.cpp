#include "BookShelf.h"
#include <algorithm>
#include <stdexcept>

BookShelf::BookShelf(int maxObjects, int floor) : Shelf(maxObjects, floor) {}

void BookShelf::addPublication(Publication *publication)
{
    Book *book = dynamic_cast<Book *>(publication);
    if (!book)
        throw std::invalid_argument("Only books can be added to the BookShelf");

    booksByAuthor[book->author.lastName].push_back(*book);
    booksByAuthor[book->author.lastName].sort([](const Book &a, const Book &b)
                                              { return a.title < b.title; });
}

void BookShelf::removePublication(int id)
{
    for (auto &authorBooks : booksByAuthor)
    {
        auto &books = authorBooks.second;
        books.remove_if([id](const Book &book)
                        { return book.id == id; });
    }
}

Publication *BookShelf::borrowPublication(int id)
{
    for (auto &authorBooks : booksByAuthor)
    {
        for (auto &book : authorBooks.second)
        {
            if (book.id == id)
            {
                if (book.availableCopies <= 0)
                    throw std::out_of_range("No copies available");
                book.availableCopies--;
                return new Book(book);
            }
        }
    }
    throw std::invalid_argument("Book not found");
}

void BookShelf::returnPublication(Publication *publication)
{
    Book *book = dynamic_cast<Book *>(publication);
    if (!book)
        throw std::invalid_argument("Only books can be returned to the BookShelf");

    for (auto &authorBooks : booksByAuthor)
    {
        for (auto &b : authorBooks.second)
        {
            if (b.id == book->id)
            {
                b.availableCopies++;
                return;
            }
        }
    }
    throw std::invalid_argument("Book not found in shelf");
}

void BookShelf::addExemplar(int id)
{
    for (auto &authorBooks : booksByAuthor)
    {
        for (auto &book : authorBooks.second)
        {
            if (book.id == id)
            {
                book.totalCopies++;
                book.availableCopies++;
                return;
            }
        }
    }
    throw std::invalid_argument("Book not found");
}

std::vector<Book> BookShelf::getBooksByAuthor(const std::string &author) const
{
    std::vector<Book> result;
    auto it = booksByAuthor.find(author);
    if (it != booksByAuthor.end())
    {
        result.insert(result.end(), it->second.begin(), it->second.end());
    }
    return result;
}

std::vector<Book> BookShelf::getAvailableBooksByAuthor(const std::string &author) const
{
    std::vector<Book> result;
    auto it = booksByAuthor.find(author);
    if (it != booksByAuthor.end())
    {
        std::copy_if(it->second.begin(), it->second.end(), std::back_inserter(result), [](const Book &book)
                     { return book.availableCopies > 0; });
    }
    return result;
}

std::vector<Book> BookShelf::getAllAvailableBooks() const
{
    std::vector<Book> result;
    for (const auto &authorBooks : booksByAuthor)
    {
        std::copy_if(authorBooks.second.begin(), authorBooks.second.end(), std::back_inserter(result), [](const Book &book)
                     { return book.availableCopies > 0; });
    }
    return result;
}
