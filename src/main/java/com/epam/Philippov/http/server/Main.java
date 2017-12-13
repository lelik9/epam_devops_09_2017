package com.epam.Philippov.http.server;

import com.epam.Philippov.http.server.core.Handler;
import com.epam.Philippov.http.server.framework.view.View;
import com.epam.Philippov.http.server.framework.middleware.AuthenticationFilter;
import com.epam.Philippov.http.server.core.Server;
import com.epam.Philippov.http.hotelApp.views.IndexView;
import com.epam.Philippov.http.hotelApp.views.StaticView;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    /**
     * HTTP server entry point.
     * Request examples:
     * get;http://192.168.1.1/Гостиница «Волхов» – официальный сайт_files/04.jpg
     * get;http://192.168.1.1/
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.listen();
    }
}
