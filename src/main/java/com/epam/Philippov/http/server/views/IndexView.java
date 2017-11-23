package com.epam.Philippov.http.server.views;

public class IndexView extends View {
    @Override
    public void get(String query) {
        returnResource("Гостиница «Волхов» – официальный сайт.html");
    }
}
