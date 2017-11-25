package com.epam.Philippov.http.server.engine;

import com.epam.Philippov.http.server.engine.middleware.PostMiddleware;
import com.epam.Philippov.http.server.engine.middleware.PreMiddleware;
import com.epam.Philippov.http.server.engine.middleware.SendResponseMiddleware;
import com.epam.Philippov.http.server.engine.view.View;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.BufferedWriter;
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

    public void handle(Request request){
        getResource(request);
    }

    public void registeredURL(HashMap<? extends String, ? extends Class> url){
        urlPatterns.putAll(url);
    }

    public void registerPreMiddleware(Class... middleware){
        preMiddleware.addAll(Arrays.asList(middleware));
    }
    public void registerPostMiddleware(Class... middleware){
        postMiddleware.addAll(Arrays.asList(middleware));
    }

    @SneakyThrows
    private void getResource(Request request) {
        Request request1 = request;

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

    public class Parser {
        public void parser(HashMap<? super String, ? super String> hhtpData, String request) {
            Scanner scan = new Scanner(request);

            if(request.contains(": ")) {
                scan.useDelimiter(": ");
                hhtpData.put(scan.next(), scan.next());
            } else {
                hhtpData.put("method", scan.next());
                String[]query = parseQuery(scan.next());

                hhtpData.put("url", query[0]);
                hhtpData.put("query", query[1]);
                hhtpData.put("httpVersion", scan.next());
            }
        }

        public Request generateRequest(BufferedWriter out, HashMap<String, String> hhtpData) {
            String url = hhtpData.remove("url");
            return new Request(
                    url,
                    hhtpData.remove("method"),
                    hhtpData.remove("query"),
                    hhtpData.remove("data"),
                    urlPatterns.get(url),
                    out,
                    hhtpData
            );
        }

        private String[] parseQuery(String request){
            String query;
            String url;

            if(request.contains("static")){
                Pattern pattern = Pattern.compile("https?:\\/\\/[\\d+.]*(\\/\\w+)\\/([\\/\\w+]*.*)");
                Matcher matcher = pattern.matcher(request);
                matcher.find();

                url = matcher.group(1);
                query = matcher.group(2);

            }else {
//                Pattern pattern = Pattern.compile("https?:\\/\\/[\\d+\\.]*([\\/\\w+.*]*)\\??([\\w+\\.=&]*)");
                Pattern pattern = Pattern.compile("([\\/\\w+.*]*)\\??([\\w+\\.=&]*)");
                Matcher matcher = pattern.matcher(request);
                matcher.find();

                url = matcher.group(1);
                query = matcher.group(2);
            }

            return new String[]{url, query};
        }
    }

//    private Request parseRequest(String request){
//        Scanner scan = new Scanner(request);
//        scan.useDelimiter(";");
//        String method = scan.next();
//        String queryString = scan.next();
//        String query;
//        String url;
//
//        if(queryString.contains("static")){
//            Pattern pattern = Pattern.compile("https?:\\/\\/[\\d+.]*(\\/\\w+)\\/([\\/\\w+]*.*)");
//            Matcher matcher = pattern.matcher(queryString);
//            matcher.find();
//
//            url = matcher.group(1);
//            query = matcher.group(2);
//
//        }else {
//            Pattern pattern = Pattern.compile("https?:\\/\\/[\\d+\\.]*([\\/\\w+.*]*)\\??([\\w+\\.=&]*)");
//            Matcher matcher = pattern.matcher(queryString);
//            matcher.find();
//
//            url = matcher.group(1);
//            query = matcher.group(2);
//        }
//
//        Class c = urlPatterns.get(url);
//
////        return new Request(url,method,query, c, new HashMap<>());
//        return null;
//    }

}
