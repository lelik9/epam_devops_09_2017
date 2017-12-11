package com.epam.Philippov.http.server.core;

import java.util.HashMap;

public class ServerSession implements Session {
    private HashMap<String, Object> sessionValues = new HashMap<>();

    @Override
    public Object getValue(String name) {
        return sessionValues.getOrDefault(name, null);
    }

    @Override
    public void setValue(String name, Object value) {
        sessionValues.put(name, value);
    }
}
