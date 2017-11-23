package com.epam.Philippov.http.server.engine.middleware;

import com.epam.Philippov.http.server.engine.ResponseInterface;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SendResponseMiddleware implements PostMiddleware {
    @Override
    @SneakyThrows
    public ResponseInterface call(ResponseInterface response) {
        PrintWriter outWriter = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
        BufferedReader reader;

        try {
            reader = response.getResponse();

            for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                outWriter.write(line + "\n");
            }

        }catch (NullPointerException e){
            outWriter.write("404 File not found.");
        } finally {
            outWriter = null;
        }

        return response;
    }
}
