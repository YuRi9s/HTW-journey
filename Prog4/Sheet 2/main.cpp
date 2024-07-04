#include <iostream>
#include "City.h"

using namespace std;

void printMenu() {
    cout << "\nMenu:\n";
    cout << "1. Add an article\n";
    cout << "2. Increase stock\n";
    cout << "3. Reduce stock\n";
    cout << "4. Change price of an article\n";
    cout << "5. Show storehouse\n";
    cout << "6. Create and show city\n";
    cout << "7. Exit\n";
    cout << "Enter your choice: ";
}

int main() {
    try {
        // Create a storehouse
        cout << "Enter the maximum size for the storehouse: ";
        int maxSize;
        cin >> maxSize;
        if (maxSize <= 0) throw invalid_argument("Storehouse size must be positive");
        Storehouse storehouse(maxSize);

        int choice;
        do {
            printMenu();
            cin >> choice;

            switch (choice) {
                case 1: {
                    // Add an article
                    int articleNumber, stock;
                    string description;
                    double price;

                    cout << "Enter article number: ";
                    cin >> articleNumber;
                    if (articleNumber <= 0) throw invalid_argument("Article number must be positive");

                    cin.ignore(); // Ignore newline character left in the buffer

                    cout << "Enter description: ";
                    getline(cin, description);
                    if (description.empty()) throw invalid_argument("Description cannot be empty");

                    cout << "Enter price: ";
                    cin >> price;
                    if (price < 0) throw invalid_argument("Price must be non-negative");

                    cout << "Enter stock: ";
                    cin >> stock;
                    if (stock < 0) throw invalid_argument("Stock must be non-negative");

                    storehouse.addArticle(new Article(articleNumber, description, price, stock));
                    break;
                }
                case 2: {
                    // Increase stock
                    int articleNumber, amount;
                    cout << "Enter article number: ";
                    cin >> articleNumber;
                    cout << "Enter amount to increase stock: ";
                    cin >> amount;
                    storehouse.increaseStock(articleNumber, amount);
                    break;
                }
                case 3: {
                    // Reduce stock
                    int articleNumber, amount;
                    cout << "Enter article number: ";
                    cin >> articleNumber;
                    cout << "Enter amount to reduce stock: ";
                    cin >> amount;
                    storehouse.reduceStock(articleNumber, amount);
                    break;
                }
                case 4: {
                    // Change price
                    int articleNumber, percent;
                    cout << "Enter article number: ";
                    cin >> articleNumber;
                    cout << "Enter percentage to change price: ";
                    cin >> percent;
                    storehouse.changePrice(articleNumber, percent);
                    break;
                }
                case 5: {
                    // Show storehouse
                    cout << "Storehouse:\n";
                    cout << storehouse.toString();
                    break;
                }
                case 6: {
                    // Create and show city
                    string cityName;
                    int distance;

                    cout << "Enter city name: ";
                    cin.ignore(); // Ignore newline character left in the buffer
                    getline(cin, cityName);
                    if (cityName.empty()) throw invalid_argument("City name cannot be empty");

                    cout << "Enter distance to home city: ";
                    cin >> distance;
                    if (distance < 0) throw invalid_argument("Distance must be non-negative");

                    City city(cityName, distance, storehouse);

                    // Display the city
                    cout << "City:\n";
                    cout << city.toString();
                    break;
                }
                case 7: {
                    cout << "Exiting...\n";
                    break;
                }
                default:
                    cout << "Invalid choice. Please try again.\n";
            }
        } while (choice != 7);
    } catch (const exception &e) {
        cerr << "Error: " << e.what() << endl;
    }

    return 0;
}
