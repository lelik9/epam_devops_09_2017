package com.epam.Philippov.selfCollection;

public class LinkedList<E> implements ArrayInterface<E> {

  private ListNode firstNode;
  private ListNode lastNode;
  private ListNode outOfBoundNode = new ListNode(0);
  private int size = 0;

  public LinkedList() {
  }

  public LinkedList(E... args) {
    for (E arg : args) {
      add(arg);
    }
  }

  @Override
  public void add(E value) {
    ListNode node = new ListNode(value);

    if (lastNode == null) {
      firstNode = node;
      lastNode = firstNode;
      lastNode.setNextNode(outOfBoundNode);
    } else {
      if (firstNode.getNextNode() == null) {
        firstNode.setNextNode(node);
        node.setPrevNode(firstNode);
      }

      lastNode.setNextNode(node);
      node.setPrevNode(lastNode);
      lastNode = node;
      lastNode.setNextNode(outOfBoundNode);
    }

    size += 1;
  }

  @Override
  public void add(E value, int index) {
    if (index == 0) {
      add(value);
    } else {
      ListNode newNode = new ListNode(value);
      ListNode previousNode = getNodeByIndex(index - 1);
      ListNode currentNode = previousNode.getNextNode();

      previousNode.setNextNode(newNode);
      newNode.setNextNode(currentNode);

      size += 1;
    }
  }

  @Override
  public void set(E value, int index) {
    checkIndex(index);
    ListNode currentNode = getNodeByIndex(index);
    currentNode.setValue(value);
  }

  @Override
  public boolean remove(E value) {
    int index = indexOf(value);

    if (index > 0) {
      fastRemove(index);
      return true;
    }
    return false;
  }

  @Override
  public boolean removeAll(E value) {
    boolean result = false;
    ListNode node = firstNode;

    for (int i = 0; i < size; ) {
      if (node.getValue() == value) {
        fastRemove(i);
        result = true;
      } else {
        i += 1;
      }
      node = node.getNextNode();
    }
    return result;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E removeFrom(int index) {
    return (E) fastRemove(index);
  }

  @Override
  @SuppressWarnings("unchecked")
  public E get(int index) {
    checkIndex(index);

    ListNode node = getNodeByIndex(index);

    return (E) node.getValue();
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public int indexOf(Object value) {
    ListNode node = firstNode;
    int i = 0;

    while (node != outOfBoundNode) {
      Object currentValue = node.getValue();

      if (currentValue == value) {
        return i;
      }

      node = node.getNextNode();
      i ++;
    }
    return -1;
  }

  public int lastIndexOf(Object value) {
    ListNode node = lastNode;
    int i = size - 1;

    while (node != firstNode) {
      Object currentValue = node.getValue();

      if (currentValue == value) {
        return i;
      }

      node = node.getPrevNode();
      i --;
    }
    return -1;
  }

  private ListNode getNodeByIndex(int index) {
    checkIndex(index);

    ListNode node = firstNode;

    if (index > 0) {
      for (int i = 1; i <= index; i++) {
        node = node.getNextNode();
      }
    } else if (index < -1) {
      node = lastNode;
      for (int i = 1; i < Math.abs(index); i++) {
        node = node.getPrevNode();
      }
    } else if (index == -1) {
      return lastNode;
    }

    return node;
  }

  //FIXME:
  private Object fastRemove(int index) {
    ListNode previousNode = getNodeByIndex(index - 1);
    ListNode nextNode = getNodeByIndex(index + 1);
    ListNode currentNode = getNodeByIndex(index);

    previousNode.setNextNode(nextNode);
    size -= 1;
    return currentNode.getValue();
  }

  private void checkIndex(int index) {
    if (index >= size || size == 0) {
      System.err.println("Index: " + index + " out of range");
      throw new IndexOutOfBoundsException("Index out of range");
    }
  }

  class ListNode {

    private Object value;
    private ListNode nextNode;
    private ListNode prevNode;


    public void setPrevNode(ListNode prevNode) {
      this.prevNode = prevNode;
    }

    public ListNode getPrevNode() {

      return prevNode;
    }

    ListNode(Object value) {
      this.value = value;
    }

    void setNextNode(ListNode nextNode) {
      this.nextNode = nextNode;
    }

    public Object getValue() {
      return value;
    }

    ListNode getNextNode() {
      return nextNode;
    }

    void setValue(Object value) {
      this.value = value;
    }
  }
}
