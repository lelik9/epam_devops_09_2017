package com.epam.Philippov.http.server.core;

import com.epam.Philippov.http.server.framework.view.View;

import java.util.HashMap;

public class HandlerFactory {
    private HashMap<String, Handler> handlersList = new HashMap<>();

    public HandlerFactory() {
        handlersList.put("/hotel/", generatrHandler());
    }

    public Handler getHandler(String endPoint) {
        return handlersList.get(endPoint);
    }

    private Handler generatrHandler(){
        Handler handler = new Handler();
        HashMap<String, View> urlPatterns = new HashMap<>();
        try {
            urlPatterns.put("/", (View) Class.forName("com.epam.Philippov.http.hotelApp.views.IndexView").newInstance());
            urlPatterns.put("/Гостиница «Волхов» – официальный сайт_files", (View) Class.forName("com.epam.Philippov.http.hotelApp.views.StaticView").newInstance());
            handler.registeredURL(urlPatterns);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            urlPatterns.put("/", null);

            e.printStackTrace();
        }
        return handler;
    }
}
