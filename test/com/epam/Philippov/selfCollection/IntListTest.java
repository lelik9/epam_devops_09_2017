package com.epam.Philippov.selfCollection;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntListTest {

  @Test
  void add() {
    IntList list = new IntList();
    list.add(10);
    assertEquals(10, list.get(0));
    list.add(-5);
    assertEquals(-5, list.get(1));
  }

  @Test
  void add1() {
    IntList list = new IntList();
    list.add(10, 0);
    list.add(10,1);
    list.add(10, 3);
    list.add(5, 1);
    assertEquals(10, list.get(0));
    assertEquals(5, list.get(1));
    assertEquals(3, list.size());
    assertEquals(10, list.get(2));
  }

  @Test
  void set() {
    IntList list = new IntList();
    list.set(5, 0);
    list.set(10, 0);
    assertEquals(10, list.get(0));
    list.set(10, 5);
    assertEquals(-1, list.get(5));
    assertEquals(1, list.size());
  }

  @Test
  void remove() {
    IntList list = new IntList();
    list.add(10);
    list.add(10);
    list.add(10);
    assertEquals(true, list.remove(10));
    assertEquals(2, list.size());
    assertEquals(false, list.remove(5));
    assertEquals(2, list.size());
  }

  @Test
  void removeAll() {
    IntList list = new IntList();
    list.add(10);
    list.add(10);
    list.add(10);
    assertEquals(true, list.removeAll(10));
    assertEquals(0, list.size());
  }

  @Test
  void removeFrom() {
    IntList list = new IntList();
    list.add(10);
    list.add(5);
    assertEquals(5, list.removeFrom(1));
  }

  @Test
  void get() {
    IntList list = new IntList();
    assertEquals(-1, list.get(0));
    list.add(10);
    list.add(5);
    assertEquals(10, list.get(0));
  }

  @Test
  void toArray() {
    IntList list = new IntList();
    assertEquals(true, Arrays.equals(new int[0], list.toArray()));
    list.add(10);
    list.add(5);
    assertEquals(true, Arrays.equals(new int[]{10, 5}, list.toArray()));
  }

  @Test
  void size() {
    IntList list = new IntList();
    assertEquals(0, list.size());
    list.add(10);
    list.add(5);
    assertEquals(2, list.size());
  }

  @Test
  void indexOf() {
    IntList list = new IntList();
    list.add(10);
    list.add(5);
    assertEquals(1, list.indexOf(5));
    assertEquals(-1, list.indexOf(15));
  }

  @Test
  void lastIndexOf() {
    IntList list = new IntList();
    list.add(10);
    list.add(5);
    list.add(5);
    list.add(5);
    assertEquals(3, list.lastIndexOf(5));
    assertEquals(-1, list.lastIndexOf(15));
  }

  @Test
  void trimToSize() {
    IntList list = new IntList();
    list.add(10);
    list.add(5);
    list.add(5);
    list.add(5);
  }

}