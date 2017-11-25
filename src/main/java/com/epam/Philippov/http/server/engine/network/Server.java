package com.epam.Philippov.http.server.engine.network;

import com.epam.Philippov.http.server.engine.Handler;
import com.epam.Philippov.http.server.engine.Handler.Parser;
import com.epam.Philippov.http.server.engine.Request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private ServerSocket serverSocket = new ServerSocket(8080);
    private HashMap<String, String> hhtpData;
    private final Handler handler;

    public Server(Handler handler) throws IOException {
        this.handler = handler;
    }

    public void listen() throws IOException {
        try (Socket clientSocket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            hhtpData = new HashMap<>();
            Handler.Parser parser = handler.new Parser();

            String userInput;
            while (in.ready()) {
                userInput = in.readLine();

                if (userInput.equals("")) {
                    Integer bufSize = Integer.valueOf(hhtpData.getOrDefault("Content-Length", "0"));
                    char[] buf = new char[bufSize];
                    in.read(buf);
                    hhtpData.put("data", new String(buf));
                }else {
                    parser.parser(hhtpData, userInput);
                }
            }
            Request request = parser.generateRequest(out, hhtpData);
            handler.handle(request);
        }

    }
}