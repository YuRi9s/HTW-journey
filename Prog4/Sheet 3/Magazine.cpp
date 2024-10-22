#include "Magazine.h"

Magazine::Magazine(int id, const std::string& title, int year, int issueNumber, int totalCopies, int availableCopies)
    : Publication(id, title, year, totalCopies, availableCopies), issueNumber(issueNumber) {}
