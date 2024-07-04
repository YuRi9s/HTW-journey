//
// Created by yuri9 on 01/07/2024.
//

#ifndef CITY_H
#define CITY_H

#include "Storehouse.h"
#include <string>
#include <sstream>
#include <stdexcept>

using namespace std;

/**
 * @class City
 * @brief Represents a city with a name, distance from the home city, and a storehouse.
 */
class City {
private:
    string name; ///< Name of the city
    int distance; ///< Distance to the home city
    Storehouse storehouse; ///< Storehouse in the city

public:
    /**
     * @brief Constructor to initialize a city.
     * @param name Name of the city.
     * @param distance Distance to the home city.
     * @param storehouse Storehouse for the city.
     * @throw invalid_argument if distance is negative.
     */
    City(const string &name, int distance, const Storehouse &storehouse);

    /**
     * @brief Gets the name of the city.
     * @return The name of the city.
     */
    string getName() const;

    /**
     * @brief Gets the distance to the home city.
     * @return The distance to the home city.
     */
    int getDistance() const;

    /**
     * @brief Gets the storehouse of the city.
     * @return The storehouse of the city.
     */
    Storehouse getStorehouse() const;

    /**
     * @brief Returns a string representation of the city and its storehouse.
     * @return String representation of the city.
     */
    string toString() const;
};

#endif // CITY_H
