package com.epam.Philippov.http.server.core;

import com.epam.Philippov.http.framework.Request;
import com.epam.Philippov.http.framework.response.Response;
import lombok.SneakyThrows;

import java.io.*;
import java.util.HashMap;

public class SendResponse {
//    @Override
    @SneakyThrows
    public void call(Response response, Request request) {
        BufferedWriter out = request.getOut();

        try {
            StringWriter headers = new StringWriter();
            HashMap<String, String> responseHeaders = response.getHeaders();

            responseHeaders.putAll(request.getHeaders());
            responseHeaders.put("Cookie", request.getCoockiesAsString());

            out.write("HTTP/1.1" + response.getError() + "OK\n");

            for (String key : responseHeaders.keySet()) {
                headers.write(key);
                headers.write(": ");
                headers.write(response.getHeaders().get(key));
                headers.write("\r\n");
            }
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

}
