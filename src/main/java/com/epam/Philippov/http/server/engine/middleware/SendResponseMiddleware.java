package com.epam.Philippov.http.server.engine.middleware;

import com.epam.Philippov.http.server.engine.*;
import com.epam.Philippov.http.server.engine.network.Server;
import lombok.SneakyThrows;

import java.io.*;
import java.util.HashMap;
import java.util.Random;

public class SendResponseMiddleware{
//    @Override
    @SneakyThrows
    public void call(Response response, Request request) {
        BufferedReader reader;
        BufferedWriter out = request.getOut();

        try {
            StringWriter headers = new StringWriter();
            HashMap<String, String> responseHeaders = response.getHeaders();

            checkSession(request);
            responseHeaders.putAll(request.getHeaders());
            responseHeaders.put("Cookie", request.getCoockiesAsString());

            out.write("HTTP/1.1" + response.getError() + "OK\n");

            for (String key : responseHeaders.keySet()) {
                headers.write(key);
                headers.write(": ");
                headers.write(response.getHeaders().get(key));
                headers.write("\r\n");
            }
//            System.out.println(headers.toString());
            out.write(headers.toString());
            out.write("\n");
            out.write(response.getResponse());
            out.flush();


        }catch (NullPointerException e){
            out.write("404 File not found.");
        } finally {
            out = null;
        }
    }

    private void checkSession(Request request) {
        HashMap<String, String> coockies = request.getCookies();
        HashMap<String, String> headers = request.getHeaders();
        String sessionID = coockies.getOrDefault("sessionID", "");

        if(sessionID.equals("")) {
            sessionID = String.valueOf(new Random().nextLong());
            Session session = new ServerSession();
            session.setValue("UserName", "Alex");
            Server.setSession(sessionID, session);
            headers.put("Set-Cookie", "sessionID="+sessionID);
        }
    }
}
