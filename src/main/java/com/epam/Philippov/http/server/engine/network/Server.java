package com.epam.Philippov.http.server.engine.network;

import com.epam.Philippov.http.server.engine.Handler;
import com.epam.Philippov.http.server.engine.Request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    private ServerSocket serverSocket = new ServerSocket(8080);
    private HashMap<String, String> hhtpData;
    private final Handler handler;

    public Server(Handler handler) throws IOException {
        this.handler = handler;
    }

    public void listen() throws IOException {
        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "utf-8"));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

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
                handler.handle(request);
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
                Pattern pattern = Pattern.compile("(\\w+)\\ (\\/[\\/\\w+]*.*)\\ (\\w+\\/[\\d\\.]+)");
                Matcher matcher = pattern.matcher(request);
                matcher.find();

                hhtpData.put("method", matcher.group(1));
                String queryUrl = java.net.URLDecoder.decode(matcher.group(2), "UTF-8");;
                String[] query = parseQuery(queryUrl);

                hhtpData.put("url", query[0]);
                hhtpData.put("query", query[1]);
                hhtpData.put("httpVersion", matcher.group(3));
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