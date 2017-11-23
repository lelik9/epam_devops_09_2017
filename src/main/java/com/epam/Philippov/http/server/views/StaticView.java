package com.epam.Philippov.http.server.views;

public class StaticView extends View{

    @Override
    public void get(String query) {
        String pattern = "([\\/\\w*]*)\\/(\\w+\\.\\w+)";
    }

    @Override
    public void returnResource(String resourceName) {
        super.returnResource(resourceName);
    }
}
