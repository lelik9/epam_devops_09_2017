package com.epam.Philippov.http.server.engine;

public interface Session {

    Object getValue(String name);
    void setValue(String name, Object value);
}
