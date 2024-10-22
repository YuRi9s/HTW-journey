#ifndef STACK_CPP
#define STACK_CPP

#include "Stack.h"

template <typename T>
void Stack<T>::push(const T& item) {
    elements.push_back(item);
}

template <typename T>
void Stack<T>::pop() {
    if (elements.empty()) throw std::out_of_range("Stack is empty");
    elements.pop_back();
}

template <typename T>
T Stack<T>::top() const {
    if (elements.empty()) throw std::out_of_range("Stack is empty");
    return elements.back();
}

template <typename T>
bool Stack<T>::isEmpty() const {
    return elements.empty();
}

template <typename T>
int Stack<T>::size() const {
    return elements.size();
}

#endif // STACK_CPP
