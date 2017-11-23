package com.epam.Philippov.http.server;

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
        Handler handler = new Handler();

        try {
            try (InputStream input = System.in;
                 BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"))){
                while (true) {
                    handler.getResource(reader.readLine());
                }
            }
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
