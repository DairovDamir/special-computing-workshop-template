package ru.spbu.apcyb.svp.tasks.task2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public  class MyLinkedList<T> implements List<T> {
    private  Node<T> head;
    private  Node<T> tail;
    private int size = 0;

    @Override
    public boolean isEmpty() {//проверка пустоты
        return head == null;
    }

    @Override
    public T get(int index) { //получение по индексу
        if (isIndexIn(index)) {
            Node<T> x;
            if (index < size / 2) {
                x = head;
                for (int i = 0; i < index; i++) {
                    x = x.next;
                }
            } else {
                x = tail;
                for (int i = size - 1; i > index; i--) {
                    x = x.prev;
                }
            }
            return x.item;
        } else {throw new IndexOutOfBoundsException("The index is not correct");}
    }

    @Override
    public boolean contains(Object o) { //Проверка наличия по значению
        Node<T> x = head;
        for (int i = 0; i < size; i++) {
            if (o == x.item) {return true;}
            else {x = x.next;}
        }
        return false;
    }

    @Override
    public boolean add(T o) {//добавление в конец
        Node<T> x;
        if (size == 0) {
            x = new Node<>(null, o, null);
            head = x;
        } else {
            x = new Node<>(tail, o, null);
            tail.next = x;
        }
        tail = x;
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) { //добавление в конкретную позицию
        if (index >= 0 && index <= size) {
            Node<T> x;
            if (index == 0) {
                x = head;
                Node<T> newElement = new Node<>(null, element, x);
                x.prev = newElement;
                head = newElement;
                if (size == 1) {tail = newElement;};
                size++;
            } else if (index == size) {
                add(element);
            } else if (index <= size/2) {
                x = head;
                for (int i = 0; i < index; i++) {
                    x = x.next;
                }
                Node<T> newElement = new Node<>(x.prev, element, x);
                (x.prev).next = newElement;
                x.prev = newElement;
                size++;
            } else {
                x = tail;
                for (int i = size - 1; i > index; i--){
                    x = x.prev;
                }
                Node<T> newElement = new Node<>(x.prev, element, x);
                (x.prev).next = newElement;
                x.prev = newElement;
                size++;
            }
        } else {throw new IndexOutOfBoundsException("The index is not correct");}
    }

    @Override
    public T remove(int index) {//удаление по индексу
        if (isIndexIn(index)) {
            Node<T> x;
            if (index == 0) {
                if (size == 1) {
                    head = null;
                    tail = null;
                } else{
                    x = head;
                    head = x.next;
                    head.prev = null;
                }
            } else if(index == size - 1) {
                x = tail;
                tail = x.prev;
                tail.next = null;
            } else {
                if (index <= size / 2) {
                    x = head;
                    for (int i = 0; i < index; i++) {
                        x = x.next;
                    }
                } else {
                    x = tail;
                    for (int i = size - 1; i > index; i--) {
                        x = x.prev;
                    }
                }
                (x.prev).next = x.next;
                (x.next).prev = x.prev;
            }
            size--;
        } else {throw new IndexOutOfBoundsException("The index is not correct");}
    return null;
    }

    @Override
    public int size()  {
        return size;
    }

    private boolean isIndexIn(int index) { //Проверка корректности индекса
        return (index >= 0 && index < size);
    }

    @Override
    public Iterator iterator() {
        throw  new  UnsupportedOperationException("the method iterator is not overridden");
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
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException("the method toArray is not overridden");
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
    public Object set(int index, Object element) {
        throw new UnsupportedOperationException("the method set is not overridden");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("the method indexOf is not overridden");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("the method lastIndexOf is not overridden");
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("the method listIterator is not overridden");
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException("the method listIterator is not overridden");
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("the method subList is not overridden");
    }

    private static class Node<T>{
        T item;
        Node<T> next = null;
        Node<T> prev = null;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }
}

