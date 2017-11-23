package com.epam.Philippov.http.server;

import java.io.InputStream;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) {
        Handler handler = new Handler();

        try {
//            handler.getResource("get /index");
            InputStream input = System.in;

            handler.getResource("get http://192.168.1.1/static/test.txt");
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
