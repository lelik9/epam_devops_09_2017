package com.epam.Philippov.http.server;

public class Main {
    public static void main(String[] args) {
        Handler handler = new Handler();
        handler.getResource("get /index");
    }
}
