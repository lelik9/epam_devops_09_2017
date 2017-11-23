package com.epam.Philippov.http.server.views;

import lombok.SneakyThrows;

public class StaticView extends View{

    @Override
    @SneakyThrows
    public void get(String query) {
        returnResource(query);
    }

}
