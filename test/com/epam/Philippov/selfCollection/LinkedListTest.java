package com.epam.Philippov.selfCollection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListTest {

  @Test
  void baseConstructor() {
    LinkedList list = new LinkedList();

    assertEquals(0, list.size());
  }

  @Test
  void constructorWithArgs() {
    LinkedList<Integer> list = new LinkedList<>(1,3,5,7);

    assertEquals(new Integer(1), list.get(0));
    assertEquals(new Integer(5), list.get(2));

  }

  @Test
  void add() {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(2);
    list.add(5);
    list.add(5);
    list.add(5);
    list.add(9);

    assertEquals(new Integer(2), list.get(0));
    assertEquals(new Integer(5), list.get(1));
    assertEquals(new Integer(9), list.get(4));
  }
//
//  @Test
//  void add1() {
//    LinkedList<Integer> list = new LinkedList<>();
//    list.add(2, 0);
//    list.add(9);
//    list.add(5, 1);
//
//    assertEquals(2, list.get(0));
//    assertEquals(5, list.get(1));
//  }
//
//  @Test
//  void set() {
//  }
//
//  @Test
//  void remove() {
//  }
//
//  @Test
//  void removeAll() {
//    LinkedList<Integer> list = new LinkedList<>();
//    list.add(2);
//    list.add(2);
//    list.add(2);
//    list.add(5);
//    list.removeAll(2);
//
//    assertEquals(1, list.size());
//  }
//
//  @Test
//  void removeFrom() {
//  }
//
  @Test()
  void get() {
    LinkedList<Integer> list = new LinkedList<>();

//    assertThrows(IndexOutOfBoundsException.class, new TestAssert(list), "Index out of range");
//    assertThrows(IndexOutOfBoundsException.class, new Executable() {
//      @Override
//      public void execute() throws Throwable {
//        list.get(0);
//      }
//    }, "Index out of range");
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0), "Index out of range");
    list.add(5);
    list.add(2);
    list.add(2);

    assertEquals(new Integer(2), list.get(-1));
    assertEquals(new Integer(5), list.get(-3));
    assertEquals(new Integer(2), list.get(1));
    assertEquals(new Integer(5), list.get(0));
  }
//
//  class TestAssert implements Executable {
//
//    private final LinkedList list;
//
//    TestAssert(LinkedList list) {
//      this.list = list;
//    }
//
//    @Override
//    public void execute() throws Throwable {
//      list.get(0);
//    }
//  }
//
//  @Test
//  void toArray() {
//  }
//
//  @Test
//  void size() {
//  }
//
  @Test
  void indexOf() {
    LinkedList<Integer> list = new LinkedList<>();
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
    LinkedList<Integer> list = new LinkedList<>();
    list.add(2);
    list.add(5);
    list.add(6);
    list.add(5);
    list.add(9);

    assertEquals(3, list.lastIndexOf(5));
    assertEquals(-1, list.lastIndexOf(61));
  }

}