package com.epam.practice;

import com.epam.practice.Example10;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


class Example10Test {


    @Test
    void testOne() {
        assertTrue(Example10.isPowerOfTwo(1));
        assertTrue(Example10.isPowerOfTwo(2));
        assertTrue(Example10.isPowerOfTwo(128));

        assertFalse(Example10.isPowerOfTwo(0));
        assertFalse(Example10.isPowerOfTwo(-5));
        assertFalse(Example10.isPowerOfTwo(100));
    }
}