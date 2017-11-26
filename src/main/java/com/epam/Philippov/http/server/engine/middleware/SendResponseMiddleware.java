package com.epam.Philippov.http.server.engine.middleware;

import com.epam.Philippov.http.server.engine.Response;
import com.epam.Philippov.http.server.engine.ResponseInterface;
import lombok.SneakyThrows;

import java.io.*;

public class SendResponseMiddleware{
//    @Override
    @SneakyThrows
    public void call(Response response, BufferedWriter out) {
//        PrintWriter outWriter = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
        BufferedReader reader;

        try {
            out.write("HTTP/1.1" + response.getError() + "OK\n");

            StringWriter headers = new StringWriter();
            for (String key : response.getHeaders().keySet()) {
                headers.write(key);
                headers.write(": ");
                headers.write(response.getHeaders().get(key));
                headers.write("/n");
            }

            out.write(headers.toString());
            out.write("\n");
            out.write(response.getResponse());
            out.flush();

//            reader = response.getResponse();
//
//            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
//                out.write(line + "\n");
//            }

        }catch (NullPointerException e){
            out.write("404 File not found.");
        } finally {
            out = null;
        }
    }
}
