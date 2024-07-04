//
// Created by yuri9 on 01/07/2024.
//

#include "Storehouse.h"

#include <algorithm>

Storehouse::Storehouse(int size) : maxArticles(size) {
    if (size <= 0) {
        throw invalid_argument("Storehouse size must be positive");
    }
}

Storehouse::Storehouse() : Storehouse(10) {
}

void Storehouse::addArticle(Article *article) {
    if (articles.size() >= maxArticles) {
        throw overflow_error("Storehouse is full");
    }
    articles.push_back(article);
}

void Storehouse::removeArticle(int articleNumber) {
    auto it = find_if(articles.begin(), articles.end(),
                      [articleNumber](Article *article) { return article->getArticleNumber() == articleNumber; });

    if (it != articles.end()) {
        delete *it;
        articles.erase(it);
    } else {
        throw invalid_argument("Article not found");
    }
}

void Storehouse::increaseStock(int articleNumber, int amount) {
    Article *article = findArticle(articleNumber);
    if (article) {
        article->increaseStock(amount);
    } else {
        throw invalid_argument("Article not found");
    }
}

void Storehouse::reduceStock(int articleNumber, int amount) {
    Article *article = findArticle(articleNumber);
    if (article) {
        article->reduceStock(amount);
    } else {
        throw invalid_argument("Article not found");
    }
}

void Storehouse::changePrice(int articleNumber, int percent) {
    Article *article = findArticle(articleNumber);
    if (article) {
        article->changePrice(percent);
    } else {
        throw invalid_argument("Article not found");
    }
}

void Storehouse::changePrice(int percent) {
    for (Article *article: articles) {
        article->changePrice(percent);
    }
}

double Storehouse::calculateTotalValue() const {
    double total = 0;
    for (const Article *article: articles) {
        total += article->getPrice() * article->getStock();
    }
    return total;
}

Article *Storehouse::findArticle(int articleNumber) const {
    auto it = find_if(articles.begin(), articles.end(),
                      [articleNumber](Article *article) { return article->getArticleNumber() == articleNumber; });

    return (it != articles.end()) ? *it : nullptr;
}

bool Storehouse::isFull() const {
    return articles.size() >= maxArticles;
}

bool Storehouse::isEmpty() const {
    return articles.empty();
}

int Storehouse::printArticleCount() const {
    return articles.size();
}

string Storehouse::toString() const {
    stringstream ss;
    ss << "+--------------+----------------------+------------+------------+\n";
    ss << "| Article Num  | Description          | Stock      | Price      |\n";
    ss << "+--------------+----------------------+------------+------------+\n";
    for (const Article *article: articles) {
        ss << article->toString() << "\n";
    }
    ss << "+--------------+----------------------+------------+------------+\n";
    return ss.str();
}

Storehouse::Storehouse(const Storehouse &other) : maxArticles(other.maxArticles) {
    for (const Article *article: other.articles) {
        articles.push_back(new Article(*article));
    }
}

Storehouse &Storehouse::operator=(const Storehouse &other) {
    if (this != &other) {
        for (Article *article: articles) {
            delete article;
        }
        articles.clear();

        maxArticles = other.maxArticles;
        for (const Article *article: other.articles) {
            articles.push_back(new Article(*article));
        }
    }
    return *this;
}

Storehouse::~Storehouse() {
    for (Article *article: articles) {
        delete article;
    }
}
