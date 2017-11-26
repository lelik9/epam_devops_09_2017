package com.epam.Philippov.http.server.engine;

import com.epam.Philippov.http.server.engine.middleware.PostMiddleware;
import com.epam.Philippov.http.server.engine.middleware.PreMiddleware;
import com.epam.Philippov.http.server.engine.middleware.SendResponseMiddleware;
import com.epam.Philippov.http.server.engine.view.View;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;


public class Handler {
    private HashMap<String, Class> urlPatterns = new HashMap<>();
    private LinkedList<Class> preMiddleware = new LinkedList<>();
    private LinkedList<Class> postMiddleware = new LinkedList<>();
    private SendResponseMiddleware sendMiddleware = new SendResponseMiddleware();

    public Handler() {

    }

    public void handle(Request request){
        Executor executor = new Executor();
        executor.getResource(request);
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

    private class Executor {
        @SneakyThrows
        private void getResource(Request request) {
            Request request1 = request;


            Class c = urlPatterns.get(request1.getUrl());
            if (c != null) {
                request1.setViewClass(c);
                request1 = execPreMiddleware(request1);
                Response response;
                View view = (View) c.newInstance();

                switch (request1.getMethod()) {
                    case "GET":
                        response = view.get(request1);
                        break;
                    case "POST":
                        response = view.post(request1);
                        break;
                    default:
                        response = new Response(404, "", "Resource not found.");
                }
                execPostMiddleware(response);
                sendMiddleware.call(response, request1.getOut());
            } else {
//                throw new Exception("404 Resource not found.");
            }

        }

        @SneakyThrows
        private void execPostMiddleware(Response response) {
            Response newResponse = response;

            for (Class middleware : postMiddleware) {
                PostMiddleware instance = (PostMiddleware) middleware.newInstance();
                newResponse = instance.call(newResponse);
            }
        }

        @SneakyThrows
        private Request execPreMiddleware(Request request) {
            Request newRequest = request;
            try {
                for (Class middleware : preMiddleware) {
                    PreMiddleware instance = (PreMiddleware) middleware.newInstance();
                    newRequest = instance.call(newRequest);
                }
            } finally {
                return newRequest;
            }
        }

    }
}
