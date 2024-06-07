#ifndef MATH_FUNCTIONS_H
#define MATH_FUNCTIONS_H

#include <cstdlib>
#include <ctime>
#include <iostream>

/**
 * Class Moduls contains static methods for mathematical operations, random number generation, and array manipulation.
 */
class Moduls
{
public:
    // Mathe

    /**
     * Calculates the finite product of numbers within a specified range.
     *
     * @param start The start value of the range.
     * @param end   The end value of the range.
     * @return      The finite product of numbers within the specified range.
     */
    static long berechneEndlichesProdukt(int start, int ende);

    /**
     * Calculates the sum of divisors of a given number.
     *
     * @param zahl The number for which the sum of divisors needs to be calculated.
     * @return     The sum of divisors of the given number.
     */
    static long berechneTeilersumme(int zahl);

    // Random

    /**
     * Initializes the random number generator.
     */
    static void initGenerator();

    /**
     * Generates a random integer.
     *
     * @param maxWert The maximum value for the generated random number.
     * @return        The generated random integer.
     */
    static int erzeugeInt(int maxWert);

    // Arrays

    /**
     * Fills an array with random integer values.
     *
     * @param laenge   The length of the array.
     * @param maxZahl  The maximum value for the generated random numbers.
     * @return         A pointer to the dynamically allocated array filled with random integer values.
     */
    static int *fuelleArray(int laenge, int maxZahl);

    /**
     * Prints the elements of an array.
     *
     * @param array       Pointer to the array.
     * @param anzahlWerte Number of elements in the array.
     */
    static void gibAusArray(int *array, int anzahlWerte);

    /**
     * Calculates the average of an array of integers.
     *
     * @param zahlen      Pointer to the array of integers.
     * @param anzahlWerte Number of elements in the array.
     * @return            The average of the array elements.
     */
    static double berechneDurchschnitt(int *zahlen, int anzahlWerte);

    /**
     * Finds the maximum value in an array of integers.
     *
     * @param zahlen      Pointer to the array of integers.
     * @param anzahlWerte Number of elements in the array.
     * @return            The maximum value in the array.
     */
    static int bestimmeMaximum(int *zahlen, int anzahlWerte);

    /**
     * Finds the minimum value in an array of integers.
     *
     * @param zahlen      Pointer to the array of integers.
     * @param anzahlWerte Number of elements in the array.
     * @return            The minimum value in the array.
     */
    static int bestimmeMinimum(int *zahlen, int anzahlWerte);
};

#endif
