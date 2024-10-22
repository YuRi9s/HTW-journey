#include "MagazineShelf.h"
#include <algorithm>
#include <stdexcept>

MagazineShelf::MagazineShelf(int maxObjects, int floor) : Shelf(maxObjects, floor) {}

void MagazineShelf::addPublication(Publication *publication)
{
    Magazine *magazine = dynamic_cast<Magazine *>(publication);
    if (!magazine)
        throw std::invalid_argument("Only magazines can be added to the MagazineShelf");

    magazinesByTitle[magazine->title].push_back(*magazine);
    std::sort(magazinesByTitle[magazine->title].begin(), magazinesByTitle[magazine->title].end(), [](const Magazine &a, const Magazine &b)
              { return a.year < b.year || (a.year == b.year && a.issueNumber < b.issueNumber); });
}

void MagazineShelf::removePublication(int id)
{
    for (auto &titleMagazines : magazinesByTitle)
    {
        auto &magazines = titleMagazines.second;
        magazines.erase(std::remove_if(magazines.begin(), magazines.end(), [id](const Magazine &magazine)
                                       { return magazine.id == id; }),
                        magazines.end());
    }
}

Publication *MagazineShelf::borrowPublication(int id)
{
    for (auto &titleMagazines : magazinesByTitle)
    {
        for (auto &magazine : titleMagazines.second)
        {
            if (magazine.id == id)
            {
                if (magazine.availableCopies <= 0)
                    throw std::out_of_range("No copies available");
                magazine.availableCopies--;
                return new Magazine(magazine);
            }
        }
    }
    throw std::invalid_argument("Magazine not found");
}

void MagazineShelf::returnPublication(Publication *publication)
{
    Magazine *magazine = dynamic_cast<Magazine *>(publication);
    if (!magazine)
        throw std::invalid_argument("Only magazines can be returned to the MagazineShelf");

    for (auto &titleMagazines : magazinesByTitle)
    {
        for (auto &m : titleMagazines.second)
        {
            if (m.id == magazine->id)
            {
                m.availableCopies++;
                return;
            }
        }
    }
    throw std::invalid_argument("Magazine not found in shelf");
}

void MagazineShelf::addExemplar(int id)
{
    for (auto &titleMagazines : magazinesByTitle)
    {
        for (auto &magazine : titleMagazines.second)
        {
            if (magazine.id == id)
            {
                magazine.totalCopies++;
                magazine.availableCopies++;
                return;
            }
        }
    }
    throw std::invalid_argument("Magazine not found");
}

std::vector<Magazine> MagazineShelf::getMagazinesByTitle(const std::string &title) const
{
    auto it = magazinesByTitle.find(title);
    if (it != magazinesByTitle.end())
    {
        return it->second;
    }
    return {};
}

std::vector<Magazine> MagazineShelf::getAvailableMagazinesByTitleAndYear(const std::string &title, int year) const
{
    std::vector<Magazine> result;
    auto it = magazinesByTitle.find(title);
    if (it != magazinesByTitle.end())
    {
        std::copy_if(it->second.begin(), it->second.end(), std::back_inserter(result), [year](const Magazine &magazine)
                     { return magazine.year == year && magazine.availableCopies > 0; });
    }
    return result;
}
