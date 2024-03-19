package ru.spbu.apcyb.svp.tasks.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {
    private MyQueue<String> myQueue;

    @BeforeEach
    void setUp() {
        myQueue = new MyQueue<String>();
    }

    @Test
    void sizeIfEmpty() {
        assertEquals(0, myQueue.size());
    }

    @Test
    void sizeIfNotEmpty() {
        myQueue.add("element");
        assertEquals(1, myQueue.size());
    }

    @Test
    void isEmptyIfEmpty() {
        assertTrue(myQueue.isEmpty());
    }

    @Test
    void isEmptyIfNotEmpty() {
        myQueue.add("element");
        assertFalse(myQueue.isEmpty());
    }

    @Test
    void peekIfEmpty() {
        assertNull(myQueue.peek());
    }

    @Test
    void peekIfNotEmpty() {
        myQueue.add("elementOne");
        myQueue.add("elementTwo");
        assertEquals("elementOne", myQueue.peek());
    }

    @Test
    void add() {
        myQueue.add("element");
        assertEquals("element", myQueue.peek());
    }


    @Test
    void containsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> myQueue.contains(" "));
    }

    @Test
    void iteratorUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> myQueue.iterator());
    }

    @Test
     void toArrayUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> myQueue.toArray());
    }

    @Test
    void toArrayWithObjectUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> myQueue.toArray(new Object[0]));
    }

    @Test
    void removeUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> myQueue.remove(" "));
    }

    @Test
    void addAllUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> myQueue.addAll(new MyQueue<String>()));
    }

    @Test
    void clearUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> myQueue.clear());
    }

    @Test
    void retainAllUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> myQueue.retainAll(new MyQueue<String>()));
    }

    @Test
    void removeAllUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> myQueue.removeAll(new MyQueue<String>()));
    }

    @Test
    void containsAllUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> myQueue.containsAll(new MyQueue<String>()));
    }

    @Test
    void offerUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> myQueue.offer(""));
    }

    @Test
    void removeTheHeadUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> myQueue.remove());
    }
    @Test
    void pollUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> myQueue.poll());
    }

    @Test
    void elementUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> myQueue.element());
    }



}