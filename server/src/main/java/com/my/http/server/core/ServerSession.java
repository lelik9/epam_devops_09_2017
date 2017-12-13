package com.my.http.server.core;

import java.io.Serializable;
import java.util.HashMap;

public class ServerSession implements Session, Serializable {
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
