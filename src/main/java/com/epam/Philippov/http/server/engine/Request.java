package com.epam.Philippov.http.server.engine;

import com.epam.Philippov.http.server.engine.network.Server;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Random;

@Getter
public class Request {
    private final String url;
    private final String method;
    private final String query;
    private final String data;
    private final BufferedWriter out;
    private final HashMap<String, String> headers;
    private final HashMap<String, String> cookies;
    @Setter
    private Class viewClass;

    public Request(String url, String method, String query, String data, BufferedWriter out, HashMap<? extends String, ? extends String> headers) {
        this.url = url;
        this.method = method;
        this.query = query;
        this.data = data;
        this.out = out;
        this.headers = new HashMap<>(headers);
        cookies = new HashMap<>();
        setCookies();
        checkSession();
    }

    public String getCoockiesAsString() {
        StringWriter coockie = new StringWriter();
        for (String key : cookies.keySet()) {
            coockie.write(key);
            coockie.write("=");
            coockie.write(cookies.get(key));
            coockie.write("; ");
        }

        return coockie.toString();
    }

    public Session getSession() {
        String sessionID = cookies.getOrDefault("sessionID", "");
        return Server.getSession(sessionID);
    }

    private void checkSession() {

        String sessionID = cookies.getOrDefault("sessionID", "");

        if(sessionID.equals("")) {
            sessionID = String.valueOf(new Random().nextLong());
            Session session = new ServerSession();
            session.setValue("UserName", "Alex");
            Server.setSession(sessionID, session);
            headers.put("Set-Cookie", "sessionID="+sessionID);
        }
    }

    private void setCookies() {
        String coockie = headers.getOrDefault("Cookie", "");
        String[] coockiesList = coockie.split(";");

        for (String c : coockiesList) {
            if(!c.equals("")) {
                String[] cList = c.split("=");
                cookies.put(cList[0], cList[1]);
            }
        }
    }
}
