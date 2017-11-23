package com.epam.Philippov.selfCollection;

public interface ArrayInterface<E> {

  void add(E value);

  void add(E value, int index);

  void set(E value, int index);

  boolean remove(E value);

  boolean removeAll(E value);

  E removeFrom(int index);

  E get(int index);

  int size();

  int indexOf(E value);

}
