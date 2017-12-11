package com.epam.Philippov.http.server.engine.network;

import com.epam.Philippov.http.server.engine.core.Handler;
import com.epam.Philippov.http.server.engine.Request;
import com.epam.Philippov.http.server.engine.Session;
import com.epam.Philippov.http.server.engine.core.HandlerFactory;
import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    private ServerSocket serverSocket = new ServerSocket(8080);
    private final Handler handler;
    private final ExecutorService workers;
    private static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();


    public Server(Handler handler) throws IOException {
        this.handler = handler;
        workers = Executors.newFixedThreadPool(10);
    }

    public void listen() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            workers.execute(new ServerWorker(clientSocket));
        }
    }

    public static Session getSession(String sessionID){
        return sessions.getOrDefault(sessionID, null);
    }

    public static void setSession(String sessionID, Session session){
        sessions.put(sessionID, session);
    }

    private class ServerWorker implements Runnable {

        private final Socket clientSocket;
        private HashMap<String, String> hhtpData;


        private ServerWorker(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        @SneakyThrows
        public void run() {
            try (
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "utf-8"));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
            ) {
                hhtpData = new HashMap<>();
                Parser parser = new Parser();

                String userInput = in.readLine();
                while (in.ready()) {

                    if (userInput.equals("")) {
                        Integer bufSize = Integer.valueOf(hhtpData.getOrDefault("Content-Length", "0"));
                        char[] buf = new char[bufSize];
                        in.read(buf);
                        hhtpData.put("data", new String(buf));
                    } else {
                        parser.parser(hhtpData, userInput);
                    }
                    userInput = in.readLine();

                }
                Request request = parser.generateRequest(out, hhtpData);
                HandlerFactory factory = new HandlerFactory();
                Handler handler = factory.getHandler("/hotel/");
                handler.handle(request);
            } finally {
                clientSocket.close();
            }
        }
    }

    private class Parser {
        public void parser(HashMap<? super String, ? super String> hhtpData, String request) throws UnsupportedEncodingException {
            Scanner scan = new Scanner(request);

            if(request.contains(": ")) {
                scan.useDelimiter(": ");
                hhtpData.put(scan.next(), scan.next());
            } else {
                hhtpData.put("method", scan.next());
                String queryUrl = java.net.URLDecoder.decode(scan.next(), "UTF-8");;

                String[] query = parseQuery(queryUrl);

                hhtpData.put("url", query[0]);
                hhtpData.put("query", query[1]);
                hhtpData.put("httpVersion", scan.next());

            }
        }

        public Request generateRequest(BufferedWriter out, HashMap<String, String> hhtpData) {
            String url = hhtpData.remove("url");
            return new Request(
                    url,
                    hhtpData.remove("method"),
                    hhtpData.remove("query"),
                    hhtpData.remove("data"),
                    out,
                    hhtpData
            );
        }

        private String[] parseQuery(String request){
            String query;
            String url;

            if(request.contains("Гостиница «Волхов» – официальный сайт_files")){
                Pattern pattern = Pattern.compile("(\\/.*)\\/([\\/\\w+]*.*)");
                Matcher matcher = pattern.matcher(request);
                matcher.find();

                url = matcher.group(1);
                query = matcher.group(2);

            }else {
                Pattern pattern = Pattern.compile("([\\/\\w+.*]*)\\??([\\w+\\.=&]*)");
                Matcher matcher = pattern.matcher(request);
                matcher.find();

                url = matcher.group(1);
                query = matcher.group(2);
            }

            return new String[]{url, query};
        }
    }
}