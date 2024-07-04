#include "math_functions.h"
using namespace std;

/**
 * Prints a separator line to improve readability.
 */
void printSeparator()
{
    cout << "----------------------------" << endl;
}

/**
 * Displays the menu options to the user.
 */
void zeigeMenue()
{
    cout << "1: Array mit Zufallswerten anlegen;" << endl;
    cout << "2: Array anzeigen;" << endl;
    cout << "3: Durchschnitt berechnen;" << endl;
    cout << "4: Minimum bestimmen;" << endl;
    cout << "5: Maximum bestimmen;" << endl;
    cout << "6: Teilersumme berechnen;" << endl;
    cout << "7: Endliches Produkt berechnen;" << endl;
    cout << "0: beenden -> ";
}

/**
 * The main entry point of the program.
 */
int main()
{
    // Initialize the random number generator
    Moduls::initGenerator();
    int *array = nullptr;
    int arrayLaenge = 0;

    // Main program loop
    while (true)
    {
        zeigeMenue();
        int auswahl;
        cin >> auswahl;

        // Exit loop if the user chooses to quit
        if (auswahl == 0)
        {
            break;
        }

        printSeparator();

        // Process user's selection
        switch (auswahl)
        {
        case 1:
        {
            cout << "Wie viele Werte soll das Array enthalten?";
            cin >> arrayLaenge;
            cout << "Geben Sie eine Obergrenze fuer die Zufallswerte an (0 = keine Obergrenze):";
            int maxZahl;
            cin >> maxZahl;
            delete[] array;
            array = Moduls::fuelleArray(arrayLaenge, maxZahl);
            cout << "Array wurde erzeugt" << endl;
            break;
        }
        case 2:
            if (array != nullptr)
            {
                Moduls::gibAusArray(array, arrayLaenge);
            }
            else
            {
                cout << "Array ist nicht vorhanden" << endl;
            }
            break;
        case 3:
            if (array != nullptr)
            {
                double durchschnitt = Moduls::berechneDurchschnitt(array, arrayLaenge);
                cout << "Durchschnitt: " << durchschnitt << endl;
            }
            else
            {
                cout << "Array ist nicht vorhanden" << endl;
            }
            break;
        case 4:
            if (array != nullptr)
            {
                int min = Moduls::bestimmeMinimum(array, arrayLaenge);
                cout << "Minimum: " << min << endl;
            }
            else
            {
                cout << "Array ist nicht vorhanden" << endl;
            }
            break;
        case 5:
            if (array != nullptr)
            {
                int max = Moduls::bestimmeMaximum(array, arrayLaenge);
                cout << "Maximum: " << max << endl;
            }
            else
            {
                cout << "Array ist nicht vorhanden" << endl;
            }
            break;
        case 6:
        {
            cout << "Geben Sie eine Zahl ein:";
            int zahl;
            cin >> zahl;
            long teilersumme = Moduls::berechneTeilersumme(zahl);
            cout << "Teilersumme: " << teilersumme << endl;
            break;
        }
        case 7:
        {
            cout << "Geben Sie den Startwert ein:";
            int start, ende;
            cin >> start;
            cout << "Geben Sie den Endwert ein:";
            cin >> ende;
            long produkt = Moduls::berechneEndlichesProdukt(start, ende);
            cout << "Endliches Produkt: " << produkt << endl;
            break;
        }
        default:
            cout << "Ungültige Auswahl" << endl;
        }
        printSeparator();
    }

    delete[] array;
    return 0;
}
