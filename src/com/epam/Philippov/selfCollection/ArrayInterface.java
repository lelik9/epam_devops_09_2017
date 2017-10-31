package com.epam.Philippov.selfCollection;

public interface ArrayInterface<E> {

  void add(E value); // TODO добавление элемента в конец списка

  void add(E value, int index); // TODO добавление элемента в указанную позицию

  void set(E value, int index); // TODO изменяет значение указанного элемента


  boolean remove(E value);// TODO удаление первого вхождения указанного элемента (если он присутствует в списке)
  // TODO возвращает true - если элемент был удален, в противном случае false


  boolean removeAll(E value);// TODO удаление всех вхождений указанного элемента (если он присутствует в списке)
  // TODO возвращает true - если элемент был удален, в противном случае false


  E removeFrom(int index);// TODO удаление элемента по указанному индексу
  // TODO возвращает удаленное значение


  E get(int index); // TODO значение указанного элемента

  int size(); // TODO текущий размер списка


  int indexOf(E value); // TODO поиск элемента (с головы списка к хвосту)

  interface ListNode<E> {}

}
