package com.epam.Philippov.selfCollection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntLinkedListTest {

  @Test
  void baseConstructor() {
    IntLinkedList list = new IntLinkedList();

    assertEquals(0, list.size());
  }

  @Test
  void constructorWithArgs() {
    IntLinkedList list = new IntLinkedList(1,3,5,7);

    assertEquals(1, list.get(0));
    assertEquals(5, list.get(2));

  }

  @Test
  void add() {
    IntLinkedList list = new IntLinkedList();
    list.add(2);
    list.add(5);
    list.add(5);
    list.add(5);
    list.add(9);

    assertEquals(2, list.get(0));
    assertEquals(5, list.get(1));
    assertEquals(9, list.get(4));
  }

  @Test
  void add1() {
    IntLinkedList list = new IntLinkedList();
    list.add(2, 0);
    list.add(9);
    list.add(5, 1);

    assertEquals(2, list.get(0));
    assertEquals(5, list.get(1));
  }

  @Test
  void set() {
  }

  @Test
  void remove() {
  }

  @Test
  void removeAll() {
    IntLinkedList list = new IntLinkedList();
    list.add(2);
    list.add(2);
    list.add(2);
    list.add(5);
    list.removeAll(2);

    assertEquals(1, list.size());
  }

  @Test
  void removeFrom() {
  }

  @Test()
  void get() {
    IntLinkedList list = new IntLinkedList();
//    assertThrows(IndexOutOfBoundsException.class, list.get, "Index out of range");
  }

  @Test
  void toArray() {
  }

  @Test
  void size() {
  }

  @Test
  void indexOf() {
    IntLinkedList list = new IntLinkedList();
    list.add(2);
    list.add(5);
    list.add(6);
    list.add(8);
    list.add(9);

    assertEquals(2, list.indexOf(6));
    assertEquals(-1, list.indexOf(61));
  }

  @Test
  void lastIndexOf() {
  }

}