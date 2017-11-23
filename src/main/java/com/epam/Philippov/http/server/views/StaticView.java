package com.epam.Philippov.http.server.views;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaticView extends View{

    @Override
    @SneakyThrows
    public void get(String query) {
        Pattern pattern = Pattern.compile("([\\/\\w+]*)\\/([\\w+\\.]*)");
        Matcher matcher = pattern.matcher(query);
        matcher.find();

        String path = matcher.group(1);
        String fileName = matcher.group(2);

//        if(!path.equals("")){
//            path = path + "/";
//        }

//        try {
//            resourceReader = Class.class.getResourceAsStream(path +"/"+ fileName);
//            reader = new BufferedInputStream(resourceReader);
//
//            while (reader.available() > 0){
//                outWriter.write(reader.read());
//            }
//        } catch (FileNotFoundException e) {
//            outWriter.write("404 File not found.");
//        } finally {
//          outWriter.close();
//          reader.close();
//        }
        returnResource(path +"/"+ fileName);

    }

    @Override
    public void returnResource(String resourceName) {
        super.returnResource(resourceName);
    }
}
