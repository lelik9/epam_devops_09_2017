package com.my.http.framework.session;

public interface Session {

    Object getValue(String name);
    void setValue(String name, Object value);
}
