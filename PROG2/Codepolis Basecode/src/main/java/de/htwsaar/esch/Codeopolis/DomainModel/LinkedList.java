package de.htwsaar.esch.Codeopolis.DomainModel;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.Iterator;
import java.util.function.Function;

/**
 * A generic singly linked list implementation.
 *
 * @param <T> the type of elements in this list
 */
public class LinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private int size;

    /**
     * A node in the linked list.
     *
     * @param <T> the type of element stored in the node
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param value the element to add
     */
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Returns a new linked list containing elements that match the given predicate.
     *
     * @param predicate the predicate to apply to each element
     * @return a new linked list with elements that match the predicate
     */
    public LinkedList<T> filter(Predicate<T> predicate) {
        LinkedList<T> result = new LinkedList<>();
        for (T item : this) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Removes all elements that match the given predicate from the list.
     *
     * @param predicate the predicate to apply to each element
     */
    public void removeIf(Predicate<T> predicate) {
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null) {
            if (predicate.test(current.value)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
            } else {
                previous = current;
            }
            current = current.next;
        }
    }

    /**
     * Adds an element to the list if it matches the given predicate.
     *
     * @param value     the element to add
     * @param predicate the predicate to apply to the element
     */
    public void addIf(T value, Predicate<T> predicate) {
        if (predicate.test(value)) {
            add(value);
        }
    }

    /**
     * Sorts the elements in the list based on the given comparator.
     *
     * @param comparator the comparator to determine the order of the list
     */
    public void sort(Comparator<T> comparator) {
        if (size > 1) {
            boolean wasChanged;
            do {
                Node<T> current = head;
                Node<T> previous = null;
                Node<T> next = head.next;
                wasChanged = false;
                while (next != null) {
                    if (comparator.compare(current.value, next.value) > 0) {
                        wasChanged = true;
                        if (previous != null) {
                            Node<T> tmp = next.next;
                            previous.next = next;
                            next.next = current;
                            current.next = tmp;
                        } else {
                            Node<T> tmp = next.next;
                            head = next;
                            next.next = current;
                            current.next = tmp;
                        }
                        previous = next;
                        next = current.next;
                    } else {
                        previous = current;
                        current = next;
                        next = next.next;
                    }
                }
            } while (wasChanged);
        }
    }

    /**
     * Sums the elements in the list based on the given mapper function.
     *
     * @param mapper the function to map each element to a double value
     * @return the sum of the mapped double values
     */
    public int sum(Function<T, Integer> mapper) {
        int sum = 0;
        for (T item : this) {
            sum += mapper.apply(item);
        }
        return sum;
    }

    /**
     * Checks if the linked list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns an iterator over the elements in the linked list.
     *
     * @return an iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * An iterator over the elements in the linked list.
     */
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        /**
         * Checks if there are more elements in the linked list.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the linked list.
         *
         * @return the next element
         * @throws RuntimeException if there are no more elements
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new RuntimeException("No more elements");
            }
            T value = current.value;
            current = current.next;
            return value;
        }
    }

    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }
}
