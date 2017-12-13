package com.my.http.server;

import com.epam.Philippov.http.server.core.Server;
import java.io.IOException;

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
