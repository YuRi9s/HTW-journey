#include "Publication.h"

Publication::Publication(int id, const std::string &title, int year, int totalCopies, int availableCopies)
    : id(id), title(title), year(year), totalCopies(totalCopies), availableCopies(availableCopies) {}
