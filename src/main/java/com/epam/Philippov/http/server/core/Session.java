package com.epam.Philippov.http.server.core;

public interface Session {

    Object getValue(String name);
    void setValue(String name, Object value);
}
