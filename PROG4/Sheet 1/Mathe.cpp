#include "math_functions.h"

/**
 * Calculates the finite product of numbers within a specified range.
 *
 * @param start The start value of the range.
 * @param end   The end value of the range.
 * @return      The finite product of numbers within the specified range.
 */
long Moduls::berechneEndlichesProdukt(int start, int end)
{
    if (start <= 0 || end <= 0)
    {
        return 0;
    }
    if (start > end)
    {
        int temp = start;
        start = end;
        end = temp;
    }
    long produkt = 1;
    for (int i = start; i <= end; ++i)
    {
        produkt *= i;
    }
    return produkt;
}

/**
 * Calculates the sum of divisors of a given number.
 *
 * @param zahl The number for which the sum of divisors needs to be calculated.
 * @return     The sum of divisors of the given number.
 */
long Moduls::berechneTeilersumme(int zahl)
{
    if (zahl <= 0)
    {
        return -1;
    }
    long summe = 0;
    for (int i = 1; i <= zahl; ++i)
    {
        if (zahl % i == 0)
        {
            summe += i;
        }
    }
    return summe;
}
