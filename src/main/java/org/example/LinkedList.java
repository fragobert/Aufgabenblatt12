package org.example;

import java.util.Comparator;

public class LinkedList<T extends Comparable<?>> implements Comparable<LinkedList<?>> {
    private Node<T> head;
    private int size;

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value);
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(value);
        }
        size++;
    }


    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T removedValue;
        if (index == 0) {
            removedValue = head.value;
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedValue = current.next.value;
            current.next = current.next.next;
        }
        size--;
        return removedValue;
    }

    public int size() {
        return size;
    }

    public void sort() {
        // Hier kann ein Sortieralgorithmus implementiert werden, z.B. Merge Sort, Bubble Sort, usw.
        // Wichtig ist, dass der Sortieralgorithmus stabil ist.
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public String toFlatString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            current = current.next;
        }
        return sb.toString();
    }

    @Override
    public int compareTo(LinkedList<?> other) {
        Node<T> thisCurrent = head;
        Node<?> otherCurrent = other.head;

        while (thisCurrent != null && otherCurrent != null) {
            int cmp = thisCurrent.compareTo(otherCurrent);
            if (cmp != 0) {
                return cmp;
            }
            thisCurrent = thisCurrent.next;
            otherCurrent = otherCurrent.next;
        }

        return Integer.compare(this.size, other.size);
    }

    private static class Node<T extends Comparable<?>> implements Comparable<Node<?>> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public int compareTo(Node<?> other) {
            if (this.value instanceof LinkedList) {
                if (other.value instanceof LinkedList) {
                    return ((LinkedList<?>) this.value).compareTo((LinkedList<?>) other.value);
                }
                return -1;
            } else if (this.value instanceof Integer) {
                if (other.value instanceof Integer) {
                    return ((Integer) this.value).compareTo((Integer) other.value);
                }
                return other.value instanceof LinkedList ? 1 : -1;
            } else if (this.value instanceof Double || this.value instanceof Float) {
                if (other.value instanceof Double || other.value instanceof Float) {
                    return Double.compare(((Number) this.value).doubleValue(), ((Number) other.value).doubleValue());
                }
                return other.value instanceof LinkedList || other.value instanceof Integer ? 1 : -1;
            } else if (this.value instanceof Boolean) {
                if (other.value instanceof Boolean) {
                    return ((Boolean) this.value).compareTo((Boolean) other.value);
                }
                return other.value instanceof LinkedList || other.value instanceof Integer || other.value instanceof Double || other.value instanceof Float ? 1 : -1;
            } else if (this.value instanceof String) {
                if (other.value instanceof String) {
                    return ((String) this.value).compareTo((String) other.value);
                }
                return other.value instanceof LinkedList || other.value instanceof Integer || other.value instanceof Double || other.value instanceof Float || other.value instanceof Boolean ? 1 : -1;
            } else {
                if (other.value instanceof Object) {
                    return this.value.toString().compareTo(other.value.toString());
                }
                return 1;
            }
        }
    }

    // Implementierung eines Beispiel-Sortieralgorithmus (Bubble Sort)
    public void sortList() {
        if (size <= 1) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            Node<T> previous = null;
            Node<T> next = head.next;

            for (int i = 0; i < size - 1; i++) {
                if (current.compareTo(next) > 0) {
                    if (previous != null) {
                        previous.next = next;
                    } else {
                        head = next;
                    }

                    current.next = next.next;
                    next.next = current;

                    previous = next;
                    next = current.next;
                    swapped = true;
                } else {
                    previous = current;
                    current = next;
                    next = next.next;
                }
            }
        } while (swapped);
    }
}
