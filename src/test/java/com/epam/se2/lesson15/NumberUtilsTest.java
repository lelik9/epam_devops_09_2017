package com.epam.se2.lesson15;

import com.epam.se2.lesson15.NumberUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


class NumberUtilsTest {
    @Test
    void sum() {
        NumberUtils instance = new NumberUtils();

        Number result = instance.sum(10, 20);

        assertEquals(30, result.intValue());
    }

}