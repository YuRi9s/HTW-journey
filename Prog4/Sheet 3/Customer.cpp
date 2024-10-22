#include "Customer.h"
#include <algorithm>
#include <stdexcept>

Customer::Customer() : customerId(0), firstName(""), lastName("") {}

Customer::Customer(int id, const std::string &fName, const std::string &lName)
    : customerId(id), firstName(fName), lastName(lName) {}

void Customer::borrowPublication(Publication *publication)
{
    for (const auto &pub : borrowedPublications)
    {
        if (pub->title == publication->title)
            throw std::invalid_argument("Publication already borrowed");
    }
    borrowedPublications.push_back(publication);
}

void Customer::returnPublication(int id)
{
    auto it = std::remove_if(borrowedPublications.begin(), borrowedPublications.end(), [id](Publication *publication)
                             { return publication->id == id; });
    if (it == borrowedPublications.end())
        throw std::invalid_argument("Publication not found");
    borrowedPublications.erase(it, borrowedPublications.end());
}
