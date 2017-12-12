package com.epam.Philippov.http.server.core;

import com.epam.Philippov.http.server.framework.Request;
import com.epam.Philippov.http.server.framework.response.Response;
import com.epam.Philippov.http.server.framework.middleware.PostMiddleware;
import com.epam.Philippov.http.server.framework.middleware.PreFilter;
import com.epam.Philippov.http.server.framework.view.View;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;


public class Handler {
    private HashMap<String, View> urlPatterns = new HashMap<>();
    private LinkedList<Class> preMiddleware = new LinkedList<>();
    private LinkedList<Class> postMiddleware = new LinkedList<>();
    private SendResponse sendMiddleware = new SendResponse();
    private String appName;
    private SessionManager sessionManager;

    public Handler(String appName) {
        this.appName = appName;
        sessionManager = new SessionManager(appName);
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
            checkSession(request);

            View view = urlPatterns.get(request.getUrl());
            if (view != null) {
                Response response;

                request.setViewClass(view);
                request = execPreMiddleware(request);


                switch (request.getMethod()) {
                    case "GET":
                        response = view.get(request);
                        break;
                    case "POST":
                        response = view.post(request);
                        break;
                    default:
                        response = new Response(404, "", "Resource not found.");
                }
                execPostMiddleware(response);
                sendMiddleware.call(response, request);
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

        private void checkSession(Request request) {

            String sessionID = request.getCookies().getOrDefault("sessionID", "");

            if(sessionID.equals("")) {
                sessionID = String.valueOf(new Random().nextLong());
                Session session = new ServerSession();
                session.setValue("UserName", "Alex");
                sessionManager.setSession(sessionID, session);
                request.getHeaders().put("Set-Cookie", "sessionID="+sessionID);
            }
        }

    }
}
