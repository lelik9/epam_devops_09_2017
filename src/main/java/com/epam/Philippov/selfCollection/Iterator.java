package com.epam.Philippov.selfCollection;

public interface Iterator<T> extends java.util.Iterator<T> {

    boolean hasNext();
    T next();

}
