package com.example.homework2_14;

import com.example.homework2_14.Exception.InvalidIndexException;
import com.example.homework2_14.Exception.NullItemException;
import com.example.homework2_14.Exception.StorageIsFullException;
import com.example.homework2_14.Impl.IntegerListImpl;
import org.junit.jupiter.api.Test;

import static com.example.homework2_14.Impl.IntegerListImpl.*;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerListImplTest {
    @Test
    void add() {
        IntegerListImpl m = new IntegerListImpl(5);
        m.add(1);
        assertTrue(m.contains(1));
    }
    @Test
    void addByIndex() {
        IntegerListImpl m = new IntegerListImpl(5);
        m.add(0, 1);
        m.add(1, 2);
        assertTrue(m.contains(1));
        assertTrue(m.contains(2));
    }

    @Test
    void set() {
        IntegerListImpl m = new IntegerListImpl(5);
        m.add(1);
        m.set(0, 2);
        assertNotEquals(1, 2);
        assertTrue(m.contains(2));
    }

    @Test
    void removeByItem() {
        IntegerListImpl m = new IntegerListImpl(5);
        m.add(1);
        assertTrue(m.contains(1));
        m.remove(1);
        assertFalse(m.contains(1));
    }

    @Test
    void removeByIndex() {
        IntegerListImpl m = new IntegerListImpl(5);
        m.add(0, 1);
        assertTrue(m.contains(1));
        m.remove(0);
        assertFalse(m.contains(1));
    }

    @Test
    void testNullItemException() {
        IntegerListImpl m = new IntegerListImpl(5);
        assertThrows(NullItemException.class, () -> m.add(null));
    }

    @Test
    void testStorageIsFullException() {
        IntegerListImpl m = new IntegerListImpl(5);
        m.add(1);
        m.add(2);
        m.add(3);
        m.add(4);
        m.add(5);
        assertThrows(StorageIsFullException.class, () -> m.add(6));
    }

    @Test
    void testInvalidIndexException() {
        IntegerListImpl m = new IntegerListImpl(5);
        assertThrows(InvalidIndexException.class, () -> m.add(-1, 1));
    }
}
