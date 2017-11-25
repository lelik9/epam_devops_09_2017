package com.epam.Philippov.http.server;

import com.epam.Philippov.http.server.engine.Handler;
import com.epam.Philippov.http.server.engine.middleware.AuthenticationMiddleware;
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
        HashMap<String, Class> urlPatterns = new HashMap<>();

        urlPatterns.put("/index", IndexView.class);
        urlPatterns.put("/static", StaticView.class);

        handler.registeredURL(urlPatterns);
        handler.registerPreMiddleware(AuthenticationMiddleware.class);

        Server server = new Server(handler);
        server.listen();

    }
}
