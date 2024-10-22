#ifndef STACK_H
#define STACK_H

#include <vector>
#include <stdexcept>

/**
 * @class Stack
 * @brief Template class representing a stack data structure.
 * @tparam T Type of elements in the stack
 */
template <typename T>
class Stack {
private:
    std::vector<T> elements; ///< Vector to store stack elements

public:
    /**
     * @brief Pushes an element onto the stack.
     * @param item Element to be pushed
     */
    void push(const T& item);

    /**
     * @brief Pops the top element from the stack.
     */
    void pop();

    /**
     * @brief Returns the top element of the stack.
     * @return Top element of the stack
     */
    T top() const;

    /**
     * @brief Checks if the stack is empty.
     * @return True if the stack is empty, false otherwise
     */
    bool isEmpty() const;

    /**
     * @brief Returns the size of the stack.
     * @return Size of the stack
     */
    int size() const;
};

#include "Stack.cpp" // Include the implementation file

#endif // STACK_H
