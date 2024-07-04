//
// Created by yuri9 on 01/07/2024.
//

#include "City.h"

City::City(const string &name, int distance, const Storehouse &storehouse)
    : name(name), distance(distance), storehouse(storehouse) {
    if (distance < 0) {
        throw invalid_argument("Distance must be non-negative");
    }
}

string City::getName() const { return name; }
int City::getDistance() const { return distance; }
Storehouse City::getStorehouse() const { return storehouse; }

string City::toString() const {
    stringstream ss;
    ss << "City: " << name << "\n";
    ss << "Distance: " << distance << " km\n";
    ss << "Storehouse:\n";
    ss << storehouse.toString();
    return ss.str();
}
