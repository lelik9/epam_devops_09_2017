package com.my.http.server.core;

import com.epam.Philippov.http.framework.errors.Http404;
import com.epam.Philippov.http.framework.view.View;
import java.util.HashMap;
import org.apache.logging.log4j.core.util.ArrayUtils;

public class HandlerFactory {
    private HashMap<String, Handler> handlersList = new HashMap<>();

    public HandlerFactory() {
        handlersList.put("/hotel/", generatrHandler());
    }

    public Handler getHandler(String url) {
        String endPoint = url;
        String[] pathArr;

        while (!handlersList.containsKey(endPoint)) {
            pathArr = endPoint.split("/");
//            System.out.println(endPoint);
            if(pathArr.length == 1) {
                throw new Http404();
            }
            pathArr = ArrayUtils.remove(pathArr, pathArr.length - 1);

            endPoint = String.join("/", pathArr) + "/";
        }
        return handlersList.get(endPoint);
    }

    private Handler generatrHandler(){
        Handler handler = new Handler("hotelSite");
        HashMap<String, View> urlPatterns = new HashMap<>();
        try {
            urlPatterns.put("/hotel/", (View) Class.forName("com.epam.Philippov.http.hotelApp.views.IndexView").newInstance());
            urlPatterns.put("/hotel/Гостиница «Волхов» – официальный сайт_files", (View) Class.forName("com.epam.Philippov.http.hotelApp.views.StaticView").newInstance());
            handler.registeredURL(urlPatterns);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            urlPatterns.put("/", null);

            e.printStackTrace();
        }
        return handler;
    }
}
