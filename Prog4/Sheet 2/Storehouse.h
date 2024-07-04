//
// Created by yuri9 on 01/07/2024.
//

#ifndef STOREHOUSE_H
#define STOREHOUSE_H

#include "Article.h"
#include <vector>

using namespace std;

/**
 * @class Storehouse
 * @brief Manages a collection of articles.
 */
class Storehouse {
private:
    vector<Article *> articles; ///< Container for article pointers
    int maxArticles; ///< Maximum number of articles in the storehouse

public:
    /**
     * @brief Constructor with specified size.
     * @param size Maximum number of articles.
     * @throw invalid_argument if size is not positive.
     */
    Storehouse(int size);

    /**
     * @brief Default constructor with size 10.
     */
    Storehouse();

    /**
     * @brief Adds an article to the storehouse.
     * @param article Pointer to the article to be added.
     * @throw overflow_error if storehouse is full.
     */
    void addArticle(Article *article);

    /**
     * @brief Removes an article from the storehouse by its number.
     * @param articleNumber The number of the article to be removed.
     * @throw invalid_argument if article is not found.
     */
    void removeArticle(int articleNumber);

    /**
     * @brief Increases the stock of a specified article.
     * @param articleNumber The number of the article.
     * @param amount The amount to increase the stock.
     * @throw invalid_argument if article is not found.
     */
    void increaseStock(int articleNumber, int amount);

    /**
     * @brief Reduces the stock of a specified article.
     * @param articleNumber The number of the article.
     * @param amount The amount to reduce the stock.
     * @throw invalid_argument if article is not found.
     */
    void reduceStock(int articleNumber, int amount);

    /**
     * @brief Changes the price of a specified article.
     * @param articleNumber The number of the article.
     * @param percent The percentage to change the price.
     * @throw invalid_argument if article is not found.
     */
    void changePrice(int articleNumber, int percent);

    /**
     * @brief Changes the price of all articles.
     * @param percent The percentage to change the price.
     */
    void changePrice(int percent);

    /**
     * @brief Calculates the total value of all articles in the storehouse.
     * @return The total value of all articles.
     */
    double calculateTotalValue() const;

    /**
     * @brief Finds an article by its number.
     * @param articleNumber The number of the article.
     * @return Pointer to the article if found, nullptr otherwise.
     */
    Article *findArticle(int articleNumber) const;

    /**
     * @brief Checks if the storehouse is full.
     * @return True if the storehouse is full, false otherwise.
     */
    bool isFull() const;

    /**
     * @brief Checks if the storehouse is empty.
     * @return True if the storehouse is empty, false otherwise.
     */
    bool isEmpty() const;

    /**
     * @brief Returns the number of articles in the storehouse.
     * @return The number of articles.
     */
    int printArticleCount() const;

    /**
     * @brief Returns a string representation of the storehouse in table format.
     * @return String representation of the storehouse.
     */
    string toString() const;

    /**
     * @brief Copy constructor for deep copying.
     * @param other The storehouse to copy from.
     */
    Storehouse(const Storehouse &other);

    /**
     * @brief Assignment operator for deep copying.
     * @param other The storehouse to copy from.
     * @return Reference to the assigned storehouse.
     */
    Storehouse &operator=(const Storehouse &other);

    /**
     * @brief Destructor to clean up resources.
     */
    ~Storehouse();
};

#endif // STOREHOUSE_H
