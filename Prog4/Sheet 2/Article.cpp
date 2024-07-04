//
// Created by yuri9 on 01/07/2024.
//

#include "Article.h"

Article::Article(int articleNumber, const string &description, double price, int stock)
    : articleNumber(articleNumber), description(description), price(price), stock(stock) {
    if (price < 0 || stock < 0) {
        throw invalid_argument("Price and stock must be non-negative");
    }
}

Article::Article(int articleNumber, const string &description, double price)
    : Article(articleNumber, description, price, 0) {
}

void Article::increaseStock(int amount) {
    if (amount < 0) {
        throw invalid_argument("Amount must be non-negative");
    }
    stock += amount;
}

void Article::reduceStock(int amount) {
    if (amount < 0 || amount > stock) {
        throw invalid_argument("Invalid amount");
    }
    stock -= amount;
}

void Article::changePrice(double percent) {
    price += price * percent / 100;
    if (price < 0) {
        this->price = tempPrice;
        throw invalid_argument("Price cannot be negative");
    }
}

int Article::getArticleNumber() const { return articleNumber; }
string Article::getDescription() const { return description; }
int Article::getStock() const { return stock; }
double Article::getPrice() const { return price; }

string Article::toString() const {
    stringstream ss;
    ss << "| " << setw(12) << articleNumber
            << " | " << setw(20) << description
            << " | " << setw(10) << stock
            << " | " << setw(10) << fixed << setprecision(2) << price
            << " |";
    return ss.str();
}
