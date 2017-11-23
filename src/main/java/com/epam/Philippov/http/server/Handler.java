package com.epam.Philippov.http.server;

import com.epam.Philippov.http.server.views.View;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Handler {
    protected HashMap<String, Class> urlPatterns = new HashMap<>();

    public void listner(String request){
        getResource(request);
    }

    @SneakyThrows
    private void getResource(String request) {
        System.out.println(request);
        Scanner scan = new Scanner(request);
        scan.useDelimiter(";");
        String method = scan.next();
        String queryString = scan.next();
        String query;
        String url;

        if(queryString.contains("static")){
            Pattern pattern = Pattern.compile("https?:\\/\\/[\\d+.]*(\\/\\w+)\\/([\\/\\w+]*.*)");
            Matcher matcher = pattern.matcher(queryString);
            matcher.find();

            url = matcher.group(1);
            query = matcher.group(2);

        }else {
            Pattern pattern = Pattern.compile("https?:\\/\\/[\\d+\\.]*([\\/\\w+.*]*)\\??([\\w+\\.=&]*)");
            Matcher matcher = pattern.matcher(queryString);
            matcher.find();

            url = matcher.group(1);
            query = matcher.group(2);
        }

        Class c = urlPatterns.get(url);
        if(c != null) {
            View view = (View) c.newInstance();

            switch (method) {
                case "get":
                    view.get(query);
                    break;
                case "post":
                    view.post();
                    break;
                default:
                    throw new Exception("404 Resource not found.");
            }
        }else {
            throw new Exception("404 Resource not found.");
        }

    }

}
