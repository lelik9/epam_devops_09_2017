package com.my.http.server.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.my.http.framework.Request;
import lombok.SneakyThrows;

public class Server {
    private ServerSocket serverSocket;
    private HandlerFactory factory;
    private final ExecutorService workers;


    public Server() throws IOException {
        factory = new HandlerFactory();
        workers = Executors.newFixedThreadPool(10);
        serverSocket = new ServerSocket((Integer) ConfigParser.getInstance().getServerConfig().getServer().getOrDefault("port", 80));
    }



    public void listen() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            workers.execute(new ServerWorker(clientSocket));
        }
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
                Handler handler = factory.getHandler(request.getUrl());
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