/**
 * Fills an array with random integer values.
 *
 * @param laenge   The length of the array.
 * @param maxZahl  The maximum value for the generated random numbers.
 * @return         A pointer to the dynamically allocated array filled with random integer values.
 */

#include "math_functions.h"

int *Moduls::fuelleArray(int laenge, int maxZahl)
{
    int *array = new int[laenge];
    for (int i = 0; i < laenge; ++i)
    {
        array[i] = erzeugeInt(maxZahl);
    }
    return array;
}

/**
 * Prints the elements of an array.
 *
 * @param array        Pointer to the array.
 * @param anzahlWerte  Number of elements in the array.
 */

void Moduls::gibAusArray(int *array, int anzahlWerte)
{
    for (int i = 0; i < anzahlWerte; ++i)
    {
        std::cout << array[i] << std::endl;
    }
}

/**
 * Calculates the average of an array of integers.
 *
 * @param zahlen       Pointer to the array of integers.
 * @param anzahlWerte  Number of elements in the array.
 * @return             The average of the array elements.
 */

double Moduls::berechneDurchschnitt(int *zahlen, int anzahlWerte)
{
    if (anzahlWerte <= 0)
        return 0;
    double summe = 0;
    for (int i = 0; i < anzahlWerte; ++i)
    {
        summe += zahlen[i];
    }
    return summe / anzahlWerte;
}

/**
 * Finds the maximum value in an array of integers.
 *
 * @param zahlen       Pointer to the array of integers.
 * @param anzahlWerte  Number of elements in the array.
 * @return             The maximum value in the array.
 */

int Moduls::bestimmeMaximum(int *zahlen, int anzahlWerte)
{
    if (anzahlWerte <= 0)
        return 0;
    int max = zahlen[0];
    for (int i = 1; i < anzahlWerte; ++i)
    {
        if (zahlen[i] > max)
        {
            max = zahlen[i];
        }
    }
    return max;
}

/**
 * Finds the minimum value in an array of integers.
 *
 * @param zahlen       Pointer to the array of integers.
 * @param anzahlWerte  Number of elements in the array.
 * @return             The minimum value in the array.
 */

int Moduls::bestimmeMinimum(int *zahlen, int anzahlWerte)
{
    if (anzahlWerte <= 0)
        return 0;
    int min = zahlen[0];
    for (int i = 1; i < anzahlWerte; ++i)
    {
        if (zahlen[i] < min)
        {
            min = zahlen[i];
        }
    }
    return min;
}