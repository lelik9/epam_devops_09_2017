package com.my.http.framework.response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import lombok.SneakyThrows;

public class FileResponse extends Response {


    public FileResponse(String resourceName) {
        this("/",resourceName);
    }

    @SneakyThrows
    public FileResponse(String staticPath, String resourceName) {
        InputStream resourceReader = Class.class.getResourceAsStream(staticPath+resourceName);

        if(resourceReader != null){
            StringWriter response = new StringWriter();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "utf-8"))){
                while (reader.ready()){
                    response.write(reader.readLine());
                }
            }
            this.response = response.toString();
            this.statusCode = 200;
            this.error = "";
//            this.headers.put("data","image/svg+xml");
        }else {
            this.response = "404 File not found.";
            this.statusCode = 200;
            this.error = "";
        }

    }

//    @Override
//    @SneakyThrows
//    public BufferedReader getResponse() {
//        InputStream resourceReader = Class.class.getResourceAsStream(staticPath+resourceName);
//        return new BufferedReader(new InputStreamReader(resourceReader, "utf-8"));
//    }
}
