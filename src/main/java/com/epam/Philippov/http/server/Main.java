package com.epam.Philippov.http.server;

import com.epam.Philippov.http.server.engine.core.Handler;
import com.epam.Philippov.http.server.engine.framework.view.View;
import com.epam.Philippov.http.server.engine.middleware.AuthenticationFilter;
import com.epam.Philippov.http.server.engine.network.Server;
import com.epam.Philippov.http.server.views.IndexView;
import com.epam.Philippov.http.server.views.StaticView;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    /**
     * HTTP server entry point.
     * Request examples:
     * get;http://192.168.1.1/static/Гостиница «Волхов» – официальный сайт_files/04.jpg
     * get;http://192.168.1.1/index
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Handler handler = new Handler();
        HashMap<String, View> urlPatterns = new HashMap<>();

        urlPatterns.put("/", new IndexView());
        urlPatterns.put("/Гостиница «Волхов» – официальный сайт_files", new StaticView());

        handler.registeredURL(urlPatterns);
        handler.registerPreMiddleware(AuthenticationFilter.class);

        Server server = new Server(handler);
        server.listen();

    }
}
