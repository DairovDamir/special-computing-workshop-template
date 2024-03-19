package ru.spbu.apcyb.svp.tasks.task2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyQueue<T> implements Queue<T> {

    private final MyLinkedList<T> queue = new MyLinkedList<T>();

    int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public T peek() {
        if (queue.isEmpty()) {
            return null;
        } else {
            return queue.get(0);
        }
    }

    @Override
    public boolean add(T o) {
        queue.add(o);
        size++;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("the method contains  is not overridden");
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("the method iterator is not overridden");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("the method toArray is not overridden");
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("the method toArray is not overridden");
    }


    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("the method remove is not overridden");
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("the method addAll is not overridden");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("the method clear is not overridden");
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("the method retainAll is not overridden");
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("the method removeAll is not overridden");
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("the method containsAll is not overridden");
    }

    @Override
    public boolean offer(Object o) {
        throw new UnsupportedOperationException("the method offer is not overridden");
    }

    @Override
    public T remove() {
        throw new UnsupportedOperationException("the method remove is not overridden");
    }

    @Override
    public T poll() {
        throw new UnsupportedOperationException("the method poll is not overridden");
    }

    @Override
    public T element() {
        throw new UnsupportedOperationException("the method element is not overridden");
    }
}

