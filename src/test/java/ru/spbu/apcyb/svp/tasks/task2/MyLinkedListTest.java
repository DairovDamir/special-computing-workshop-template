package ru.spbu.apcyb.svp.tasks.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private MyLinkedList<String> linkedList;

    @BeforeEach
    void setUp(){
        linkedList = new MyLinkedList<>();
    }

    @Test
    void isEmptyIfEmpty() {
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void isEmptyIfNotEmpty() {
        linkedList.add("element");
        assertFalse(linkedList.isEmpty());
    }

    @Test
    void getIfIndexIsNegative() {
        linkedList.add("element");
        assertThrows(IndexOutOfBoundsException.class,
                () -> linkedList.get(-1));
    }

    @Test
    void getIfIndexIsBiggerThanTailIndex() {
        linkedList.add("element");
        assertThrows(IndexOutOfBoundsException.class,
                () -> linkedList.get(1));;
    }

    @Test
    void getIfIndexIsCorrect() {
        linkedList.add("element");
        assertEquals("element", linkedList.get(0));
    }

    @Test
    void containsIfFalse() {
        linkedList.add("1");
        assertFalse(linkedList.contains("2"));
    }

    @Test
    void containsIfTrue() {
        linkedList.add("1");
        assertTrue(linkedList.contains("1"));
    }

    @Test
    void addInTheEnd() {
        linkedList.add("1");
        assertEquals("1", linkedList.get(0));

    }

    @Test
    void addByTheIndex() {
        linkedList.add("0");
        linkedList.add("1");
        linkedList.add("3");
        linkedList.add(2, "2");
        assertEquals("2", linkedList.get(2));
    }

    @Test
    void removeIfListIsNotEmpty() {
        linkedList.add("0");
        linkedList.add("1");
        linkedList.add("2");
        linkedList.remove(1);
        assertEquals("2", linkedList.get(1));
    }

    @Test
    void removeIfListIsEmpty() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> linkedList.remove(2));
    }

    @Test
    void removeIfIndexOfElementIsBiggerThanListSize() {
        linkedList.add("0");
        linkedList.add("1");
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(2));
    }

    @Test
    void sizeIfListIsEmpty() {
        assertEquals(0, linkedList.size());
    }

    @Test
    void sizeIfListIsNotEmpty() {
        linkedList.add("element");
        assertEquals(1, linkedList.size());

    }

    @Test
    void iteratorUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.iterator());
    }

    @Test
    void toArrayUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.toArray());
    }

    @Test
    void toArrayWithArgumentsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.toArray(new Object[0]));
    }

    @Test
    void removeUnsupportedOperationException() {
        String str = " ";
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.remove(str));

    }

    @Test
    void addAllUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.addAll(new MyLinkedList<String>()));
    }

    @Test
    void addAllWithIndexUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.addAll(0, new MyLinkedList<String>()));
    }

    @Test
    void clearUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.clear());
    }

    @Test
    void retainAllUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.retainAll(new MyLinkedList<String>()));
    }

    @Test
    void removeAllUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.removeAll(new MyLinkedList<String>()));
    }

    @Test
    void containsAllUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.containsAll(new MyLinkedList<String>()));
    }

    @Test
    void setUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.set(0, " "));
    }

    @Test
    void indexOfUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.indexOf(" "));
    }

    @Test
    void lastIndexOfUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.lastIndexOf(" "));
    }

    @Test
    void listIteratorUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.listIterator());
    }
    @Test
    void listIteratorWithIndexUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.listIterator(0));
    }

    @Test
    void subListUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> linkedList.subList(0, 10));
    }
}
