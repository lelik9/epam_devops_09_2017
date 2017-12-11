package com.epam.Philippov.http.server.core;

import com.epam.Philippov.http.server.framework.Request;
import com.epam.Philippov.http.server.framework.response.Response;
import com.epam.Philippov.http.server.framework.middleware.PostMiddleware;
import com.epam.Philippov.http.server.framework.middleware.PreFilter;
import com.epam.Philippov.http.server.framework.middleware.SendResponseMiddleware;
import com.epam.Philippov.http.server.framework.view.View;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;


public class Handler {
    private HashMap<String, View> urlPatterns = new HashMap<>();
    private LinkedList<Class> preMiddleware = new LinkedList<>();
    private LinkedList<Class> postMiddleware = new LinkedList<>();
    private SendResponseMiddleware sendMiddleware = new SendResponseMiddleware();

    public Handler() {

    }

    public void handle(Request request){
        Executor executor = new Executor();
        executor.getResource(request);
    }

    public void registeredURL(HashMap<? extends String, ? extends View> url){
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


            View view = urlPatterns.get(request1.getUrl());
            if (view != null) {
                Response response;

                request1.setViewClass(view);
                request1 = execPreMiddleware(request1);


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
                sendMiddleware.call(response, request1);
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
                    PreFilter instance = (PreFilter) middleware.newInstance();
                    newRequest = instance.call(newRequest);
                }
            } finally {
                return newRequest;
            }
        }

    }
}
