package com.epam.Philippov.http.server.views;

interface ViewInterface {
    void get(String query);
    void post();
    void returnResource(String resourceName);
}
