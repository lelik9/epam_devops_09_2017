package com.epam.Philippov.http.server;

import com.epam.Philippov.http.server.views.IndexView;
import com.epam.Philippov.http.server.views.View;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Scanner;

public class Handler {
    private HashMap<String, Class> urlPatterns = new HashMap<>();

    {
        urlPatterns.put("/index", IndexView.class);
        urlPatterns.put("/static", IndexView.class);
    }

    @SneakyThrows
    public void getResource(String request) {
        Scanner scan = new Scanner(request);
        String method = scan.next();
        String pattern = scan.next();

        Class c = urlPatterns.get(pattern);
        View view = (View) c.newInstance();

        switch (method) {
            case "get":
                view.get(pattern);
                break;
            case "post":
                view.post();
                break;
            default:
                System.out.println("404 Resource not found.");
        }

    }

}
