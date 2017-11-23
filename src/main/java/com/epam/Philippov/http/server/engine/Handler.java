package com.epam.Philippov.http.server.engine;

import com.epam.Philippov.http.server.engine.middleware.PostMiddleware;
import com.epam.Philippov.http.server.engine.middleware.PreMiddleware;
import com.epam.Philippov.http.server.engine.middleware.SendResponseMiddleware;
import com.epam.Philippov.http.server.engine.view.View;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Handler {
    private HashMap<String, Class> urlPatterns = new HashMap<>();
    private LinkedList<Class> preMiddleware = new LinkedList<>();
    private LinkedList<Class> postMiddleware = new LinkedList<>();

    public Handler() {
        registerPostMiddleware(SendResponseMiddleware.class);
    }

    public void listner(String request){
        getResource(request);
    }

    public void registerURL(HashMap<? extends String, ? extends Class> url){
        urlPatterns.putAll(url);
    }

    public void registerPreMiddleware(Class... middleware){
        preMiddleware.addAll(Arrays.asList(middleware));
    }
    public void registerPostMiddleware(Class... middleware){
        postMiddleware.addAll(Arrays.asList(middleware));
    }

    @SneakyThrows
    private void getResource(String request) {
        Request request1 = parseRequest(request);

        request1 = execPreMiddleware(request1);

        Class c = request1.getViewClass();
        if(c != null) {
            ResponseInterface response;
            View view = (View) c.newInstance();

            switch (request1.getMethod()) {
                case "get":
                    response = view.get(request1);
                    break;
                case "post":
                    response = view.post(request1);
                    break;
                default:
                    response = new Response(404, "", "Resource not found.");
            }
            execPostMiddleware(response);
        }else {
            throw new Exception("404 Resource not found.");
        }

    }

    @SneakyThrows
    private void execPostMiddleware(ResponseInterface response) {
        ResponseInterface newResponse = response;

        for (Class middleware : postMiddleware) {
            PostMiddleware instance = (PostMiddleware) middleware.newInstance();
            newResponse = instance.call(newResponse);
        }
    }

    @SneakyThrows
    private Request execPreMiddleware(Request request){
        Request newRequest = request;
        try {
            for (Class middleware : preMiddleware) {
                PreMiddleware instance = (PreMiddleware) middleware.newInstance();
                newRequest = instance.call(newRequest);
            }
        }finally {
            return newRequest;
        }
    }

    private Request parseRequest(String request){
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

        return new Request(url,method,query, c, new HashMap<>());
    }

}
