package com.epam.se2.lesson14;

import com.epam.se2.lesson14.GenericArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

class GenericArrayListTest {
    @Test
    void add() {
        GenericArrayList<Integer> list = new GenericArrayList<>();
        list.add(10);

        assertEquals(new Integer[]{1}, list.toArray());
    }

    @Test
    void add1() {
    }

    @Test
    void set() {
    }

}