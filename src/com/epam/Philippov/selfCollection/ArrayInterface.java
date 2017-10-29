package com.epam.Philippov.selfCollection;

public interface ArrayInterface {
    void add(int value); // TODO добавление элемента в конец списка

    void add(int value, int index); // TODO добавление элемента в указанную позицию

    void set(int value, int index); // TODO изменяет значение указанного элемента


    boolean remove(int value);// TODO удаление первого вхождения указанного элемента (если он присутствует в списке)
        // TODO возвращает true - если элемент был удален, в противном случае false


    boolean removeAll(int value);// TODO удаление всех вхождений указанного элемента (если он присутствует в списке)
        // TODO возвращает true - если элемент был удален, в противном случае false


    int removeFrom(int index);// TODO удаление элемента по указанному индексу
        // TODO возвращает удаленное значение


    int get(int index); // TODO значение указанного элемента


    int[] toArray(); // TODO возвращаем копию внутреннего массива


    int size(); // TODO текущий размер списка


    int indexOf(int value); // TODO поиск элемента (с головы списка к хвосту)

}
