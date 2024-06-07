package de.htwsaar.esch.Codeopolis.DomainModel;

import java.util.Iterator;

/**
 * Represents a singly linked list data structure.
 * Added for the sake of (Übung 4) so that Depot Silo and Harvest are modified
 * so that
 * we can change the list size dynamically
 *
 * @param <T> the type of elements in the linked list, must extend Comparable
 */
public class LinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private int size;

    /**
     * Represents a node in the linked list.
     */
    private static class Node<T> {
        private T value;
        private Node<T> next;

        /**
         * Constructs a node with the specified value.
         *
         * @param value the value of the node
         */
        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Adds an element to the end of the linked list.
     *
     * @param element the element to add
     */
    public void addLast(T element) {
        Node<T> newNode = new Node<>(element);
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
     * Removes and returns the first element from the linked list.
     *
     * @return the removed element, or null if the list is empty
     */
    public T removeFirst() {
        if (head == null) {
            return null;
        }
        T removedData = head.value;
        head = head.next;
        size--;
        return removedData;
    }

    /**
     * Checks if the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the linked list.
     *
     * @return the size of the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Gets the element at the specified index in the linked list.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public T get(int index) {
        if (index >= 0 && index < size) {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    /**
     * Replaces the element at the specified index in the linked list with the
     * specified element.
     *
     * @param index   the index of the element to replace
     * @param element the new element
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T oldData = current.value;
        current.value = element;
        return oldData;
    }

    /**
     * Removes all elements from the linked list.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Sorts the elements of the linked list using the bubble sort algorithm.
     */
    public void bubbleSort() {
        if (head == null || head.next == null) {
            return;
        }

        boolean swapped = true;
        while (swapped) {
            swapped = false;
            Node<T> current = head;
            while (current.next != null) {
                if (current.value.compareTo(current.next.value) > 0) {
                    // swap
                    T temp = current.value;
                    current.value = current.next.value;
                    current.next.value = temp;
                    swapped = true;
                }
                current = current.next;
            }
        }
    }

    /**
     * Removes and returns the element at the specified index in the linked list.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            return removeFirst();
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        T removedData = current.next.value;
        current.next = current.next.next;
        size--;
        return removedData;
    }

    /**
     * Returns an iterator over the elements in the linked list.
     *
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Represents an iterator over the elements in the linked list.
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
            T data = current.value;
            current = current.next;
            return data;
        }
    }
}
