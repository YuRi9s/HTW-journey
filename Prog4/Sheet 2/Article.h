//
// Created by yuri9 on 01/07/2024.
//

#ifndef ARTICLE_H
#define ARTICLE_H

#include <string>
#include <iomanip>


using namespace std;

/**
 * @class Article
 * @brief Represents an article with a unique number, description, stock, and price.
 */
class Article {
private:
    int articleNumber; ///< Unique article number
    string description; ///< Description of the article
    int stock; ///< Current stock of the article
    double price; ///< Price of the article
    double tempPrice = price; ///< only usable if price was negative

public:
    /**
     * @brief Constructor with all attributes.
     * @param articleNumber Unique number of the article.
     * @param description Description of the article.
     * @param price Price of the article.
     * @param stock Current stock of the article.
     * @throw invalid_argument if price or stock is negative.
     */
    Article(int articleNumber, const string &description, double price, int stock);

    /**
     * @brief Constructor without stock (default stock to 0).
     * @param articleNumber Unique number of the article.
     * @param description Description of the article.
     * @param price Price of the article.
     */
    Article(int articleNumber, const string &description, double price);

    /**
     * @brief Increases the stock by the specified amount.
     * @param amount Amount to increase the stock.
     * @throw invalid_argument if amount is negative.
     */
    void increaseStock(int amount);

    /**
     * @brief Reduces the stock by the specified amount.
     * @param amount Amount to reduce the stock.
     * @throw invalid_argument if amount is negative or greater than current stock.
     */
    void reduceStock(int amount);

    /**
     * @brief Changes the price by a specified percentage.
     * @param percent Percentage to change the price (positive for increase, negative for decrease).
     * @throw invalid_argument if resulting price is negative.
     */
    void changePrice(double percent);

    /**
     * @brief Gets the article number.
     * @return The article number.
     */
    int getArticleNumber() const;

    /**
     * @brief Gets the description of the article.
     * @return The description of the article.
     */
    string getDescription() const;

    /**
     * @brief Gets the current stock of the article.
     * @return The current stock of the article.
     */
    int getStock() const;

    /**
     * @brief Gets the price of the article.
     * @return The price of the article.
     */
    double getPrice() const;

    /**
     * @brief Returns a string representation of the article in table format.
     * @return String representation of the article.
     */
    string toString() const;
};

#endif // ARTICLE_H
