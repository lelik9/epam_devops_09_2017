package com.epam.Philippov.selfCollection;

public class IntLinkedList implements ArrayInterface {

  private ListNode firstNode;
  private ListNode lastNode;
  private int size = 0;

  public IntLinkedList() {
  }

  public IntLinkedList(int... args) {
    for (int arg : args) {
      add(arg);
    }
  }

  @Override
  public void add(int value) {
    ListNode node = new ListNode(value);

    if (lastNode == null) {
      firstNode = node;
      lastNode = firstNode;
    } else {

      if (firstNode.getNextNode() == null) {
        firstNode.setNextNode(node);
      }

      lastNode.setNextNode(node);
      lastNode = node;
    }

    size += 1;
  }

  @Override
  public void add(int value, int index) {
    if(index == 0){
      add(value);
    }else {
      ListNode newNode = new ListNode(value);
      ListNode previousNode = getNodeByIndex(index - 1);
      ListNode currentNode = previousNode.getNextNode();

      previousNode.setNextNode(newNode);
      newNode.setNextNode(currentNode);

      size += 1;
    }
  }

  @Override
  public void set(int value, int index) {
    checkIndex(index);
    ListNode currentNode = getNodeByIndex(index);
    currentNode.setValue(value);
  }

  @Override
  public boolean remove(int value) {
    int index = indexOf(value);

    if(index > 0) {
      fastRemove(index);
      return true;
    }
    return false;
  }

  @Override
  public boolean removeAll(int value) {
    boolean result = false;
    ListNode node = firstNode;

    for (int i = 0; i < size;) {
      if(node.getValue() == value) {
        fastRemove(i);
        result = true;
      }else {
        i += 1;
      }
      node = node.getNextNode();
    }
    return result;
  }

  @Override
  public int removeFrom(int index) {
    return fastRemove(index);
  }

  @Override
  public int get(int index) {
    checkIndex(index);

    ListNode node = getNodeByIndex(index);

    return node.getValue();
  }

  @Override
  public int[] toArray() {
    return new int[0];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public int indexOf(int value) {
    ListNode node = firstNode;

    for (int i = 0; i < size; i++) {
      if(node != null) {
        int currentValue = node.getValue();

        if (currentValue == value) {
          return i;
        }

        node = node.getNextNode();
      }
    }

    return -1;
  }

//  public int lastIndexOf(int value) {
//    return 0;
//  }

  private ListNode getNodeByIndex(int index) {
    checkIndex(index);

    ListNode node = firstNode;

    if(index != 0) {
      for (int i = 1; i <= index; i++) {
        node = node.getNextNode();
      }
    }

    return node;
  }

  private int fastRemove(int index) {
    System.out.println(index);
    ListNode previousNode = getNodeByIndex(index - 1);
    ListNode nextNode = getNodeByIndex(index + 1);
    ListNode currentNode = getNodeByIndex(index);

    previousNode.setNextNode(nextNode);
    size -= 1;
    return currentNode.getValue();
  }

  private void checkIndex(int index) {
    if(index >= size || size == 0) {
      System.err.println("Index: "+index+" out of range");
      throw new IndexOutOfBoundsException("Index out of range");
    }
  }

  class ListNode {

    private int Value;
    private ListNode nextNode;

    ListNode(int value) {
      Value = value;
    }

    void setNextNode(ListNode nextNode) {
      this.nextNode = nextNode;
    }

    int getValue() {
      return Value;
    }

    ListNode getNextNode() {
      return nextNode;
    }

    void setValue(int value) {
      Value = value;
    }
  }
}
