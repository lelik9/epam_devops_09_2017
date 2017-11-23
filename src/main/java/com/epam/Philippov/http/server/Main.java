package com.epam.Philippov.http.server;

import com.epam.Philippov.http.server.views.URLHandler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    /**
     * HTTP server entry point.
     * Request examples:
     * get;http://192.168.1.1/static/Гостиница «Волхов» – официальный сайт_files/04.jpg
     * get;http://192.168.1.1/index
     * @param args
     */
    public static void main(String[] args) {
        Handler handler = new URLHandler();

        try {
            try (InputStream input = System.in;
                 BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"))){
                while (true) {
                    handler.listner(reader.readLine());
                }
            }
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
