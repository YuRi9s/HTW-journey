#include "Book.h"

Book::Book(int id, const std::string &title, const Author &author, int year, int pages, int totalCopies, int availableCopies)
    : Publication(id, title, year, totalCopies, availableCopies), author(author), pages(pages) {}
