#include "math_functions.h"

/**
 * Initializes the random number generator.
 */
void Moduls::initGenerator()
{
    std::srand(std::time(0));
}

/**
 * Generates a random integer.
 * 
 * @param maxWert The maximum value for the generated random number.
 * @return        The generated random integer.
 */
int Moduls::erzeugeInt(int maxWert)
{
    if (maxWert > 0)
    {
        return std::rand() % maxWert;
    }
    else
    {
        return std::rand();
    }
}
