package com.epam.Philippov.selfCollection;

public interface ArrayInterface {
    boolean contains(Object o);
    int indexOf(Object o);
    Object getSet();
    Object[] toArray();
    void setArray(Object[] arr);
    int size();
    void trimToSize(int value);
}
